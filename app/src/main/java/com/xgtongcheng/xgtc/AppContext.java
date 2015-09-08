package com.xgtongcheng.xgtc;

import android.app.Application;
import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.xgtongcheng.xgtc.common.http.ApiHttpClient;

import java.io.File;

/**
 * Created by WWX on 2015/07/17.
 */
public class AppContext extends Application {

    private static AppContext instance;
    File cacheDir;

    Context _context;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
        initImageLoader(this);
    }

    private void init() {
        // 初始化网络请求
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);

        client.setTimeout(6000);
        ApiHttpClient.setHttpClient(client);

        cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), AppConfig.BITMAP_PATH);
    }

    /**
     * 获得当前app运行的AppContext
     *
     * @return
     */
    public static AppContext getInstance() {
        return instance;
    }


    public void initImageLoader(Context context){
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration
                .Builder(context);
        builder.memoryCacheExtraOptions(480, 800);
        builder.diskCacheExtraOptions(480, 800, null);
        builder.threadPoolSize(3);
        builder.threadPriority(Thread.NORM_PRIORITY - 1);
        builder.tasksProcessingOrder(QueueProcessingType.FIFO);
        builder.denyCacheImageMultipleSizesInMemory();
        builder.memoryCache(new LruMemoryCache(2 * 1024 * 1024));
        builder.memoryCacheSize(2 * 1024 * 1024);
        builder.memoryCacheSizePercentage(13);
        builder.diskCache(new UnlimitedDiskCache(cacheDir));
        builder.diskCacheSize(50 * 1024 * 1024);
        builder.diskCacheFileCount(100);
        builder.diskCacheFileNameGenerator(new HashCodeFileNameGenerator());
        builder.imageDownloader(new BaseImageDownloader(context));
        builder.imageDecoder(new BaseImageDecoder(true));
        builder.defaultDisplayImageOptions(DisplayImageOptions.createSimple());
        builder.writeDebugLogs();
        ImageLoaderConfiguration config = builder.build();
        ImageLoader.getInstance().init(config);
    }

}
