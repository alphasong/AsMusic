package com.example.asus.asmusic;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.pm.ActivityInfoCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.asus.asmusic.R;
import com.example.asus.asmusic.ui.asmusic.SearchActivity;
import com.lapism.searchview.SearchAdapter;

/**
 * Created by ASUS on 2017/4/18.
 * 所有 activity 的基类
 */

public class BaseActivity extends AppCompatActivity {

    private String TAG = "BaseActivity";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //默认不能横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unbind from the service
        unbindService();
    }

    private void unbindService() {
    }

    /***
     * snackbar 的显示
     * @param view
     * @param text
     */
    public void showSnackBar(View view,@Nullable String text){
        Snackbar.make(view,text,Snackbar.LENGTH_SHORT).show();
    }
    public void showSnackBar(View view,int resID){
        Snackbar.make(view,resID,Snackbar.LENGTH_SHORT).show();
    }

    //跳转界面
    public void startToActivity(Class activity){
        Intent intent = new Intent(this,activity);
        startActivity(intent);
    }
}
