package com.example.thinkingandtest.Act;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thinkingandtest.JavaBeanAndAdapter.MesAdapter;
import com.example.thinkingandtest.JavaBeanAndAdapter.Message;
import com.example.thinkingandtest.R;

import java.util.ArrayList;
import java.util.List;

public class Message_Act extends AppCompatActivity {

    private List<Message> messageList = new ArrayList<>();
    private MesAdapter adapter;

    private EditText mes_et_input;
    private Button mes_btn_send;
    private RecyclerView mes_relv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_message);

        /**隐藏标题栏*/
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        initMes();

        //绑定控件
        mes_et_input = findViewById(R.id.mes_et_input);
        mes_btn_send = findViewById(R.id.mes_btn_send);
        mes_relv = findViewById(R.id.mes_relv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mes_relv.setLayoutManager(layoutManager);
        adapter = new MesAdapter(messageList);
        mes_relv.setAdapter(adapter);

        mes_btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mes_et_input.getText().toString();
                if(!"".equals(content)){
                    Message message = new Message(content, Message.TYPE_SENT);
                    messageList.add(message);
                    Message message2 = new Message("这是一条自动回复", Message.TYPE_RECEIVED);
                    messageList.add(message2);
                    //下面一行注释了也没影响（怪
                    adapter.notifyItemInserted(messageList.size()-1); //通知列表有新数据插入
                    mes_relv.scrollToPosition(messageList.size()-1);    //定位在最后一行
                    mes_et_input.setText("");
                }
            }
        });
    }

    private void initMes(){
        Message mes1 = new Message("Hello guy.", Message.TYPE_RECEIVED);
        messageList.add(mes1);
        Message mes2 = new Message("Hello. What's that?", Message.TYPE_SENT);
        messageList.add(mes2);
        Message mes3 = new Message("This is Jerry.", Message.TYPE_RECEIVED);
        messageList.add(mes3);
    }
}
