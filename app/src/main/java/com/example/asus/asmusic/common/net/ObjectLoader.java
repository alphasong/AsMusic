package com.example.asus.asmusic.common.net;

import rx.Observable;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.asus.asmusic.R.id.a;


/**
 * Created by ASUS on 2017/8/11.
 */

class ObjectLoader {
    /***
     *
     */
    protected  <T> Observable<T> observe(Observable<T> observable){
        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
