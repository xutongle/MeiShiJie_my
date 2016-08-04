package com.weibo.meishijie.view.fragment;

import com.weibo.meishijie.R;
import com.weibo.meishijie.base.BaseFragment;

public class Fragment_discover extends BaseFragment {

    public Fragment_discover() {
    }

    public static Fragment_discover newInstance() {
        Fragment_discover fragment = new Fragment_discover();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discover;
    }

}
