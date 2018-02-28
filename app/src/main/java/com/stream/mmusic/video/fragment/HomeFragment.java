package com.stream.mmusic.video.fragment;

import com.stream.mmusic.video.R;

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
