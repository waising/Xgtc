package com.xgtongcheng.xgtc.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.xgtongcheng.xgtc.common.SysApplication;

/**
 * 
 * @ClassName 类名：AndroidTools
 * @Description 功能说明： TODO
 ************************************************************************ 
 * @date 创建日期：2012-11-8
 * @author 创建人：wwx
 * @version 版本号：V1.0
 *          <p>
 *          修订记录*************************************
 * 
 *          2012-11-8 wwx 创建该类功能。
 * 
 * 
 *          </p>
 */
public class AUtil {

    final static String TAG = "ANDROIDTOOLS";

    /**
     * 
     * 函数名称：isConnect 功能说明：是否联网 参数说明：
     * 
     * @return
     * @date 创建时间：2012-11-8
     * @author 作者：wwx
     */
    public static boolean isConnect(Activity mActivity) {
        boolean isCon = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) mActivity
                    .getApplicationContext().getSystemService(
                            Context.CONNECTIVITY_SERVICE);
            NetworkInfo netWorkInfo = cm.getActiveNetworkInfo();
            if (netWorkInfo != null) {
                isCon = netWorkInfo.isAvailable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isCon;
    }

    /**
     * 
     * 函数名称：isWifi 功能说明：是否是wifi 参数说明：
     * 
     * @return
     * @date 创建时间：2012-11-8
     * @author 作者：wwx
     */
    public static boolean isWifi(Activity mActivity) {
        ConnectivityManager cm = (ConnectivityManager) mActivity
                .getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (!ni.getTypeName().equals("WIFI")) {
                /*
                 * ni.getTypeNmae()可能取值如下 WIFI，表示WIFI联网 MOBILE，表示GPRS、EGPRS
                 * 3G网络没有测试过 WIFI和(E)GPRS不能共存，如果两个都打开，系统仅支持WIFI
                 */
                return true;
            }
        }
        return false;
    }

    /**
     * WIFI是否开启
     * 
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 
     * 函数名称：hideInput 功能说明：隐藏软键盘 参数说明：
     * 
     * @param mActivity
     * @date 创建时间：2012-11-8
     * @author 作者：wwx
     */
    public static void hideInput(Activity mActivity) {
        InputMethodManager mInputMethodManager = (InputMethodManager) mActivity
                .getApplicationContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.hideSoftInputFromWindow(
                new View(mActivity).getWindowToken(), 0);
    }

    /**
     * 
     * 函数名称：isSystemApplication 功能说明：判断程序是否为系统应用 参数说明：
     * 
     * @param context
     * @param packageName
     * @return
     * @date 创建时间：2012-11-8
     * @author 作者：wwx
     */
    public static boolean isSystemApplication(Context context,
            String packageName) {
        boolean isflag = false;
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo pInfo = pm.getApplicationInfo(packageName,
                    PackageManager.GET_META_DATA);
            if ((pInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                isflag = true;
            }
        } catch (Exception e) {
            Log.i(TAG, "获取系统是否为系统应用异常!");
        }
        return isflag;
    }

    /**
     * 
     * 函数名称：getVesionName 功能说明：获取系统版本 参数说明：
     * 
     * @param context
     * @return
     * @date 创建时间：2012-11-9
     * @author 作者：wwx
     */
    public static String getVesionName(Context context) {
        String versionName = "";
        try {
            versionName = context.getPackageManager().getPackageInfo(
                    "net.vpntunnel", 0).versionName;
        } catch (NameNotFoundException e) {
            Log.i(TAG, "getVesionName:" + e.getMessage());
        }

        return versionName;
    }

    /**
     * 
     * 函数名称：getVersionCode 功能说明： 参数说明：
     * 
     * @param context
     * @return
     * @date 创建时间：2012-11-9
     * @author 作者：wwx
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            versionCode = context.getPackageManager().getPackageInfo(
                    "net.vpntunnel", 0).versionCode;
        } catch (NameNotFoundException e) {
            Log.i(TAG, "getVersionCode" + e.getMessage());
        }
        return versionCode;
    }

    /**
     * 双击退出
     * 
     * @param mActivity
     */
    private static Boolean isExit = false;

    public static void exitBy2Click(final Activity mActivity) {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(mActivity, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            mActivity.finish();
            SysApplication.getInstance().exit();
        }
    }

    /**
     * 将json 数组转换为Map 对象
     * 
     * @return
     */
    public static Map<String, Object> getMap(JSONObject jsonObject) {
        try {
            Iterator<String> iterator = jsonObject.keys();
            String key;
            Object value;
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (iterator.hasNext()) {
                key = iterator.next();
                value = jsonObject.get(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json 数组转换为List 对象
     * 
     * @return
     */
    public static List<Map<String, Object>> getList(String jsonString) {
        List<Map<String, Object>> list = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject;
            list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                list.add(getMap(jsonObject));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
