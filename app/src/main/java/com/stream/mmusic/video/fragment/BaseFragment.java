package com.stream.mmusic.video.fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by jdsm on 2018/2/27.
 */

public abstract class BaseFragment extends Fragment {
    protected int layoutId;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setLayoutId();
        View view=inflater.inflate(layoutId,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    protected abstract void setLayoutId();

    protected abstract void initData();

   // protected abstract Fragment getInstance();
}
