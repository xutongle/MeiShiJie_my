package com.weibo.meishijie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.weibo.meishijie.R;
import com.weibo.meishijie.adapter.FragmentViewPagerAdapter;
import com.weibo.meishijie.base.BaseActivity;
import com.weibo.meishijie.util.Constant;
import com.weibo.meishijie.util.ImageLoader;
import com.weibo.meishijie.util.MainApp;
import com.weibo.meishijie.util.SPUtil;
import com.weibo.meishijie.view.custom.JumpViewPager;
import com.weibo.meishijie.view.fragment.WellcomeFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class WellcomeActivity extends BaseActivity implements JumpViewPager.OnEdgeFinishedListener {

    @BindView(R.id.wellcome_Viewpager)
    JumpViewPager Vp;

    @BindView(R.id.image_wellcom)
    ImageView iw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ( MainApp.getSp() != null && !SPUtil.getBoolean(Constant.WELLCOM_ISFIRST)) {
            Vp.setVisibility(View.VISIBLE);
            initFragment();
            SPUtil.putBoolean(Constant.WELLCOM_ISFIRST, true);
        } else {
            iw.setVisibility(View.VISIBLE);
            initImageView();
        }
    }

    private void initImageView() {
        int[] images = {R.mipmap.pic_welcome_1,R.mipmap.pic_welcome_2};
        Random random = new Random();
        int i = random.nextInt(2);
        ImageLoader.load(MainApp.getAppContext(),images[i],iw);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                start();
            }
        }, 3000);
    }

    private void initFragment() {
        int[] images = {R.mipmap.introduct_1, R.mipmap.introduct_2, R.mipmap.introduct_3};
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            fragments.add(WellcomeFragment.newInstance(images[i]));
        }
        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(fragments, getSupportFragmentManager());
        Vp.setAdapter(adapter);
        Vp.setOnEdgeFinishedListener(this);
    }

    private void start(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        remove(this);
        finish();
    }

    @Override
    public void OnRightEdgeFinished(View view) {
        start();
    }

    @Override
    public void OnleftEdgeFinished(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wellcome;
    }
}
