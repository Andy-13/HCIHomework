package com.example.yang.hcihomework;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lee Sima on 2016/11/21.
 */
public class DetailDialog extends DialogFragment implements  View.OnClickListener{

    private ImageButton leftButton = null;
    private ImageButton rightButton = null;
    private ImageView time_iv = null;
    private TextView star_tv = null;

    private int currrentPage;

    private static final int PAGE_DAY = 0;
    private static final int PAGE_NIGHT = 1;
    private static final int PAGE_BLUETOOTH = 2;
    private static final int STATUS_COUNT = 3;

    private static final int SEX_BOY = 1;
    private static final int SEX_GIRL = 2;

    private ArrayList<Integer> STARS = null;
    private int SEX;

    public static DetailDialog newInstance(ArrayList<Integer> stars,int sex){
        DetailDialog mDialog = new DetailDialog();
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("STAR",stars);
        bundle.putInt("SEX",sex);
        mDialog.setArguments(bundle);
        return mDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        STARS = new ArrayList<>(STATUS_COUNT);
        STARS = bundle.getIntegerArrayList("STAR");
        SEX = bundle.getInt("SEX");
        currrentPage = PAGE_DAY;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = null;
        if(SEX == SEX_BOY ){
            view = inflater.inflate(R.layout.detail,container);
        }
        else if(SEX == SEX_GIRL){
            view = inflater.inflate(R.layout.girl_detail,container);
        }
        else{
            Log.d("HHH","renyaoa!");
            view = inflater.inflate(R.layout.detail,container);
        }

        leftButton = (ImageButton)view.findViewById(R.id.left_button);
        rightButton = (ImageButton)view.findViewById(R.id.right_button);
        time_iv = (ImageView)view.findViewById(R.id.mid_pic);
        star_tv = (TextView)view.findViewById(R.id.star_num);
        //初始化星星数量

        star_tv.setText( String.valueOf(STARS.get(currrentPage)));

        //初始化
        leftButton.setVisibility(View.GONE);
        rightButton.setVisibility(View.VISIBLE);

        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);

        //设置圆角关键
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getDialog().getWindow().setWindowAnimations(R.style.AnimationDialog);
        return view;
    }

    //用于设置dialog大小
    @Override
    public void onStart() {
        super.onStart();
        Dialog mDialog = getDialog();
        if(mDialog!=null){
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            mDialog.getWindow().setLayout((int)(dm.widthPixels * 0.73),(int)(dm.heightPixels*0.48));
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.left_button:
                showLeft();
                break;
            case R.id.right_button:
                showRight();
                break;
            default:
                break;
        }

    }

    //日历左按钮按下执行事件
    protected void showLeft(){
        if(currrentPage == PAGE_DAY){
            return;
        }
        else if(currrentPage == PAGE_NIGHT){
            leftButton.setVisibility(View.GONE);
            rightButton.setVisibility(View.VISIBLE);

            time_iv.setImageDrawable(getResources().getDrawable(R.mipmap.daytime_reward));
            currrentPage = PAGE_DAY;
            star_tv.setText( String.valueOf(STARS.get(currrentPage)));
        }
        else if(currrentPage == PAGE_BLUETOOTH){
            leftButton.setVisibility(View.VISIBLE);
            rightButton.setVisibility(View.VISIBLE);

            time_iv.setImageDrawable(getResources().getDrawable(R.mipmap.night_reward));
            currrentPage = PAGE_NIGHT;
            star_tv.setText( String.valueOf(STARS.get(currrentPage)));
        }
    }

    //日历右按钮按下执行事件
    protected void showRight(){
        if(currrentPage == PAGE_DAY){
            leftButton.setVisibility(View.VISIBLE);
            rightButton.setVisibility(View.VISIBLE);

            time_iv.setImageDrawable(getResources().getDrawable(R.mipmap.night_reward));
            currrentPage = PAGE_NIGHT;
            star_tv.setText( String.valueOf(STARS.get(currrentPage)));
        }
        else if(currrentPage == PAGE_NIGHT){
            leftButton.setVisibility(View.VISIBLE);
            rightButton.setVisibility(View.GONE);

            time_iv.setImageDrawable(getResources().getDrawable(R.mipmap.bluetooth_reward));
            currrentPage = PAGE_BLUETOOTH;
            star_tv.setText( String.valueOf(STARS.get(currrentPage)));
        }
        else if(currrentPage == PAGE_BLUETOOTH){
            return;
        }

    }
}
