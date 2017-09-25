package com.example.asus.asmusic.service;

import android.support.v4.media.session.PlaybackStateCompat;

import com.example.asus.asmusic.data.Song;


/**
 * @desciption: 歌曲变化监听器
 */
public interface OnSongChangedListener {
    void onSongChanged(Song song);

    void onPlayBackStateChanged(PlaybackStateCompat state);
}
