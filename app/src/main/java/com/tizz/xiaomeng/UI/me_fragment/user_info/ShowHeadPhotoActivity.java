package com.tizz.xiaomeng.UI.me_fragment.user_info;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tizz.xiaomeng.R;
import com.tizz.xiaomeng.UI.me_fragment.me_fg;

public class ShowHeadPhotoActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView title;
    private LinearLayout back;
    private AlertDialog dialog;
    private ImageView picture;
    private Button use;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_head_photo);
        initViews();
        setOnListener();

    }

    public void initViews(){
        back=(LinearLayout)findViewById(R.id.img_back);
        title=(TextView)findViewById(R.id.txt_title);
        title.setText("编辑头像");
        picture=(ImageView)findViewById(R.id.head);
        Intent intent=getIntent();
        if (intent!=null){
            bitmap=intent.getParcelableExtra("bitmap");
            picture.setImageBitmap(bitmap);
        }

        use=(Button)findViewById(R.id.btn_use);
    }





    public void setOnListener(){
        back.setOnClickListener(this);
        use.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.img_back:
                ShowHeadPhotoActivity.this.finish();
                break;
            case R.id.btn_use:
                Intent intent=new Intent(ShowHeadPhotoActivity.this, me_fg.class);
                intent.putExtra("bitmap",bitmap);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


}
