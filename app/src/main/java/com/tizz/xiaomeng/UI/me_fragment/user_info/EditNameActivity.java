package com.tizz.xiaomeng.UI.me_fragment.user_info;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tizz.xiaomeng.R;

public class EditNameActivity extends AppCompatActivity implements
        View.OnClickListener,View.OnTouchListener{

    private LinearLayout back;
    private TextView title;
    private Button save;
    private EditText editName;
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);
        initViews();
        setOnListener();
    }

    public void initViews(){
        back=(LinearLayout)findViewById(R.id.img_back);
        title=(TextView)findViewById(R.id.txt_title);
        title.setText("编辑昵称");
        save=(Button)findViewById(R.id.btn_saveName);
        editName=(EditText)findViewById(R.id.et_userName);
        layout=(LinearLayout)findViewById(R.id.ll_editName);



    }

    public void setOnListener(){
        back.setOnClickListener(this);
        save.setOnClickListener(this);
        layout.setOnTouchListener(this);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.img_back:
                EditNameActivity.this.finish();
                break;
            case R.id.btn_saveName:
                if(editName.getText().toString().trim().equals("")){
                    Toast toast=Toast.makeText(EditNameActivity.this,"昵称不能为空！",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    break;
                }
                    break;
            default:
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
