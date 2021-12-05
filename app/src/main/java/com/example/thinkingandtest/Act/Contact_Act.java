package com.example.thinkingandtest.Act;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.thinkingandtest.R;

import java.util.ArrayList;
import java.util.List;

public class Contact_Act extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    List<String> contactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contact);

        /**隐藏标题栏*/
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Button ct_btn_call = findViewById(R.id.ct_btn_call);
        ct_btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Contact_Act.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Contact_Act.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    call();
                }
            }
        });

        ListView ct_lv = findViewById(R.id.ct_lv);
        adapter = new ArrayAdapter<String>(Contact_Act.this, android.R.layout.simple_list_item_1, contactsList);
        ct_lv.setAdapter(adapter);
        if(ContextCompat.checkSelfPermission(Contact_Act.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Contact_Act.this, new String[] {Manifest.permission.READ_CONTACTS}, 2);
        }else{
            readContacts();
        }
    }

    public void call(){
        try{            //防止程序崩溃
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:13007239653"));
            startActivity(intent);
        }catch(SecurityException e){
            e.printStackTrace();
        }
    }


    public void readContacts(){
        Cursor cursor = null;
        try{
            //查询联系人数据
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if(cursor != null){
                while(cursor.moveToNext()){
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add(displayName + "\n" + number);
                }
                adapter.notifyDataSetChanged();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(cursor != null)  cursor.close();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) call();
                else    Toast.makeText(Contact_Act.this, "You denied the permission", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) readContacts();
                else    Toast.makeText(Contact_Act.this, "You denied the permission", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }
}
