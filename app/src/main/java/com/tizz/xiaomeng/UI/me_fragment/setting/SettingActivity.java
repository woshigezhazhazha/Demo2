package com.tizz.xiaomeng.UI.me_fragment.setting;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tizz.xiaomeng.R;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView aboutUs;
    private TextView cleanSpace;
    private TextView giveAdvice;
    private TextView helpUser;
    private Button logout;
    private LinearLayout back;
    private TextView title;
    private TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initViews();
        setOnListener();
    }

    public void initViews(){
        helpUser=(TextView)findViewById(R.id.helpUser);
        aboutUs=(TextView)findViewById(R.id.aboutAs);
        cleanSpace=(TextView)findViewById(R.id.cleanSpace);
        giveAdvice=(TextView)findViewById(R.id.giveAdvice);
        logout=(Button)findViewById(R.id.btn_logout);
        back=(LinearLayout)findViewById(R.id.img_back);

        userName=(TextView)findViewById(R.id.tv_userName);
        title=(TextView)findViewById(R.id.txt_title);
        title.setText("设置");


    }

    public void setOnListener(){
        aboutUs.setOnClickListener(this);
        cleanSpace.setOnClickListener(this);
        giveAdvice.setOnClickListener(this);
        helpUser.setOnClickListener(this);
        logout.setOnClickListener(this);
        back.setOnClickListener(this);


    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                SettingActivity.this.finish();
                break;
            case R.id.aboutAs:
                Intent i=new Intent(SettingActivity.this,AboutUsActivity.class);
                startActivity(i);
                break;
            case R.id.cleanSpace:
                Context mContext=this;
                showNoticeDialog(mContext);
                break;
            case R.id.giveAdvice:
                Intent m=new Intent(SettingActivity.this,GiveAdviceActivity.class);
                startActivity(m);
                break;
            case R.id.helpUser:
                Intent t=new Intent(SettingActivity.this,HelpUserActivity.class);
                startActivity(t);
                break;
            case R.id.btn_logout:
                break;
        }
    }


    //点击清理存储，弹出弹窗
    public void showNoticeDialog(Context mContext){
        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
        builder.setTitle("提示");
        builder.setMessage("确定清理所有缓存吗？");
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which){
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which){
                dialog.dismiss();
            }
        });
        Dialog noticeDialog=builder.create();
        noticeDialog.show();
    }


}
