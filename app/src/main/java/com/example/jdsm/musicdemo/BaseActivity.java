package com.example.jdsm.musicdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by jdsm on 2018/2/27.
 */

public abstract class BaseActivity extends AppCompatActivity{
    protected int LatoutId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutId();
        setContentView(LatoutId);
        ButterKnife.bind(this);
        initView();
    }

    protected abstract void initView();

    protected abstract void setLayoutId();
}
