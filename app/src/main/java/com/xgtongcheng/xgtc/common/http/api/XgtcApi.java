package com.xgtongcheng.xgtc.common.http.api;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xgtongcheng.xgtc.common.http.ApiHttpClient;

/**
 * Created by wwx on 2015/9/8.
 */
public class XgtcApi {
    /**
     * 登录
     *
     * @param jobno
     * @param pwd
     * @param handler
     */
    public static void login(String jobno, String pwd,
                             AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("jobno", jobno);
        params.put("pwd", pwd);
        ApiHttpClient.post("employee/login", params, handler);
    }

}
