package com.example.yang.hcihomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


    public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton leftButton = null;
    ImageButton rightButton = null;
    private ImageView time_iv = null;
    private int currrentPage;

    private static final int PAGE_DAY = 0;
    private static final int PAGE_NIGHT = 1;
    private static final int PAGE_BLUETOOTH = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
       /* requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏*/
        currrentPage = PAGE_DAY;


        leftButton = (ImageButton)findViewById(R.id.left_button);
        rightButton = (ImageButton)findViewById(R.id.right_button);
        time_iv = (ImageView)findViewById(R.id.mid_pic) ;

        //初始化
        leftButton.setVisibility(View.GONE);
        rightButton.setVisibility(View.VISIBLE);

        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);

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
        }
        else if(currrentPage == PAGE_BLUETOOTH){
            leftButton.setVisibility(View.VISIBLE);
            rightButton.setVisibility(View.VISIBLE);
            time_iv.setImageDrawable(getResources().getDrawable(R.mipmap.night_reward));
            currrentPage = PAGE_NIGHT;
        }
    }

    //日历右按钮按下执行事件
    protected void showRight(){
        if(currrentPage == PAGE_DAY){
            leftButton.setVisibility(View.VISIBLE);
            rightButton.setVisibility(View.VISIBLE);
            time_iv.setImageDrawable(getResources().getDrawable(R.mipmap.night_reward));
            currrentPage = PAGE_NIGHT;
        }
        else if(currrentPage == PAGE_NIGHT){
            leftButton.setVisibility(View.VISIBLE);
            rightButton.setVisibility(View.GONE);
            time_iv.setImageDrawable(getResources().getDrawable(R.mipmap.bluetooth_reward));
            currrentPage = PAGE_BLUETOOTH;
        }
        else if(currrentPage == PAGE_BLUETOOTH){
            return;
        }

    }
}
