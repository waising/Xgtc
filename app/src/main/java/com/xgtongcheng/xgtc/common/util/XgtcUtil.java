package com.xgtongcheng.xgtc.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.xgtongcheng.xgtc.R;

/**
 * Created by wwx on 2015/9/8.
 */
public class XgtcUtil {
    public static DisplayImageOptions getDisplayImageOptions() {
        DisplayImageOptions options = null;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(0) // resource or
                        // drawable
                .showImageForEmptyUri(0) // resource or
                        // drawable
                .showImageOnFail(0) // resource or
                        // drawable
                .resetViewBeforeLoading(false) // default
                .delayBeforeLoading(1000).cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .considerExifParams(false) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .displayer(new SimpleBitmapDisplayer()) // default
                .handler(new Handler()) // default
                .build();

        return options;
    }

    public static DisplayImageOptions getDisplayImageOptions(int dImg,int emptyImg,int failImg) {
        DisplayImageOptions options = null;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(dImg) // resource or
                        // drawable
                .showImageForEmptyUri(emptyImg) // resource or
                        // drawable
                .showImageOnFail(failImg) // resource or
                        // drawable
                .resetViewBeforeLoading(false) // default
                .delayBeforeLoading(1000).cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .considerExifParams(false) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .displayer(new SimpleBitmapDisplayer()) // default
                .handler(new Handler()) // default
                .build();

        return options;
    }

    public static String getSmallPicPath(String url){
        String url1 = url.substring(0,url.lastIndexOf("/"));
        String name = url.substring(url.lastIndexOf("/")+1,url.length());
        return url1+"/s/"+name;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {

        } else {
            NetworkInfo info = cm.getActiveNetworkInfo();
            if(info!=null) {
                boolean flag =  info.isAvailable();
                return flag;
            }
        }
        return false;
    }
}
