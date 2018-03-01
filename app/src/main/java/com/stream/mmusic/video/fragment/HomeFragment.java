package com.stream.mmusic.video.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stream.mmusic.video.Data.HomeData;
import com.stream.mmusic.video.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;

/**
 * Created by jdsm on 2018/2/27.
 */

public class HomeFragment extends BaseFragment implements Observer {
    @BindView(R.id.tab_layout1)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager_fragment)
    ViewPager mViewPagrer1;
    List<String> mTitleList;
    List<Fragment> mFragmentList;
    public HomeData mHomeData;
    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setLayoutId() {
        layoutId= R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        mHomeData=HomeData.getInstance();
        mTitleList=new ArrayList<>();
        mFragmentList=new ArrayList<>();
        mHomeData.addObserver(this);
        mTitleList.add("Top");
        mTitleList.add("Charts");
        mTitleList.add("Trend");
      //  setIndicator(getContext(),mTabLayout,5,5);
        mFragmentList.add(TopFragment.getInstance());
        mFragmentList.add(ChartsFragment.getInstance());
        mFragmentList.add(TrendFragment.getInstance());
        mTabLayout.setupWithViewPager(mViewPagrer1);
       // setIndicator(getContext(),mTabLayout,10,10);
        setIndicator1(15,15);
        mViewPagrer1.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
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
        mTabLayout.setupWithViewPager(mViewPagrer1);
    }

    private void setIndicator1(final int leftDip,final int rightDip) {
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    Field mTabStripField = mTabLayout.getClass().getDeclaredField("mTabStrip");
                    mTabStripField.setAccessible(true);

                    LinearLayout mTabStrip = (LinearLayout) mTabStripField.get(mTabLayout);

                    //  将dp转化为px
                    int left = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,leftDip, getActivity().getResources().getDisplayMetrics());
                    int right = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,rightDip, getActivity().getResources().getDisplayMetrics());
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为你想要的  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = left;
                        params.rightMargin = right;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    @Override
    public void update(Observable o, Object arg) {
        Log.d("MainActivity","success"+mHomeData.getDataList().size());
    }


}
