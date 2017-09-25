package com.example.asus.asmusic.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.asus.asmusic.ui.album.NewSongFragment;
import com.example.asus.asmusic.ui.album.RankingFragment;
import com.example.asus.asmusic.ui.album.MVFragment;

/**
 * Created by hongkl on 17/5/22.
 */
public class TabAlbumAdapter extends FragmentPagerAdapter {


    private String[] titles = new String[]{"歌单", "MV", "排行榜"};
    private NewSongFragment newSongFragment;
    private MVFragment songMenuFragment;
    private RankingFragment rankingFragment;

    public TabAlbumAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                if (newSongFragment == null) {
                    newSongFragment = newSongFragment.newInstance();
                }
                return newSongFragment;
            case 1:
                if (songMenuFragment == null) {
                    songMenuFragment = songMenuFragment.newInstance();
                }
                return songMenuFragment;
            case 2:

                if (rankingFragment == null) {
                    rankingFragment = rankingFragment.newInstance();
                }
                return rankingFragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
