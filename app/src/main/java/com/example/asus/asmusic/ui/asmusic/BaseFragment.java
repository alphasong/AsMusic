package com.example.asus.asmusic.ui.asmusic;

import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.example.asus.asmusic.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by ASUS on 2017/8/4.
 */

public class BaseFragment extends Fragment {

    public void showSnackBar(String toast){
        Snackbar.make(getActivity().getWindow().getDecorView(),toast,Snackbar.LENGTH_SHORT).show();
    }

    public void showToast(int toastRes) {
        Toast.makeText(getActivity(),getString(toastRes),Toast.LENGTH_SHORT).show();
    }

    /*
    public boolean gotoSongPlayerActivity() {
        if (MusicPlayerManager.get().getPlayingSong() == null) {
            showToast(R.string.music_playing_none);
            return false;
        }
        PlayingActivity.open(getActivity());
        return true;
    }
    */

}
