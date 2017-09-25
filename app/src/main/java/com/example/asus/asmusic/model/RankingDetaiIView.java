package com.example.asus.asmusic.model;

import com.example.asus.asmusic.data.BaseBean;

/**
 * Created by hongkl on 17/6/30.
 */
public interface RankingDetaiIView extends BaseIView{

    void loadMusicDetailData(BaseBean baseBean);
    void loadFail();


}
