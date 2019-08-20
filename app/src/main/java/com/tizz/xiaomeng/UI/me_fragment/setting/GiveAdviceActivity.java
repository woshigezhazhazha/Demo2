package com.tizz.xiaomeng.UI.me_fragment.setting;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tizz.xiaomeng.R;

public class GiveAdviceActivity extends AppCompatActivity implements
        View.OnClickListener,View.OnTouchListener{

    private TextView title;
    private LinearLayout back;
    private Button upLoadAdvice;
    private EditText inputAdvice;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_advice);
        initViews();
        setOnListener();
    }

    public void initViews(){
        title=(TextView)findViewById(R.id.txt_title);
        title.setText("提供反馈");
        back=(LinearLayout)findViewById(R.id.img_back);
        upLoadAdvice=(Button)findViewById(R.id.btn_upLoadAdvice);
        inputAdvice=(EditText)findViewById(R.id.et_giveAdvice);
        layout=(LinearLayout)findViewById(R.id.ll_giveAdvice);
    }



    public void setOnListener(){
        back.setOnClickListener(this);
        upLoadAdvice.setOnClickListener(this);
        layout.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.img_back:
                GiveAdviceActivity.this.finish();
                break;
            case R.id.btn_upLoadAdvice:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event){
        layout.setFocusable(true);
        layout.setFocusableInTouchMode(true);
        layout.requestFocus();

        //关闭输入法
        InputMethodManager imm=(InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(layout.getWindowToken(),0);
        return false;
    }


}
