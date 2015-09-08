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
     * @param username
     * @param password
     * @param handler
     */
    public static void login(String username, String password,
                             AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("password", password);
        ApiHttpClient.post("login", params, handler);
    }

}
