package com.example.asus.asmusic.model;

import com.example.asus.asmusic.data.Album;

public interface PlayListIView {
    void getAlbum(boolean moeAlbum, Album album);

    void fail(String msg);
}