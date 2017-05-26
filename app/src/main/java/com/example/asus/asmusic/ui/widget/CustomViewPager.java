package com.example.asus.asmusic.ui.widget;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ASUS on 2017/5/10.
 */

public class CustomViewPager extends ViewPager {
    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    PointF mPointF = new PointF();
    OnSingleTouchListener mOnSingleTouchListener;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 获取按下坐标
                mPointF.x = ev.getX();
                mPointF.y = ev.getY();
                if (this.getChildCount() > 1){
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (this.getChildCount() > 1){
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (PointF.length(ev.getX() - mPointF.x,ev.getY() - mPointF.y) < (float)5.0){
                    onSingleTouch(this);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void onSingleTouch(View view) {
        if (mOnSingleTouchListener != null){
            mOnSingleTouchListener.onSingleTouch();
        }
    }

    public interface OnSingleTouchListener{
        void onSingleTouch();
    }

    public void setOnSingleTouchListener(OnSingleTouchListener onSingleTouchListener){
        this.mOnSingleTouchListener = onSingleTouchListener;
    }
}
