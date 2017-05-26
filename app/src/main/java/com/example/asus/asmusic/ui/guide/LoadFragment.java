package com.example.asus.asmusic.ui.guide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.asmusic.R;

/**
 * A simple {@link Fragment} subclass.
 * fragment 预加载问题处理
 * 1. 可以懒加载的 fragment
 */
public  abstract class LoadFragment extends Fragment {

    /***
     *  视图是否已经初始化
     */

    protected boolean isInitView = false;

    /***
     * 是否可以加载数据
     */

    protected boolean isLoadData = false;

    private View view;

    public LoadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(setContentView(), container, false);
        /***
         * 视图是否已经初始化
         */
        isInitView = true;
        isCanLoadData();
        return view;
    }

    private void isCanLoadData() {
        if (!isInitView) {
            return;
        }
        /**
         * 视图用户可见
         */
        if (getUserVisibleHint()) {
            lazyLoadData();
            isLoadData = true;
        } else {
            if (isLoadData) {
                stopLoad();
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    /***
     * 停止加载数据
     */
    protected void stopLoad() {

    }

    /***
     * 预加载数据,子类实现
     */
    protected abstract void lazyLoadData();

    /***
     * 设置视图
     * @return
     */
    protected abstract int setContentView();


    /***
     * 子类传递过来的view视图
     * @return
     */
    protected View getContentView() {
        return view;
    }


    protected <T extends View> T findViewById(int id) {


        return (T) getContentView().findViewById(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInitView = false;
        isLoadData = false;
    }
}