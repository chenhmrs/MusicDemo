package com.example.jdsm.musicdemo.fragment;

import android.app.Fragment;

import com.example.jdsm.musicdemo.R;

/**
 * Created by jdsm on 2018/2/27.
 */

public class HomeFragment extends BaseFragment {

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }
    @Override
    protected void setLayoutId() {
        layoutId= R.layout.fragment_home;
    }

    @Override
    protected void initData() {

    }



}
