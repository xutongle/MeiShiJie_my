package com.weibo.meishijie.view.fragment;

import com.weibo.meishijie.R;
import com.weibo.meishijie.base.BaseFragment;

public class Fragment_shop extends BaseFragment {

    public Fragment_shop() {}

    public static Fragment_shop newInstance() {
        Fragment_shop fragment = new Fragment_shop();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

}
