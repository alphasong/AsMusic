package com.example.asus.asmusic.ui.local;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.asmusic.MyApplication;
import com.example.asus.asmusic.R;
import com.example.asus.asmusic.data.Album;
import com.example.asus.asmusic.model.LocalIView;
import com.example.asus.asmusic.ui.adapter.LocalAlbumAdapter;
import com.example.asus.asmusic.ui.cnmusic.BaseFragment;
import com.example.asus.asmusic.ui.presenter.LocalLibraryPresenter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocalAlbumFragment extends BaseFragment implements LocalIView.LocalAlbum{
    public static final String TITLE = MyApplication.getInstance().getString(R.string.local_album_fragment_title);


    private RecyclerView recyclerView;
    private LocalAlbumAdapter albumAdapter;
    private LocalLibraryPresenter libraryPresenter;

    public static LocalAlbumFragment newInstance() {
        LocalAlbumFragment fragment = new LocalAlbumFragment();
        return fragment;
    }

    public LocalAlbumFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        libraryPresenter = new LocalLibraryPresenter(this,getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View parentView = inflater.inflate(R.layout.fragment_local_music, container, false);
        initRecyclerView(parentView);
        return parentView;
    }

    private void initRecyclerView(View rootView) {
        albumAdapter = new LocalAlbumAdapter(getActivity());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(albumAdapter);

        libraryPresenter.requestAlbum();

    }

    @Override
    public void getLocalAlbum(List<Album> alba) {
        albumAdapter.setData(alba);
    }
}
