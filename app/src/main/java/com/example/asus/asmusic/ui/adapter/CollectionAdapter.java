package com.example.asus.asmusic.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.asmusic.R;
import com.example.asus.asmusic.data.CollectionBean;
import com.example.asus.asmusic.db.CollectionManager;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @desciption: 收藏夹适配器
 */

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder> {
    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的

    private List<CollectionBean> collectionList;
    private Context context;
    private OnItemClickListener<CollectionBean> itemClickListener;
    private boolean inPopupMenu;
    //HeaderView, FooterView
    private View mHeaderView;
    private View mFooterView;
/*    MaterialDialog dialog = new MaterialDialog.Builder(this)
            .title(R.string.collection_dialog_selection_title)
            .adapter(new CollectionAdapter(this), new LinearLayoutManager(this))
            .build();
    dialog.show();*/

    public CollectionAdapter(Context context) {
        this.context = context;
        inPopupMenu = false;
        update();
    }

    public CollectionAdapter(Context context, boolean inPopupMenu) {
        this.context = context;
        this.inPopupMenu = inPopupMenu;
        update();
    }

    //HeaderView和FooterView的get和set函数
    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }


    public void update() {
        collectionList = CollectionManager.getInstance().getCollectionList();
        for (CollectionBean bean : collectionList) {
            if (bean.getId() == -1) {
                collectionList.remove(bean);
            }
        }
        collectionList.add(createDefault());
        notifyDataSetChanged();
    }

    public void deleteCollection(CollectionBean bean) {
        if (collectionList.contains(bean)) {
            collectionList.remove(bean);
        }
        notifyDataSetChanged();
    }

    /**
     * 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    *
     */
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }


    private CollectionBean createDefault() {
        CollectionBean bean = new CollectionBean();
        bean.setId(-1);
        return bean;
    }

    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new CollectionViewHolder(mHeaderView);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_collection_listitem, parent, false);
        return new CollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CollectionViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_NORMAL) {
            if (holder instanceof CollectionViewHolder) {
                final CollectionBean bean = collectionList.get(position);
                //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                holder.setting.setVisibility(View.VISIBLE);
                holder.count.setVisibility(View.VISIBLE);
                holder.title.setText(bean.getTitle());
                String count = String.format(context.getString(R.string.song), bean.getCount());
                holder.count.setText(count);
                Glide.with(context)
                        .load(bean.getCoverUrl())
                        .placeholder(R.drawable.a8y)
                        .into(holder.cover);
                holder.collectLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (itemClickListener != null) {
                            itemClickListener.onItemClick(bean, holder.getAdapterPosition());
                        }
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                            return;
                        }
                    }
                });
                if (inPopupMenu) {
                    holder.setting.setVisibility(View.GONE);
                }
                holder.setting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (itemClickListener != null) {
                            itemClickListener.onItemSettingClick(holder.setting, bean, holder.getAdapterPosition());
                        }
                    }
                });
                return;
            }
        }else if(getItemViewType(position) == TYPE_HEADER){
            final CollectionBean bean = collectionList.get(0);
            String count = String.format(context.getString(R.string.song), bean.getCount());
            holder.countDefault.setText(count);
            holder.titleDefault.setText(R.string.myfavor);
            holder.menuDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemSettingClick(holder.menuDefault, bean, holder.getAdapterPosition());

                    }
                }
            });

            holder.myfavor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(bean, holder.getAdapterPosition());
                    }
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        return;
                    }
                }
            });
            return;
        }
    }

    @Override
    public int getItemCount() {
        if(mHeaderView != null) {
            return collectionList.size() ;
        }else
            return collectionList.size();
    }

    public OnItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public class CollectionViewHolder extends RecyclerView.ViewHolder {

        private View collectLayout;
        private ImageView cover;
        private TextView title, count;
        private ImageView setting;
        private SimpleDraweeView mCoverDefault;
        private TextView titleDefault,countDefault;
        private ImageView menuDefault;
        private View myfavor;

        public CollectionViewHolder(View itemView) {
            super(itemView);

            if (itemView == mHeaderView){
                mCoverDefault = (SimpleDraweeView) itemView.findViewById(R.id.fragment_main_playlist_item_img);
                titleDefault = (TextView) itemView.findViewById(R.id.fragment_main_playlist_item_title);
                countDefault = (TextView) itemView.findViewById(R.id.fragment_main_playlist_item_count);
                menuDefault = (ImageView) itemView.findViewById(R.id.fragment_main_playlist_item_menu);
                myfavor = itemView.findViewById(R.id.collection_myfavor);
                return;
            }

            collectLayout = itemView.findViewById(R.id.collection_item);
            cover = (ImageView) itemView.findViewById(R.id.collection_cover);
            count = (TextView) itemView.findViewById(R.id.collection_count);
            title = (TextView) itemView.findViewById(R.id.collection_title);
            setting = (ImageView) itemView.findViewById(R.id.collection_setting);
        }
    }
}
