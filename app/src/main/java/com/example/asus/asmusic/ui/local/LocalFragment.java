package com.example.asus.asmusic.ui.local;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.asmusic.R;
import com.example.asus.asmusic.common.utils.RxBus;
import com.example.asus.asmusic.data.CollectionBean;
import com.example.asus.asmusic.model.event.CollectionUpdateEvent;
import com.example.asus.asmusic.ui.adapter.CollectionAdapter;
import com.example.asus.asmusic.ui.adapter.OnItemClickListener;
import com.example.asus.asmusic.ui.cnmusic.DownLoadActivity;
import com.example.asus.asmusic.ui.cnmusic.LocalMusicActivity;
import com.example.asus.asmusic.ui.cnmusic.RecentPlayListActivity;
import com.example.asus.asmusic.ui.collection.CollectionCreateActivity;
import com.example.asus.asmusic.ui.collection.CollectionPlayActivity;
import com.example.asus.asmusic.ui.collection.PopupFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocalFragment extends Fragment {


    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private CollectionAdapter collectionAdapter;
    private CompositeSubscription allSubscription = new CompositeSubscription();

    public static LocalFragment newInstance() {
        LocalFragment fragment = new LocalFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allSubscription.add(RxBus.getDefault()
                .toObservable(CollectionUpdateEvent.class).subscribe(new Action1<CollectionUpdateEvent>() {
                    @Override
                    public void call(CollectionUpdateEvent event) {
                        onEvent(event);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    }
                }));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_local, container, false);
        ButterKnife.bind(this, inflate);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        collectionAdapter = new CollectionAdapter(getActivity());

        setHeaderView(recyclerView);
//        setFooterView(recyclerView);

        recyclerView.setAdapter(collectionAdapter);

        collectionAdapter.setItemClickListener(new OnItemClickListener<CollectionBean>() {
            @Override
            public void onItemClick(CollectionBean item, int position) {
                CollectionPlayActivity.open(getActivity(), item);
            }

            @Override
            public void onItemSettingClick(View v, CollectionBean item, int position) {
                PopupFragment popupFragment = PopupFragment.newInstance(position, item, collectionAdapter);
                popupFragment.show(getFragmentManager(), "");
            }
        });
        return inflate;
    }
    /***
     * 为recycleview添加尾部
     *
     * @param recyclerView
     */
    private void setFooterView(RecyclerView recyclerView) {
//        View footer = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main_playlist_first_item, recyclerView, false);
//        collectionAdapter.setHeaderView(footer);
    }


    /***
     * 为recycleview添加头部
     *
     * @param recyclerView
     */
    private void setHeaderView(RecyclerView recyclerView) {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main_playlist_first_item, recyclerView, false);
        collectionAdapter.setHeaderView(header);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.recently_layout
            , R.id.local_layout
            , R.id.download_layout
            , R.id.artist_layout
            , R.id.add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recently_layout:
                RecentPlayListActivity.open(getActivity());
                break;
            case R.id.local_layout:
                LocalMusicActivity.open(getActivity());
                break;
            case R.id.download_layout:
                DownLoadActivity.open(getActivity());
                break;
            case R.id.artist_layout:
                LocalMusicActivity.open(getActivity());
            case R.id.add:
                CollectionCreateActivity.open(getActivity());
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!allSubscription.isUnsubscribed()) {
            allSubscription.unsubscribe();
        }
    }

    public void onEvent(CollectionUpdateEvent event) {
        collectionAdapter.update();
    }

}
