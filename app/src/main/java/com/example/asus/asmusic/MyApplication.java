package com.example.asus.asmusic;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

import com.example.asus.asmusic.common.utils.ThemeHelper;
import com.example.asus.asmusic.R;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.mob.MobApplication;

import java.util.LinkedList;
import java.util.List;

import magicasakura.utils.ThemeUtils;
import okhttp3.OkHttpClient;

/**
 * Created by hongkl on 17/4/14.
 */
public class MyApplication extends MobApplication implements ThemeUtils.switchColor{
    public static Context context;
    private static int MAX_MEM = (int) Runtime.getRuntime().maxMemory() / 4;

    /**
     * application singleton
     */
    private static MyApplication instance;
    public static AppConfig appConfig;

    public static MyApplication getInstance() {

        return instance;
    }

    private void frescoInit() {
        Fresco.initialize(this, getConfigureCaches(this));
    }

    /**
     * 运用list来保存每一个activity
     * activity list
     */
    private List<Activity> mList;

    @Override
    public void onCreate() {
        frescoInit();
        super.onCreate();
        context = this;
        instance = this;
        mList = new LinkedList<>();
        ThemeUtils.setSwitchColor(this);
        //OKGO的基本配置
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);
        appConfig = new AppConfig(context);

    }

    @Override
    public int replaceColorById(Context context, @ColorRes int colorId) {
        if (ThemeHelper.isDefaultTheme(context)) {
            return context.getResources().getColor(colorId);
        }
        String theme = getTheme(context);
        if (theme != null) {
            colorId = getThemeColorId(context, colorId, theme);
        }
        return context.getResources().getColor(colorId);
    }

    @Override
    public int replaceColor(Context context, @ColorInt int originColor) {
        if (ThemeHelper.isDefaultTheme(context)) {
            return originColor;
        }
        String theme = getTheme(context);
        int colorId = -1;
        if (theme != null) {
            colorId = getThemeColor(context, originColor, theme);
        }
        return colorId != -1 ? getResources().getColor(colorId) : originColor;
    }


    private String getTheme(Context context) {
        if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_STORM) {
            return "blue";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_HOPE) {
            return "purple";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_WOOD) {
            return "green";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_LIGHT) {
            return "green_light";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_THUNDER) {
            return "yellow";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_SAND) {
            return "orange";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_FIREY) {
            return "red";
        }
        return null;
    }

    private
    @ColorRes
    int getThemeColorId(Context context, int colorId, String theme) {
        switch (colorId) {
            case R.color.theme_color_primary:
                return context.getResources().getIdentifier(theme, "color", getPackageName());
            case R.color.theme_color_primary_dark:
                return context.getResources().getIdentifier(theme + "_dark", "color", getPackageName());
            case R.color.playbarProgressColor:
                return context.getResources().getIdentifier(theme + "_trans", "color", getPackageName());
        }
        return colorId;
    }

    private
    @ColorRes
    int getThemeColor(Context context, int color, String theme) {
        switch (color) {
            case 0xd20000:
                return context.getResources().getIdentifier(theme, "color", getPackageName());
        }
        return -1;
    }


    private ImagePipelineConfig getConfigureCaches(Context context) {
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(
                MAX_MEM,// 内存缓存中总图片的最大大小,以字节为单位。
                Integer.MAX_VALUE,// 内存缓存中图片的最大数量。
                MAX_MEM,// 内存缓存中准备清除但尚未被删除的总图片的最大大小,以字节为单位。
                Integer.MAX_VALUE,// 内存缓存中准备清除的总图片的最大数量。
                Integer.MAX_VALUE / 10);// 内存缓存中单个图片的最大大小。

        Supplier<MemoryCacheParams> mSupplierMemoryCacheParams = new Supplier<MemoryCacheParams>() {
            @Override
            public MemoryCacheParams get() {
                return bitmapCacheParams;
            }
        };
        ImagePipelineConfig.Builder builder = ImagePipelineConfig.newBuilder(context)
                .setDownsampleEnabled(true);
        builder.setBitmapMemoryCacheParamsSupplier(mSupplierMemoryCacheParams);

        //默认图片的磁盘配置
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(context)
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory().getAbsoluteFile())//缓存图片基路径
                .build();

        return builder.build();
    }

    /**
     * 添加一个activity到列表中<br/>
     * add Activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    /**
     * 从列表中删除一个activity<br/>
     * remove Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        try {
            mList.remove(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否已经运行该activity<br/>
     *
     * @param activity
     * @return
     */
    public boolean containActivity(Class activity) {
        for (Activity act : mList) {
            if (act.getClass() == activity) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取已经运行的Activity<br/>
     *
     * @param activity
     * @return
     */
    public Activity getActivity(Class activity) {
        for (Activity act : mList) {
            if (act.getClass() == activity) {
                return act;
            }
        }
        return null;
    }

    /**
     * 关闭list内的每一个activity<br/>
     * close all activity
     */
    public void closeAllActivity() {
        try {
            for (Activity activity : mList) {
                if (activity != null && !activity.isFinishing())
                    activity.finish();
            }
            mList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获得最后打开的activity<br/>
     * get last opened activity
     */
    public Activity getCurActivity() {
        if (mList.size() > 0)
            return mList.get(mList.size() - 1);
        return null;
    }


    /**
     * 关闭list内的每一个activity并且退出应用<br/>
     * close all activity and exit app
     */
    public void exit() {
        closeAllActivity();
        //System.exit(0);
    }
}
