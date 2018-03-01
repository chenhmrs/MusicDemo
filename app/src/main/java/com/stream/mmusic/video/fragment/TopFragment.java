package com.stream.mmusic.video.fragment;

import com.stream.mmusic.video.R;

/**
 * Created by Well Wang on 2018/3/1.
 */

public class TopFragment extends BaseFragment {

    public static TopFragment getInstance(){
        return new TopFragment();
    }
    @Override
    protected void setLayoutId() {
        layoutId= R.layout.fragment_top;
    }

    @Override
    protected void initData() {

    }
}
