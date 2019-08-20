package com.tizz.xiaomeng.UI.Meng;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.tizz.xiaomeng.R;

public class MengActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView title;
    private LinearLayout back;
    private LinearLayout more;
    private PopupWindow mPopupWindow;
    private TextView care,cancelCare,createQuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meng);
        initViews();
        setOnListener();
    }

    public void initViews(){
        Intent i=getIntent();
        String str=i.getStringExtra("String");
        title=(TextView)findViewById(R.id.txt_title);
        title.setText(str);
        back=(LinearLayout)findViewById(R.id.img_back);
        more=(LinearLayout)findViewById(R.id.ll_more);
        more.setVisibility(View.VISIBLE);

    }

    public void setOnListener(){
        back.setOnClickListener(this);
        more.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.img_back:
                MengActivity.this.finish();
                break;
            case R.id.ll_more:
                initPopWindow();
                break;
            case R.id.createQuan:
                Intent m=new Intent(MengActivity.this,CreateQuanActivity.class);
                startActivity(m);
                mPopupWindow.dismiss();
                break;
            case R.id.meng_cancelCare:
                Toast toast=Toast.makeText(MengActivity.this,"正在取消关注...",
                        Toast.LENGTH_SHORT);
                toast.show();
                mPopupWindow.dismiss();
                break;
            case R.id.meng_care:
                Toast toast1=Toast.makeText(MengActivity.this,"正在关注...",
                        Toast.LENGTH_SHORT);
                toast1.show();
                mPopupWindow.dismiss();
                break;
        }
    }

    public void initPopWindow(){
        View parent=View.inflate(this,R.layout.common_title_layout,null);
        View convertView= LayoutInflater.from(MengActivity.this).inflate(R.layout.title_popup_meng,null);
        mPopupWindow=new PopupWindow(convertView);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setBackgroundAlpha(0.6f);

        care=(TextView)convertView.findViewById(R.id.meng_care);
        cancelCare=(TextView)convertView.findViewById(R.id.meng_cancelCare);
        createQuan=(TextView)convertView.findViewById(R.id.createQuan);
        care.setOnClickListener(this);
        cancelCare.setOnClickListener(this);
        createQuan.setOnClickListener(this);

        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(parent, Gravity.RIGHT|Gravity.TOP,
                0,0);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
    }

    //设置背景透明度
    public void setBackgroundAlpha(float bgAlpha) {
        Context mContext=this;
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().setAttributes(lp);
    }

}
