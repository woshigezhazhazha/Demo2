package com.tizz.xiaomeng.Widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by 13371 on 2017/8/27.
 */

public class EmotionInputDetector {

    private static final String SHARE_PREFERENCE_NAME="com.tizz.xiaomeng.emotioninputdetector";
    private static final String SHARE_PREFERENCE_TAG="soft_input_height";

    private Activity mActivity;
    private InputMethodManager mInputMethodManager;
    private SharedPreferences sp;
    private View mContentView;
    private View mSendButton;
    private View mEmotionLayout;
    private EditText mEditText;
    private boolean isShowEmotion=false;
    private ViewPager mViewPager;

    private EmotionInputDetector(){}

    public static EmotionInputDetector with(Activity activity){
        EmotionInputDetector emotionInputDetector=new EmotionInputDetector();
        emotionInputDetector.mActivity=activity;
        emotionInputDetector.mInputMethodManager=(InputMethodManager)activity.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        emotionInputDetector.sp=activity.getSharedPreferences(SHARE_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return emotionInputDetector;
    }

    public EmotionInputDetector setEmotionView(View emotionView){
        mEmotionLayout=emotionView;
        return this;
    }

    public EmotionInputDetector setViewPager(ViewPager viewPager){
        mViewPager=viewPager;
        return this;
    }

    public EmotionInputDetector bindToContent(View contentView){
        mContentView=contentView;
        return this;
    }

    public EmotionInputDetector bindToEditText(EditText editText){
        mEditText=editText;
        mEditText.requestFocus();
        mEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP&&mEmotionLayout.isShown()){
                    lockContentHeight();
                    hideEmotionLayout(true);

                    mEditText.postDelayed(new Runnable(){
                        @Override
                        public void run(){
                            unLockContentHeightDelayed();
                        }
                    },100L);
                }
                return false;
            }
        });

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return this;
    }

    public EmotionInputDetector bindToEmotionButton(View emotionButton){
        emotionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEmotionLayout.isShown()){
                    lockContentHeight();
                    hideEmotionLayout(true);
                    isShowEmotion=false;
                    unLockContentHeightDelayed();
                }else{
                    if (isSoftInputShow()){
                        lockContentHeight();
                        showEmotionLayout();
                        unLockContentHeightDelayed();
                    }else{
                        showEmotionLayout();
                    }
                    mViewPager.setCurrentItem(0);
                    isShowEmotion=true;
                }
            }
        });
        return this;
    }

    public EmotionInputDetector bindToSendButton(View sendButton){
        mSendButton=sendButton;
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });
        return this;
    }

    public boolean interceptBackPress(){
        if (mEmotionLayout.isShown()){
            hideEmotionLayout(false);
            return true;
        }
        return false;
    }

    private void showEmotionLayout(){
        int softInputHeight=getSupportSoftInputHeight();
        if (softInputHeight==0){
            softInputHeight=sp.getInt(SHARE_PREFERENCE_TAG,787);
        }
        hideSoftInput();
        mEmotionLayout.getLayoutParams().height=softInputHeight;
        mEmotionLayout.setVisibility(View.VISIBLE);
    }

    private void hideEmotionLayout(boolean showSoftInput){
        if (mEmotionLayout.isShown()){
            mEmotionLayout.setVisibility(View.GONE);
            if (showSoftInput){
                showSoftInput();
            }
        }
    }

    private void lockContentHeight(){
        LinearLayout.LayoutParams params=(LinearLayout.LayoutParams)mContentView.getLayoutParams();
        params.height=mContentView.getHeight();
        params.weight=0.0F;
    }

    private void unLockContentHeightDelayed(){
        mEditText.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((LinearLayout.LayoutParams)mContentView.getLayoutParams()).weight=1.0F;
            }
        },100L);
    }

    private void showSoftInput(){
        mEditText.requestFocus();
        mEditText.post(new Runnable() {
            @Override
            public void run() {
                mInputMethodManager.showSoftInput(mEditText,0);
            }
        });
    }

    private void hideSoftInput(){
        mInputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(),0);
    }

    private boolean isSoftInputShow(){
        return getSupportSoftInputHeight()!=0;
    }

    private int getSupportSoftInputHeight(){
        Rect r=new Rect();
        mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        int screenHeight=mActivity.getWindow().getDecorView().getRootView().getHeight();
        int softInputHeight=screenHeight-r.bottom;
        softInputHeight=softInputHeight-getSoftButtonsBarHeight();
        if (softInputHeight<0){
            Log.w("EmotionInputDetector","Warning: value of softInputHeight is below zero");
        }
        if (softInputHeight>0){
            sp.edit().putInt(SHARE_PREFERENCE_TAG,softInputHeight).apply();
        }
        return softInputHeight;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private int getSoftButtonsBarHeight(){
         DisplayMetrics metrics=new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight=metrics.heightPixels;
        mActivity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight=metrics.heightPixels;
        if (realHeight>usableHeight){
            return realHeight-usableHeight;
        }else{
            return 0;
        }
    }

    public EmotionInputDetector build(){
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.
            SOFT_INPUT_STATE_ALWAYS_HIDDEN|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        hideSoftInput();
        return this;
    }


}
