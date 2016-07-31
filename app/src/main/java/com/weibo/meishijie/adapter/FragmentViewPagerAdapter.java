package com.weibo.meishijie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public FragmentViewPagerAdapter(List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
