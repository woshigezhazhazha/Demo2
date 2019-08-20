package com.tizz.xiaomeng.UI.me_fragment.setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tizz.xiaomeng.R;

public class AboutUsActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout back;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about_us);
        initViews();
        setOnListener();
    }

    public void initViews(){
        back=(LinearLayout) findViewById(R.id.img_back);

        title=(TextView)findViewById(R.id.txt_title);
        title.setText("关于校盟");
    }

    public void setOnListener(){
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.img_back:
                AboutUsActivity.this.finish();
                break;
            default:
                break;
        }
    }
}
