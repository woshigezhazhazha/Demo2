package com.tizz.xiaomeng.UI.Meng;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.tizz.xiaomeng.R;

public class tiezi_fragment extends Fragment implements View.OnClickListener{

    private View layout;
    private Activity activity;
    private FloatingActionButton button;
    private String[] data={"Apple","Banana","Orange","Watermelon","Pear","Grape",
            "Pineapple","Strawberry","Cherry","Mango","Potato","Tomato","Water",
            "Man","Women","Chinese","Japanese","Child","Beauty","Food","vegetable",
            "Snake","school","class","university","country","nation","you can do it!"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        if (layout==null){
            activity=this.getActivity();
            layout=activity.getLayoutInflater().inflate(R.layout.tiezi_fragment,null);
            ListView list=(ListView)layout.findViewById(R.id.lv_tiezi);
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(activity,android.R.layout.simple_list_item_1,
                    data);
            list.setAdapter(adapter);
            button=(FloatingActionButton)layout.findViewById(R.id.fab_button);
            button.attachToListView(list);

            setOnListener();
        }else{
            ViewGroup parent=(ViewGroup)layout.getParent();
            if (parent!=null){
                parent.removeView(parent);
            }
        }
        return layout;
    }

    public void setOnListener(){
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.fab_button:
                Toast toast=Toast.makeText(getActivity(),"大哥，省省吧",Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
    }

}
