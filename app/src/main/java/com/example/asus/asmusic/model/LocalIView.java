package com.example.asus.asmusic.model;


import com.example.asus.asmusic.data.Album;
import com.example.asus.asmusic.data.Artist;
import com.example.asus.asmusic.data.Song;

import java.util.List;

/**
 * 本地视图的接口
 */
public interface LocalIView {



    interface LocalMusic{
        void getLocalMusic(List<Song> songs);
    }

    interface LocalAlbum{
        void getLocalAlbum(List<Album> alba);
    }

    interface LocalArtist{
        void getLocalArtist(List<Artist> artists);
    }

}
