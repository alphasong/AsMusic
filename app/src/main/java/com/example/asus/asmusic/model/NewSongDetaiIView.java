package com.example.asus.asmusic.model;

import com.example.asus.asmusic.data.SongListInsideInfo;

/**
 * Created by hongkl on 17/6/30.
 */
public interface NewSongDetaiIView extends BaseIView{

    void loadMusicDetailData(SongListInsideInfo baseBean);
    void loadFail(Throwable e);


}
