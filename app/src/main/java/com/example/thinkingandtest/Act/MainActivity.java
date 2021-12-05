package com.example.thinkingandtest.Act;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.thinkingandtest.JavaBeanAndAdapter.Fruit;
import com.example.thinkingandtest.JavaBeanAndAdapter.FruitAdapter2;
import com.example.thinkingandtest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends BaseAct {

    private List<Fruit> fruitList = new ArrayList<Fruit>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**隐藏标题栏*/
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        Button main_btn_toMes = findViewById(R.id.main_btn_toMes);
        main_btn_toMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Message_Act.class));
            }
        });

        Button main_btn_toBroadcast = findViewById(R.id.main_btn_toBroadcast);
        main_btn_toBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Broadcast_Act.class));
            }
        });

        Button main_btn_toLitePal = findViewById(R.id.main_btn_toLitePal);
        main_btn_toLitePal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LitePal_Act.class));
            }
        });

        Button main_btn_toContact = findViewById(R.id.main_btn_toContact);
        main_btn_toContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Contact_Act.class));
            }
        });

        initFruits();

        /*FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
        ListView lv = findViewById(R.id.main_lv);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                int real_pos = position + 1;
                Toast.makeText(MainActivity.this, "第" + real_pos + "位：" + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });*/

        RecyclerView recyclerView = findViewById(R.id.main_relv);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);   //实现水平滑动列表
        //recyclerView.setLayoutManager(linearLayoutManager);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter2 fruitAdapter2 = new FruitAdapter2(fruitList);
        recyclerView.setAdapter(fruitAdapter2);
    }

    private void initFruits(){
        for(int i = 0; i < 20; i++){
            fruitList.add(new Fruit(getRandomLengthName("Apple"), R.drawable.ic_launcher_background));
        }
    }

    private String getRandomLengthName(String name){
        Random rd = new Random();
        int Length = rd.nextInt(20) + 1;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < Length; i++){
            sb.append(name);
        }
        return sb.toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.it_add:
                Toast.makeText(this, "You click item_add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.it_remove:
                Toast.makeText(this, "You click item_remove", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}
