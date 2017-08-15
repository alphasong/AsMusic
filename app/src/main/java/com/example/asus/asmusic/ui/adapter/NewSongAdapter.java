package com.example.asus.asmusic.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.asmusic.R;
import com.example.asus.asmusic.data.SongListInfo;

import java.util.ArrayList;
import java.util.List;

import static com.example.asus.asmusic.R.drawable.c;

/**
 * Created by ASUS on 2017/8/10.
 */

public class NewSongAdapter extends RecyclerView.Adapter<NewSongAdapter.MyViewHolder>{
    private Context context;
    public static final String TAG = "RecyclerViewVideoAdapter";
    private List<SongListInfo.ContentBean> resultData = new ArrayList<>();

    public NewSongAdapter(Context context) {
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_newsong,
                parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return resultData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView avatar;
        TextView title;
        TextView subTitle;
        ImageView playAll;
        TextView heardNum;

        public MyViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.songsAvatar);
            playAll = (ImageView) itemView.findViewById(R.id.play_allsongs);
            title = (TextView) itemView.findViewById(R.id.songs_title);
            subTitle = (TextView) itemView.findViewById(R.id.songs_subtitle);
            heardNum = (TextView) itemView.findViewById(R.id.heard_number);
        }
    }

        public interface OnItemClickLitener {
            void onItemClick(View view, int position,String listid);
        }

        private OnItemClickLitener mOnItemClickLitener;

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }
}
