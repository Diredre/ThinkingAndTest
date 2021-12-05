package com.example.thinkingandtest.Act;

import android.content.Intent;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thinkingandtest.JavaBeanAndAdapter.Book;
import com.example.thinkingandtest.R;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.net.URL;
import java.util.List;

public class LitePal_Act extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_litepal);

        /**隐藏标题栏*/
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        //建表
        Button main_btn_creatDatabase = findViewById(R.id.lp_btn_creatDatabase);
        main_btn_creatDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
                Toast.makeText(LitePal_Act.this, "succeed create", Toast.LENGTH_SHORT).show();
            }
        });

        //添加数据
        Button main_btn_addData = findViewById(R.id.lp_btn_addData);
        main_btn_addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();

                Toast.makeText(LitePal_Act.this, "succeed add", Toast.LENGTH_SHORT).show();
            }
        });

        //更新数据
        Button lp_btn_updateData = findViewById(R.id.lp_btn_updateData);
        lp_btn_updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //法一：只能对已储存的对象进行操作
                /*Book book = new Book();

                book.setName("The Lost Symbol");
                book.setAuthor("Dan Brown");
                book.setPages(510);
                book.setPrice(19.95);
                book.setPress("Unknow");
                book.save();

                book.setPrice(10.99);
                book.save();*/

                //法2：指定约束条件（更灵活）
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?", "The Lost Symbol", "Dan Brown");

                //若用法二将某字段更新为默认值
                /*Book book = new Book();
                book.setToDefault("pages");         //将pages修改为默认值
                book.updateAll();*/

                Toast.makeText(LitePal_Act.this, "succeed update", Toast.LENGTH_SHORT).show();
            }
        });

        //删除数据
        Button lp_btn_deleteData = findViewById(R.id.lp_btn_deleteData);
        lp_btn_deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class,"price < ?","15");
                Toast.makeText(LitePal_Act.this, "succeed delete", Toast.LENGTH_SHORT).show();
            }
        });

        //查询数据
        Button lp_btn_searchData = findViewById(R.id.lp_btn_searchData);
        lp_btn_searchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.findAll(Book.class);
                for(Book book: books){
                    Log.d("LitePal_Act", "book name is" + book.getName());
                    Log.d("LitePal_Act", "book author is" + book.getAuthor());
                    Log.d("LitePal_Act", "book pages is" + book.getPages());
                    Log.d("LitePal_Act", "book price is" + book.getPrice());
                    Log.d("LitePal_Act", "book press is" + book.getPress());
                }
                Toast.makeText(LitePal_Act.this, "succeed search", Toast.LENGTH_SHORT).show();

                /**其他LitePal的API*/
                /*select()用于指定查询哪几列的数据
                List<Book> books = LitPal.select("name", "author").find(Book.class);*/

                /*where()用于指定查询哪的约束条件
                List<Book> books = LitPal.where("pages > ?", "400").find(Book.class);*/

                /*order()用于指定结果的排序方式，desc降序，asc或不写则升序。
                如按价格高到低排序：
                List<Book> books = LitPal.order("price desc").find(Book.class);*/

                /*limit()用于指定查询结果的数量，如查询表中前三条数据：
                List<Book> books = LitPal.limit(3).find(Book.class);*/

                /*offset()用于指定查询结果的偏移量，如查询表中第2/3/4条数据：
                List<Book> books = LitPal.limit(3).offset(1)find(Book.class);*/

                /**连缀例子：查询Book中第1-20条满足页数大于400条件的name、author、pages，并将结果按页数升序排列
                 List<Book> books1 = LitePal.select("name", "author", "pages")
                         .where("pages > ?", "400")
                         .order("pages")
                         .limit(10)
                         .offset(10)
                         .find(Book.class);*/
            }
        });

        Button lp_btn_jmp720yun = findViewById(R.id.lp_btn_jmp720yun);
        lp_btn_jmp720yun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://720yun.com/t/243jurefta8?scene_id=21458817");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });
    }
}