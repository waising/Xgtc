package com.xgtongcheng.xgtc.common.http;


import android.app.Application;

public class ApiClientHelper {
	
	/**
	 * 获得请求的服务端数据的userAgent
	 * @param
	 * @return
	 */
	public static String getUserAgent(Application application) {
		StringBuilder ua = new StringBuilder("rocks.wubo");
		//ua.append('/' + appContext.getPackageInfo().versionName + '_'
		//		+  appContext.getPackageInfo().versionCode);// app版本信息
		ua.append("/Android");// 手机系统平台
		ua.append("/" + android.os.Build.VERSION.RELEASE);// 手机系统版本
		ua.append("/" + android.os.Build.MODEL); // 手机型号
		//ua.append("/" + appContext.getAppId());// 客户端唯一标识
		return ua.toString();
	}
}
