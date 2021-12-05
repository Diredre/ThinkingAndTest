package com.example.thinkingandtest.Act;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.thinkingandtest.R;

public class Login_Act extends BaseAct {

    private EditText login_et_psw, login_et_account;
    private CheckBox login_cb_rempsw;
    private Button login_btn_login;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        login_et_psw = findViewById(R.id.login_et_psw);
        login_et_account = findViewById(R.id.login_et_account);
        login_btn_login = findViewById(R.id.login_btn_login);
        login_cb_rempsw = findViewById(R.id.login_cb_rempsw);

        boolean isRemPsw = pref.getBoolean("remeber_password", false);

        if(isRemPsw){
            //选中【记住密码】->将账号密码设置到文本框中
            login_et_account.setText(pref.getString("account", ""));
            login_et_psw.setText(pref.getString("password", ""));
            login_cb_rempsw.setChecked(true);
        }

        login_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = login_et_account.getText().toString();
                String psw = login_et_psw.getText().toString();

                if (account.equals("admin") && psw.equals("123")){
                    editor = pref.edit();
                    if(login_cb_rempsw.isChecked()){
                        editor.putBoolean("remeber_password", true);
                        editor.putString("account", account);
                        editor.putString("password", psw);
                    }else{
                        editor.clear();
                    }
                    editor.apply();

                    Intent intent = new Intent(Login_Act.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Login_Act.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
