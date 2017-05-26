package com.example.asus.asmusic.ui.asmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bilibili.magicasakura.widgets.TintToolbar;
import com.example.asus.asmusic.BaseActivity;
import com.example.asus.asmusic.R;
import com.example.asus.asmusic.ui.album.AlbumFragment;
import com.example.asus.asmusic.ui.radio.RadioFragment;
import com.example.asus.asmusic.ui.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.bar_net)
    ImageView barNet;
    @InjectView(R.id.bar_music)
    ImageView barMusic;
    @InjectView(R.id.bar_friends)
    ImageView barFriends;
    @InjectView(R.id.bar_search)
    ImageView barSearch;
    @InjectView(R.id.toolbar)
    TintToolbar toolbar;
    @InjectView(R.id.main_viewpager)
    CustomViewPager mainViewpager;
    @InjectView(R.id.bottom_container)
    FrameLayout bottomContainer;
    @InjectView(R.id.a)
    RelativeLayout a;
    @InjectView(R.id.id_lv_left_menu)
    ListView idLvLeftMenu;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private ArrayList<ImageView> tabs = new ArrayList<>();
    private ActionBar mActionBar;
    private long time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        getWindow().setBackgroundDrawableResource(R.color.background_material_light_1);

        setToolBar();
        setCustomViewPager();
        setDrawerLayout();
    }

    private void setToolBar() {
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

        CustomViewPager mCustomViewPager = (CustomViewPager) findViewById(R.id.main_viewpager);

        final AlbumFragment mainFragment = new AlbumFragment();
        final RadioFragment tabNetPagerFragment = new RadioFragment();

        CustomViewPagerAdapter myPagerAdapter = new CustomViewPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.addFragment(mainFragment);
        myPagerAdapter.addFragment(tabNetPagerFragment);

        mCustomViewPager.setAdapter(myPagerAdapter);
        mCustomViewPager.setCurrentItem(1);
        barMusic.setSelected(true);

        mCustomViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    /***
     *切换 toolbar 的 tab 标签
     */
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
                break;
            case R.id.bar_search:
                break;
        }
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
