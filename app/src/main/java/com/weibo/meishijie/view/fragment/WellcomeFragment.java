package com.weibo.meishijie.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.weibo.meishijie.R;
import com.weibo.meishijie.base.BaseFragment;
import com.weibo.meishijie.util.DLog;
import com.weibo.meishijie.util.ImageLoader;
import com.weibo.meishijie.util.MainApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WellcomeFragment extends BaseFragment {

    private static final String ImageUrl = "imageUrl";
    private int imageUrl;

    @BindView(R.id.iv_imageWellcome)
    ImageView ivWellcom;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageLoader.load(MainApp.getAppContext(),imageUrl,ivWellcom);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wellcome;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageUrl = getArguments().getInt(ImageUrl);
        }
    }

    public WellcomeFragment() {
    }

    public static WellcomeFragment newInstance(int imageUrl) {
        WellcomeFragment fragment = new WellcomeFragment();
        Bundle args = new Bundle();
        args.putInt(ImageUrl, imageUrl);
        fragment.setArguments(args);
        return fragment;
    }

}
