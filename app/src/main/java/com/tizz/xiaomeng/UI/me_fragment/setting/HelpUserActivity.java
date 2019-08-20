package com.tizz.xiaomeng.UI.me_fragment.setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tizz.xiaomeng.R;

public class HelpUserActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView title;
    private LinearLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_user);
        initView();
        setOnListener();
    }

    public void initView(){
        back=(LinearLayout)findViewById(R.id.img_back);

        title=(TextView)findViewById(R.id.txt_title);
        title.setText("功能介绍");
    }

    public void setOnListener(){
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.img_back:
                HelpUserActivity.this.finish();
                break;
            default:
                break;
        }
    }
}
