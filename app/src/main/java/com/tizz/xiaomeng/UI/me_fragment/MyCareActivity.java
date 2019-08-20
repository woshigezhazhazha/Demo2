package com.tizz.xiaomeng.UI.me_fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tizz.xiaomeng.R;

public class MyCareActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView title;
    private LinearLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_care);
        initViews();
        setOnListener();
    }

    public void initViews(){
        back=(LinearLayout) findViewById(R.id.img_back);

        title=(TextView)findViewById(R.id.txt_title);
        title.setText("我的关注");
    }

    public void setOnListener(){
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.img_back:
                MyCareActivity.this.finish();
                break;
            default:
                break;
        }
    }
}
