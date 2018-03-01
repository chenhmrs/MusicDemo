package com.stream.mmusic.video.fragment;

import com.stream.mmusic.video.R;

/**
 * Created by Well Wang on 2018/3/1.
 */

public class ChartsFragment extends BaseFragment {

    public static ChartsFragment getInstance(){
        return new ChartsFragment();
    }
    @Override
    protected void setLayoutId() {
        layoutId= R.layout.fragment_charts;
    }

    @Override
    protected void initData() {

    }
}
