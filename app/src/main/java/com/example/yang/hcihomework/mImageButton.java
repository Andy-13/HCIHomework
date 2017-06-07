package com.example.yang.hcihomework;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Lee Sima on 2016/11/15.
 */
public class mImageButton extends LinearLayout {

    private ImageView mButtonImage = null;
    private TextView mButtonText = null;

    public mImageButton(Context context, int imageResId, String text){

        super(context);
        mButtonImage = new ImageView(context);
        mButtonText = new TextView(context);
        //设置图片
        mButtonImage.setImageResource(imageResId);
        mButtonImage.setPadding(0,0,0,0);

        mButtonText.setText(text);
        mButtonText.setTextColor(0xFF000000);
        mButtonText.setPadding(0,0,0,0);

        //设置布局的属性
        this.setClickable(true);//可点击
        this.setFocusable(true);//可聚焦
        this.setBackgroundResource(android.R.drawable.btn_default);//设置布局采用的普通按钮的背景
        setOrientation(LinearLayout.VERTICAL);//垂直布局

        //按顺序添加
        this.addView(mButtonImage);
        this.addView(mButtonText);

    }

    //setImageResource
    public void setImageResouce(int resId){
        mButtonImage.setImageResource(resId);
    }

    //setText
    public void setText(String text){
        mButtonText.setText(text);
    }

    //setTextColor
    public void setTextColor(int color){
        mButtonText.setTextColor(color);
    }

}
