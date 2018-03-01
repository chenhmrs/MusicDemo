package com.stream.mmusic.video.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
;


import com.stream.mmusic.video.R;
import com.stream.mmusic.video.fragment.HomeFragment;
import com.stream.mmusic.video.fragment.MyMusicFragment;
import com.stream.mmusic.video.fragment.SearchFragment;
import com.stream.mmusic.video.service.DataService;

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
    MenuItem menuItem;
    @Override
    protected void setLayoutId() {
        LatoutId=R.layout.activity_main;
    }
    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        initFragment();
        mViewPager.setOffscreenPageLimit(4);
        startService(new Intent(this, DataService.class));
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        invalidateOptionsMenu();
                        break;
                    case 1:
                        invalidateOptionsMenu();
                        break;
                    case 2:
                        invalidateOptionsMenu();
                        break;
                }
               mBottomNavigationView.getMenu().getItem(position).setChecked(true);                }


           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });
       mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId()){
                   case R.id.item_hot:{
                       mViewPager.setCurrentItem(0);
                       return true;
                   }
                   case R.id.item_search:{
                       mViewPager.setCurrentItem(1);
                       return true;
                   }
                   case R.id.item_my:{
                       mViewPager.setCurrentItem(2);
                       return true;
                   }
               }
               return false;
           }
       });
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
