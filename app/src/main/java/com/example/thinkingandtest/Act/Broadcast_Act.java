package com.example.thinkingandtest.Act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.example.thinkingandtest.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Broadcast_Act extends BaseAct {

    /*private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;


    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }*/

    /*private IntentFilter intentFilter;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;


    class LocalReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }
    }*/

    private EditText bc_et_save;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_broadcast);

        /**隐藏标题栏*/
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        /*localBroadcastManager = LocalBroadcastManager.getInstance(this);        //获取示例*/

        /*intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);*/

        bc_et_save = findViewById(R.id.bc_et_save);
        String inputText = load();
        if(!TextUtils.isEmpty(inputText)){
            bc_et_save.setText(inputText) ;
            bc_et_save.setSelection(inputText.length());
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show();
        }

        Button bc_btn_send = findViewById(R.id.bc_btn_send);
        bc_btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent("com.example.thinkingandtest.MY_BROADCAST");
                //8.0及以上版本，自定义广播接收不到，所以写了这一行↓
                intent.setComponent( new ComponentName( "com.example.thinkingandtest" , "com.example.thinkingandtest.MyBroadcastReceiver") );
                sendBroadcast(intent);*/

                /*Intent intent = new Intent("com.example.thinkingandtest.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);    //发送本地广播*/

                Intent intent = new Intent("com.example.thinkingandtest.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });

        /*intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.thinkingandtest.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);    //注册本地广播监听器*/
    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }*/

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }*/

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        String inputText = bc_et_save.getText().toString();
        save(inputText);
    }

    //文件储存
    public void save(String inputText){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(writer != null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //文件读取
    public String load(){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try{
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while((line = reader.readLine()) != null){
                content.append(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return content.toString();
    }
}