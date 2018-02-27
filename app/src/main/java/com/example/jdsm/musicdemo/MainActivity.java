package com.example.jdsm.musicdemo;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ViewParent;
;


import com.example.jdsm.musicdemo.fragment.HomeFragment;
import com.example.jdsm.musicdemo.fragment.MyMusicFragment;
import com.example.jdsm.musicdemo.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.bottom_view)
    BottomNavigationView mBottomNavigationView;

    HomeFragment mHomeFragment;
    SearchFragment mSearchFragment;
    MyMusicFragment myMusicFragment;
    List<Fragment> fragmentList;
    @Override
    protected void setLayoutId() {
        LatoutId=R.layout.activity_main;
    }
    @Override
    protected void initView() {
       setSupportActionBar(toolbar);
       initFragment();
       mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    private void initFragment() {
        fragmentList=new ArrayList<>();
        fragmentList.add(HomeFragment.getInstance());
        fragmentList.add(SearchFragment.getInstance());
        fragmentList.add(MyMusicFragment.getInstance());
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

}
