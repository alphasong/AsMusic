package com.example.asus.asmusic.ui.asmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bilibili.magicasakura.widgets.TintToolbar;
import com.example.asus.asmusic.R;
import com.example.asus.asmusic.ui.Friends.FriendsFragment;
import com.example.asus.asmusic.ui.adapter.MenuItemAdapter;
import com.example.asus.asmusic.ui.album.AlbumFragment;
import com.example.asus.asmusic.ui.radio.RadioFragment;
import com.example.asus.asmusic.ui.widget.CustomViewPager;
import com.lapism.searchview.SearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends SearchActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String TAG = "MainActivity";
    @Bind(R.id.bar_net)
    ImageView barNet;
    @Bind(R.id.bar_music)
    ImageView barMusic;
    @Bind(R.id.bar_friends)
    ImageView barFriends;
    @Bind(R.id.bar_search)
    ImageView barSearch;
    @Bind(R.id.search_view_item)
    SearchView searchView;
    @Bind(R.id.toolbar)
    TintToolbar toolbar;
    @Bind(R.id.main_viewpager)
    CustomViewPager mainViewpager;
    @Bind(R.id.bottom_container)
    FrameLayout bottomContainer;
    @Bind(R.id.a)
    RelativeLayout a;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.id_lv_left_menu)
    ListView idLvLeftMenu;


    private ArrayList<ImageView> tabs = new ArrayList<>();
    private ActionBar mActionBar;
    private long time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getWindow().setBackgroundDrawableResource(R.color.background_material_light_1);

        setToolBar();
        setCustomViewPager();
        setDrawerLayout();
    }


    protected void setToolBar() {
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mActionBar.setTitle("");
    }

    /***
     * 设置自定义的 viewpager
     */
    private void setCustomViewPager() {
        // 添加 tab 标签
        tabs.add(barNet);
        tabs.add(barMusic);
        tabs.add(barFriends);


        final AlbumFragment albumFragment = new AlbumFragment();
        final RadioFragment tabNetPagerFragment = new RadioFragment();
        final FriendsFragment friendsFragment = new FriendsFragment();

        CustomViewPagerAdapter myPagerAdapter = new CustomViewPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.addFragment(albumFragment);
        myPagerAdapter.addFragment(tabNetPagerFragment);
        myPagerAdapter.addFragment(friendsFragment);

        mainViewpager.setAdapter(myPagerAdapter);
        mainViewpager.setCurrentItem(1);
        barMusic.setSelected(true);

        mainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 切换 tab 标签
                changeTabs(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /***
     * 初始化侧滑菜单
     */
    private void setDrawerLayout() {
        LayoutInflater inflater = LayoutInflater.from(this);
        idLvLeftMenu.addHeaderView(inflater.inflate(R.layout.nav_header_main, idLvLeftMenu, false));
        idLvLeftMenu.setAdapter(new MenuItemAdapter(this));
        idLvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        drawerLayout.closeDrawers();
                        break;
                }
            }
        });
    }

    @Override

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initSearch();
    }

    private void initSearch() {
        setSearchView();
        customSearchView(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void changeTabs(int position) {
        for (int i = 0; i < tabs.size(); i++) {
            if (position == i) {
                tabs.get(i).setSelected(true);
            } else {
                tabs.get(i).setSelected(false);
            }
        }
    }

    @OnClick({R.id.bar_net, R.id.bar_music, R.id.bar_friends, R.id.bar_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bar_net:
                mainViewpager.setCurrentItem(0);
                break;
            case R.id.bar_music:
                mainViewpager.setCurrentItem(1);
                break;
            case R.id.bar_friends:
                mainViewpager.setCurrentItem(2);
                break;
            case R.id.bar_search:
                searchView.open(true);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    @Override
    public void onClick(View view) {

    }

    /***
     * customViewPager 的适配器
     */
    static class CustomViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragments = new ArrayList<>();

        public CustomViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment) {
            mFragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /***
     * 双击返回桌面
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - time > 1000) {
                Toast.makeText(this, "再按一次返回桌面", Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            } else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
