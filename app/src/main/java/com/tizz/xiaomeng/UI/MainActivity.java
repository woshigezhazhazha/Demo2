package com.tizz.xiaomeng.UI;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.tizz.xiaomeng.App;
import com.tizz.xiaomeng.R;
import com.tizz.xiaomeng.UI.fragment.fragment_discovery;
import com.tizz.xiaomeng.UI.fragment.fragment_me;
import com.tizz.xiaomeng.UI.fragment.fragment_message;
import com.tizz.xiaomeng.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private Fragment[] fragments;
    private ImageView[] imageButtons;
    private TextView[] textViews;
    private int index;
    private int currentTabIndex;
    private TitlePopup titlePopup;
    private ImageView img_add,img_search;
    private fragment_message homeFragment;
    private fragment_discovery findFragment;
    private fragment_me meFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EMOptions options =new EMOptions();
        options.setAcceptInvitationAlways(false);
        EMClient.getInstance().init(this,options);
        EMClient.getInstance().setDebugMode(true);

        initViews();
        initTabViews();
        setOnListener();
        findViewById();
        initPopWindow();


    }

    public void initViews(){

    }

    public void initTabViews(){

        homeFragment=new fragment_message();
        findFragment=new fragment_discovery();
        meFragment=new fragment_me();
        img_add=(ImageView)findViewById(R.id.img_add);
        img_search=(ImageView)findViewById(R.id.img_search);
        fragments=new Fragment[]{homeFragment,findFragment,meFragment};
        imageButtons=new ImageView[3];
        imageButtons[0]=(ImageView)findViewById(R.id.ib_xiaomeng);
        imageButtons[1]=(ImageView)findViewById(R.id.ib_find);
        imageButtons[2]=(ImageView)findViewById(R.id.ib_me);

        imageButtons[0].setSelected(true);

        textViews=new TextView[3];
        textViews[0]=(TextView)findViewById(R.id.tv_xiaomeng);
        textViews[1]=(TextView)findViewById(R.id.tv_find);
        textViews[2]=(TextView)findViewById(R.id.tv_me);
        textViews[0].setTextColor(0xFF45C01A);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,homeFragment)
                .add(R.id.fragment_container,findFragment)
                .add(R.id.fragment_container,meFragment)
                .hide(findFragment).hide(meFragment).show(homeFragment).commit();
    }

    public void onTabClicked(View v){
        switch(v.getId()){
            case R.id.re_xiaomeng:
                index=0;
                if(homeFragment!=null){
                    homeFragment.refresh();
                }
                break;
            case R.id.re_find:
                index=2;
                break;
            case R.id.re_me:
                index=3;
                break;

        }
        if(currentTabIndex!=index){
            FragmentTransaction trx=getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if(!fragments[index].isAdded()){
                trx.add(R.id.fragment_container,fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        imageButtons[currentTabIndex].setSelected(false);
        textViews[currentTabIndex].setTextColor(0xFF999999);
        textViews[index].setTextColor(0xFF45C01A);
        currentTabIndex=index;
    }

    private AdapterView.OnItemClickListener onItemClick=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(ActionItem item,int position) {
            switch(position){
                case 0:
                    Utils.start_Activity(MainActivity.this,AddgroupChatActivity.class);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onResuse(){
        super.onResume();
    }

    @Override
    protected void onDestory(){
        super.onDestroy();
    }

    public void setOnListener(){
        img_add.setOnClickListener(this);
        img_search.setOnClickListener(this);
    }

    public void findViewById(){

    }





    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
                    EMChatManager.getInstance().logout();
                    App.getInstance2().exit();
                    finish();
                    overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    public void onClick(View v){

    }

}
