package com.tizz.xiaomeng.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tizz.xiaomeng.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText account,password;
    Button login,register,forget_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    private void init(){
        account=(EditText)findViewById(R.id.account);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.register);
        forget_pwd=(Button)findViewById(R.id.forget_psw);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        forget_pwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v==register){
            Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        }
        else if(v==forget_pwd){
            Intent intent=new Intent(LoginActivity.this,ForgetPasswordActivity.class);
            startActivity(intent);
        }
        else{

            /*
            boolean isNetConnected= CommonUtils.isNetworkAvailable(this);
            if(!isNetConnected){
                Toast.makeText(LoginActivity.this,getString(R.string.no_network),
                        Toast.LENGTH_SHORT).show();
                return;
            }
            */

            login();
        }
    }



    private void login(){
        String userName=account.getText().toString();
        String pwd=password.getText().toString();

        if(TextUtils.isEmpty(userName)){
            Toast.makeText(LoginActivity.this,getString(R.string.account_cannot_empty),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            Toast.makeText(LoginActivity.this, R.string.password_cannot_empty,
                    Toast.LENGTH_SHORT).show();
            return;
        }



        //// TODO: 2017/7/10 进一步逻辑判断


        ProgressDialog progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("正在登录...");
        progressDialog.setCancelable(true);
        progressDialog.show();

        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);

    }





}

