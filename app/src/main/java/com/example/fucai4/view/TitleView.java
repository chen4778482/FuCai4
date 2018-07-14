package com.example.fucai4.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fucai4.R;


/**
 * Created by ${小强同学} on 2017/11/23
 */

public class TitleView extends LinearLayout implements View.OnClickListener {

    private ImageButton head_break;
    private TextView tv_title;
    private TextView tv_right;
    private RelativeLayout relatve;


    private OnRightButClickListener onRightButClickListener;//右键
    private OnImageButClickListener onImageButClickListener;//左键

    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_title, this);
        initViews();
        initEvents();


    }

    private void initViews() {
        head_break = (ImageButton) findViewById(R.id.head_break);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_right = (TextView) findViewById(R.id.tv_right);
        relatve = (RelativeLayout) findViewById(R.id.relatve);

    }

    private void initEvents() {
        head_break.setOnClickListener(this);
        tv_right.setOnClickListener(this);
    }


    /**
     *  方法目录
     * */

    public void setRightVisibility( ){
        if (tv_right!=null){
            tv_right.setVisibility(VISIBLE);
        }

    }
    public void setTitleTextView(String nmg ){
        if (tv_title!=null){
            tv_title.setText(nmg);
        }
    }
    public void setRightTextView(String nmg ){
        if (tv_right!=null){
            setRightVisibility();
            tv_right.setText(nmg);
        }

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_break:
                onImageButClickListener.onLeftClick(view);
                break;
            case R.id.tv_right:
                onRightButClickListener.onRightClick(view);
                break;
        }
    }

    public interface OnRightButClickListener {
        void onRightClick(View v);
    }

    public void setOnRightButClickListener(OnRightButClickListener onSeachClickListener) {
        this.onRightButClickListener = onSeachClickListener;
    }

    public interface OnImageButClickListener {
        void onLeftClick(View v);
    }

    public void setOnImageButClickListener(OnImageButClickListener onSeachClickListener) {
        this.onImageButClickListener = onSeachClickListener;
    }
}
