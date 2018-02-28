package com.stream.mmusic.video.fragment;

import com.stream.mmusic.video.R;

/**
 * Created by jdsm on 2018/2/27.
 */

public class MyMusicFragment extends BaseFragment {

    public static MyMusicFragment getInstance() {
        return new MyMusicFragment();
    }
    @Override
    protected void setLayoutId() {
        layoutId= R.layout.fragment_mymusic;
    }

    @Override
    protected void initData() {

    }
}
