package com.example.asus.asmusic.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.example.asus.asmusic.R;
import com.example.asus.asmusic.BaseActivity;
import com.example.asus.asmusic.ui.asmusic.MainActivity;

public class WelcomeActivity extends BaseActivity {
    private ImageView mLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mLogo = (ImageView)findViewById(R.id.logo);
        startAnimation();
    }

    /***
     * 启动页动画
     */
    private void startAnimation() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mLogo,"scaleX",0f,1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mLogo, "scaleX", 0f, 1f);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(mLogo,"alpha",0f,1f);
        AnimatorSet animaltorSet = new AnimatorSet();
        animaltorSet.play(alphaAnimation).with(scaleX).with(scaleY);
        animaltorSet.setDuration(15000);
        animaltorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animaltorSet.start();

        animaltorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //判断是否登录

                //跳转界面
                startToActivity(MainActivity.class);

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            return true;
        }
        return false;
    }
}
