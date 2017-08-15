package com.example.asus.asmusic.ui.album;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.asmusic.R;
import com.example.asus.asmusic.ui.adapter.TabAlbumAdapter;
import com.example.asus.asmusic.ui.asmusic.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static butterknife.ButterKnife.bind;

/**
 * A simple {@link Fragment} subclass.
 * 唱片
 */
public class AlbumFragment extends Fragment {


    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    Unbinder unbinder;
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

        unbinder = ButterKnife.bind(this,inflate);

        mViewpager.setAdapter(mLocalMusicAdapter);

        mViewpager.setCurrentItem(0);

        mViewpager.setOffscreenPageLimit(mLocalMusicAdapter.getCount());

        mTabLayout.setupWithViewPager(mViewpager);
        return inflate;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
