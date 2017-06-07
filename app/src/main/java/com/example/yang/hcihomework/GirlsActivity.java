package com.example.yang.hcihomework;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yang on 2016/11/10.
 */
public class GirlsActivity extends AppCompatActivity {
    private ImageView mHeadIv;
    private PopupWindow mPopWindow;

    private static final int SEX_BOY = 1;
    private static final int SEX_GIRL = 2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏

        setContentView(R.layout.girl_layout);

        mHeadIv = (ImageView)findViewById(R.id.girl_head);
        mHeadIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow();
            }
        });

        //第一排
        setButton(R.id.button1_1,R.id.button1_1_iv,R.id.button1_1_tv,0,0,0);
        setButton(R.id.button1_2,R.id.button1_2_iv,R.id.button1_2_tv,2,4,1);
        setButton(R.id.button1_3,R.id.button1_3_iv,R.id.button1_3_tv,0,0,0);
        setButton(R.id.button1_4,R.id.button1_4_iv,R.id.button1_4_tv,0,0,0);
        setButton(R.id.button1_5,R.id.button1_5_iv,R.id.button1_5_tv,2,1,3);
        setButton(R.id.button1_6,R.id.button1_6_iv,R.id.button1_6_tv,0,0,0);
        setButton(R.id.button1_7,R.id.button1_7_iv,R.id.button1_7_tv,1,0,4);

        // 第二排
        setButton(R.id.button2_1,R.id.button2_1_iv,R.id.button2_1_tv,2,1,6);
        setButton(R.id.button2_2,R.id.button2_2_iv,R.id.button2_2_tv,0,0,0);
        setButton(R.id.button2_3,R.id.button2_3_iv,R.id.button2_3_tv,0,0,0);
        setButton(R.id.button2_4,R.id.button2_4_iv,R.id.button2_4_tv,0,0,0);
        setButton(R.id.button2_5,R.id.button2_5_iv,R.id.button2_5_tv,0,0,0);
        setButton(R.id.button2_6,R.id.button2_6_iv,R.id.button2_6_tv,2,2,3);
        setButton(R.id.button2_7,R.id.button2_7_iv,R.id.button2_7_tv,0,0,0);

        //第三排
        setButton(R.id.button3_1,R.id.button3_1_iv,R.id.button3_1_tv,0,0,0);
        setButton(R.id.button3_2,R.id.button3_2_iv,R.id.button3_2_tv,0,0,0);
        setButton(R.id.button3_3,R.id.button3_3_iv,R.id.button3_3_tv,0,0,0);
        setButton(R.id.button3_4,R.id.button3_4_iv,R.id.button3_4_tv,6,6,6);
        setButton(R.id.button3_5,R.id.button3_5_iv,R.id.button3_5_tv,0,0,0);
        setButton(R.id.button3_6,R.id.button3_6_iv,R.id.button3_6_tv,2,3,3);
        setButton(R.id.button3_7,R.id.button3_7_iv,R.id.button3_7_tv,0,0,0);


    }

    //nobody knows
    protected void setButton(int button_id, int imageView_id, int textView_id,
                             final int day_star, final int night_star, final int bluetooth_star){

        RelativeLayout buttonX = (RelativeLayout) findViewById(button_id);
        ImageView starX = (ImageView)findViewById(imageView_id);
        TextView textX = (TextView)findViewById(textView_id);
        int sum = day_star + night_star + bluetooth_star;

        if(sum != 0){
            starX.setVisibility(View.VISIBLE);
            textX.setText(String.valueOf(sum));
        }

        buttonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> stars = new ArrayList<>(3);
                stars.add(day_star);
                stars.add(night_star);
                stars.add(bluetooth_star);
                showDetailDialog(view,stars);
            }
        });

    }

    public void showDetailDialog(View view,ArrayList<Integer>stars){

        DetailDialog mDetailDialog = DetailDialog.newInstance(stars,SEX_GIRL);
        mDetailDialog.show(getFragmentManager(),"DetailDialog");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mPopWindow != null) {
            mPopWindow.dismiss();
        }
    }

    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(GirlsActivity.this).inflate(R.layout.girl_popuplayout, null);
        mPopWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setAnimationStyle(R.style.AnimationPreview);


        ImageView close = (ImageView) contentView.findViewById(R.id.close);
        MLRoundedImageView girl_head = (MLRoundedImageView) contentView.findViewById(R.id.boy_heads);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        girl_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GirlsActivity.this, MainActivity.class);
                startActivity(i);
                finish();//销毁当前活动
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(GirlsActivity.this).inflate(R.layout.activity_main, null);
        mPopWindow.showAtLocation(rootview, Gravity.TOP, 0, 0);

    }
}
