package com.weibo.meishijie.view.fragment;

import com.weibo.meishijie.R;
import com.weibo.meishijie.base.BaseFragment;

public class Fragment_topic extends BaseFragment {

    public Fragment_topic() {}

    public static Fragment_topic newInstance() {
        Fragment_topic fragment = new Fragment_topic();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_topic;
    }

}
