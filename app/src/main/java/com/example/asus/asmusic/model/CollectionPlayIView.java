package com.example.asus.asmusic.model;

import android.graphics.Bitmap;
import android.text.Spanned;

import com.example.asus.asmusic.data.Song;

import java.util.List;

/**
 * @desciption: 收藏夹歌曲列表的view接口
 */

public interface CollectionPlayIView {

    void collectionDetail(int collectionId, Spanned title, Spanned description);

    void collectionCover(Bitmap cover);

    void getSongs(List<Song> songs);

    void fail(Throwable throwable);
}
