package com.tizz.xiaomeng.UI.discovery_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tizz.xiaomeng.R;

public class SearchActivity extends AppCompatActivity implements
        View.OnClickListener,View.OnTouchListener{


    private Button cancel;
    private EditText mEditText;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
        setOnListener();


    }

    public void initViews(){
        mEditText=(EditText) findViewById(R.id.et_search);
        cancel=(Button)findViewById(R.id.btn_cancel);
        layout=(LinearLayout)findViewById(R.id.ll_search);
    }

    public void setOnListener(){
        cancel.setOnClickListener(this);
        layout.setOnTouchListener(this);
    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_cancel:
                SearchActivity.this.finish();
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event){
        layout.setFocusable(true);
        layout.setFocusableInTouchMode(true);
        layout.requestFocus();

        InputMethodManager imm=(InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(layout.getWindowToken(),0);
        return false;
    }





}
