package com.example.jdsm.musicdemo.fragment;

import com.example.jdsm.musicdemo.R;

/**
 * Created by jdsm on 2018/2/27.
 */

public class SearchFragment extends BaseFragment {

    public static SearchFragment getInstance() {
        return new SearchFragment();
    }
    @Override
    protected void setLayoutId() {
        layoutId= R.layout.fragment_search;
    }

    @Override
    protected void initData() {

    }
}
