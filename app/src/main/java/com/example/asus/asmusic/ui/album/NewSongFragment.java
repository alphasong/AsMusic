package com.example.asus.asmusic.ui.album;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.asmusic.MusicApplication;
import com.example.asus.asmusic.R;
import com.example.asus.asmusic.common.net.ApiLoader;
import com.example.asus.asmusic.ui.adapter.NewSongAdapter;
import com.example.asus.asmusic.ui.asmusic.BaseFragment;

/**
 * Created by ASUS on 2017/8/5.
 */

public class NewSongFragment extends Fragment {
    public static final String TITLE = MusicApplication.getInstance().getString(R.string.album);
    private RecyclerView mRecyclerView;
    private NewSongAdapter mAdapter;
    private ApiLoader mMovieLoader;

    public static NewSongFragment newInstance(){
        NewSongFragment fragment = new NewSongFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View parentView = inflater.inflate(R.layout.fragment_new_song,container,false);
        initData(parentView);
        getData();
        return parentView;
    }

    private void getData() {
    }

    private void initData(View parentView) {
        mRecyclerView = (RecyclerView) parentView.findViewById(R.id.recyclerView_newSong);
        mAdapter = new NewSongAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

    }
}
