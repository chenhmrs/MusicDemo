package com.stream.mmusic.video.fragment;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.stream.mmusic.Data.HomeData;
import com.stream.mmusic.video.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;

/**
 * Created by jdsm on 2018/2/27.
 */

public class HomeFragment extends BaseFragment implements Observer {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager1)
    ViewPager mViewPagrer1;
    List<String> mTitleList;
    List<Fragment> mFragmentList;
    public HomeData mHomeData;
    public static HomeFragment getInstance() {
        return new HomeFragment();
    }
    @Override
    protected void setLayoutId() {
        layoutId= R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        mHomeData=HomeData.getInstance();
        mTitleList=new ArrayList<>();
        mFragmentList=new ArrayList<>();
        mHomeData.addObserver(this);
        mTitleList.add("Top");
        mTitleList.add("Charts");
        mTitleList.add("Trend");
        mFragmentList.add(TopFragment.getInstance());
        mFragmentList.add(ChartsFragment.getInstance());
        mFragmentList.add(TrendFragment.getInstance());
        mTabLayout.setupWithViewPager(mViewPagrer1);
        mViewPagrer1.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitleList.get(position);
            }
        });
    }



    @Override
    public void update(Observable o, Object arg) {
        Log.d("MainActivity","success"+mHomeData.getDataList().size());
    }
}
