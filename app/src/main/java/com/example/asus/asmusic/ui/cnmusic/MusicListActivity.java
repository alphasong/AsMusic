package com.example.asus.asmusic.ui.cnmusic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.asus.asmusic.BaseActivity;
import com.example.asus.asmusic.R;

/**
 * @desciption: 专辑，电台列表界面
 */
public class MusicListActivity extends BaseActivity {

    public static void open(Context context, String musicType) {
        Intent intent = new Intent(context, MusicListActivity.class);
        intent.putExtra("MusicType", musicType);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_more);
    }


}
