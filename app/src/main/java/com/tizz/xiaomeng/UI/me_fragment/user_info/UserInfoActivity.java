package com.tizz.xiaomeng.UI.me_fragment.user_info;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tizz.xiaomeng.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.graphics.Bitmap.CompressFormat.PNG;
public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView title,name,photo,camera,album,cancel;
    private LinearLayout back;
    private AlertDialog dialog;
    private Context context;
    private Activity activity;
    private Uri outputUri;

    private static final int PHOTO_TAKE=2;
    private static final int PHOTO_ALBUM=3;
    private static final int PHOTO_SHOW=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initViews();
        setOnListener();
    }

    public void initViews(){
        title=(TextView)findViewById(R.id.txt_title);
        title.setText("个人信息");
        back=(LinearLayout)findViewById(R.id.img_back);
        name=(TextView)findViewById(R.id.tv_myName);
        photo=(TextView)findViewById(R.id.tv_myPhoto);
        context=this;
        activity=this;


    }

    public void setOnListener(){
        back.setOnClickListener(this);
        name.setOnClickListener(this);
        photo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.img_back:
                UserInfoActivity.this.finish();
                break;
            case R.id.tv_myName:
                Intent i=new Intent(UserInfoActivity.this,EditNameActivity.class);
                startActivity(i);
                break;
            case R.id.tv_myPhoto:
                showDialog();
                break;
            default:
                break;
        }
    }

    File mCameraFile,mCropFile;
    public void showDialog(){
        View view= dialogView();
        dialog=new AlertDialog.Builder(this).setTitle("").setView(view).create();
        dialog.show();

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCameraFile=null;
                try{
                    mCameraFile=createImageFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if (mCameraFile!=null){
                    Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                        Uri uriForFile= FileProvider.getUriForFile(activity,
                                "com.tizz.xiaomeng.fileprovider",mCameraFile);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,uriForFile);
                        cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }else{
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(mCameraFile));
                    }
                    startActivityForResult(cameraIntent,PHOTO_TAKE);
                    dialog.dismiss();
                }
                }
        });
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albumIntent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
                albumIntent.setType("image/*");
                albumIntent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(albumIntent,PHOTO_ALBUM);
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
}

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case PHOTO_TAKE:
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                        Uri inputUri=FileProvider.getUriForFile(activity,
                                "com.tizz.xiaomeng.fileprovider",mCameraFile);
                        startPhotoZoom(inputUri);
                    }else{
                        Uri inputUri=Uri.fromFile(mCameraFile);
                        startPhotoZoom(inputUri);
                    }
                    break;
                case PHOTO_ALBUM:
                    Uri uri=data.getData();
                    startPhotoZoom(uri);
                    break;
                case PHOTO_SHOW:
                    try{
                        Bitmap bitmap= BitmapFactory.decodeStream(
                                getContentResolver().openInputStream(outputUri));
                        Intent intent=new Intent(UserInfoActivity.this,
                                ShowHeadPhotoActivity.class);
                        //图片太大传不了
                        intent.putExtra("bitmap",bitmap);
                        startActivity(intent);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }


    String mCurrentPhotoPath;
    private File createImageFile() throws IOException {
        String timeStamp=new SimpleDateFormat("yyMMdd_HHmmss", Locale.CHINA).
                format(new Date());
        String imageFileName="png_"+timeStamp+"_";

        File storageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image=File.createTempFile(imageFileName,".png",storageDir);
        mCurrentPhotoPath="file:"+image.getAbsolutePath();
        return image;
    }

    public void startPhotoZoom(Uri inputUri){
        mCropFile=null;
        try{
            mCropFile=createImageFile();
        }catch(IOException e){
            e.printStackTrace();
        }
        if (inputUri==null){
            Log.e("error","the uri is not exit");
            return;
        }
        Intent intent=new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        outputUri=Uri.fromFile(mCropFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,outputUri);
        intent.setDataAndType(inputUri,"image/*");
        intent.putExtra("crop",true);
        intent.putExtra("scale",true);
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        intent.putExtra("outputX",300);
        intent.putExtra("outputY",300);
        intent.putExtra("return-data",false);
        intent.putExtra("noFaceDetection",false);
        intent.putExtra("outputFormat",PNG);
        startActivityForResult(intent,PHOTO_SHOW);
    }

    @SuppressLint("NewApi")
    public View dialogView(){
        LinearLayout layout=new LinearLayout(this);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(0xffffffff);
        album=new TextView(this);
        LinearLayout.LayoutParams textViewParams=new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        album.setLayoutParams(textViewParams);
        album.setPadding(20, 20, 0, 20);
        album.setText("相册");
        album.setGravity(Gravity.CENTER);
        album.setTextSize(20);
        album.setBackground(getBackGroundColor());
        TextView blod1=new TextView(this);
        LinearLayout.LayoutParams blodViewParams=new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,1);
        blod1.setLayoutParams(blodViewParams);
        blod1.setBackgroundColor(0xffd7d7d7);
        TextView blod2=new TextView(this);
        blod2.setLayoutParams(blodViewParams);
        blod2.setBackgroundColor(0xffd7d7d7);
        camera=new TextView(this);
        LinearLayout.LayoutParams photoParams=new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        camera.setLayoutParams(photoParams);
        camera.setPadding(20, 20, 0, 20);
        camera.setText("拍照");
        camera.setGravity(Gravity.CENTER);
        camera.setBackground(getBackGroundColor());
        camera.setTextSize(20);
        cancel=new TextView(this);
        LinearLayout.LayoutParams backParams=new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        cancel.setLayoutParams(backParams);
        cancel.setGravity(Gravity.CENTER);
        cancel.setPadding(0, 25, 0, 25);
        cancel.setText("取消");
        cancel.setTextSize(14);
        cancel.setBackground(getBackGroundColor());
        layout.addView(album);
        layout.addView(blod1);
        layout.addView(camera);
        layout.addView(blod2);
        layout.addView(cancel);
        return  layout;
    }
    private StateListDrawable getBackGroundColor() {
        Drawable press=new ColorDrawable(0xffd7d7d7);
        Drawable normal=new ColorDrawable(0xffffffff);
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed}, press);
        drawable.addState(new int[]{-android.R.attr.state_pressed},normal);
        return  drawable;
    }

}
