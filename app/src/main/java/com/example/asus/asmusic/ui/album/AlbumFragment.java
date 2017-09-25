package com.example.asus.asmusic.ui.album;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.asmusic.R;
import com.example.asus.asmusic.ui.adapter.TabAlbumAdapter;
import com.example.asus.asmusic.ui.cnmusic.BaseFragment;
import com.example.asus.asmusic.ui.adapter.TabAlbumAdapter;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 第三模块:唱片
 */
public class AlbumFragment extends BaseFragment {


    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    private ActionBar ab;
    private TabAlbumAdapter mLocalMusicAdapter;

    public AlbumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_album, container, false);

        ButterKnife.bind(this, inflate);


        mLocalMusicAdapter = new TabAlbumAdapter(getChildFragmentManager());

        mViewpager.setAdapter(mLocalMusicAdapter);

        mViewpager.setCurrentItem(0);

        mViewpager.setOffscreenPageLimit(mLocalMusicAdapter.getCount());

        mTabLayout.setupWithViewPager(mViewpager);
        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
