package com.xgtongcheng.xgtc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.github.mrengineer13.snackbar.SnackBar;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.xgtongcheng.xgtc.R;
import com.xgtongcheng.xgtc.common.http.api.XgtcApi;
import com.xgtongcheng.xgtc.common.util.SBar;
import com.xgtongcheng.xgtc.entity.Employee;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WWX on 2015/9/9.
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.et_username)
    MaterialEditText usernameEditText;

    @Bind(R.id.et_password)
    MaterialEditText pwdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.login));
        toolbar.setTitleTextColor(0xffffffff);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @OnClick(R.id.btn_login)
    void onLoginClick(){

        if (!check()) return;

        XgtcApi.login(usernameEditText.getText().toString().trim(), pwdEditText.getText().toString().trim(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                try {
                    Employee employee = gson.fromJson(new String(responseBody, "utf-8"), Employee.class);

                    String x = employee.getId();
                    String b = employee.getJobNo();

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                SBar.showLong("网络异常", LoginActivity.this);
            }
        });

    }

    boolean check(){
        boolean isOk=true;
        if (TextUtils.isEmpty(usernameEditText.getText().toString())) {
            usernameEditText.setError(getString(R.string.username_no_empty));
            isOk = false;
        }else if(TextUtils.isEmpty(pwdEditText.getText().toString())){
            pwdEditText.setError(getString(R.string.password_no_empty));
            isOk = false;
        }
        return isOk;
    }
}
