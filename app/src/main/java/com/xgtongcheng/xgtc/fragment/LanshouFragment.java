package com.xgtongcheng.xgtc.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xgtongcheng.xgtc.R;

/**
 * Created by wwx on 2015/9/9.
 */
public class LanshouFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //在这里定义Fragment的布局
        return inflater.inflate(R.layout.fragment_lanshou, container,false);
    }
}
