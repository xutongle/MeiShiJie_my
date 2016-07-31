package com.weibo.meishijie.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.weibo.meishijie.R;
import com.weibo.meishijie.adapter.FragmentViewPagerAdapter;
import com.weibo.meishijie.adapter.Recommend_Zuireshangpin_Adapter;
import com.weibo.meishijie.base.BaseFragment;
import com.weibo.meishijie.base.recyclerView.HeaderdAndFooterWrapper;
import com.weibo.meishijie.bean.recommend.Obj;
import com.weibo.meishijie.bean.recommend.Recomend;
import com.weibo.meishijie.presenter.recommend.RecommendPresenter;
import com.weibo.meishijie.presenter.recommend.RecommentView;
import com.weibo.meishijie.presenter.recommend.imp.RecommendPresenterImp;
import com.weibo.meishijie.view.activity.MainActivity;
import com.weibo.meishijie.view.custom.ADViewpager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

public class Fragment_recommend extends BaseFragment implements RecommentView {

    @BindView(R.id.zuireshangpin_Recycleview)
    RecyclerView zuireshangpin_re;

    private ADViewpager adViewpager;

    private HeaderdAndFooterWrapper headerdAndFooterWrapper;
    private MainActivity mainActivity;
    private RecommendPresenter recommendPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recommendPresenter = new RecommendPresenterImp(this);
        recommendPresenter.onStart();
    }

    @Override
    public void limnView(Recomend recomend) {

        Obj obj = recomend.getObj();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity, 2);
        zuireshangpin_re.setLayoutManager(gridLayoutManager);
        Recommend_Zuireshangpin_Adapter adapter = new Recommend_Zuireshangpin_Adapter
                (mainActivity, R.layout.zuireshangpin_item, Arrays.asList(obj.getShops()));
        headerdAndFooterWrapper = new HeaderdAndFooterWrapper(adapter);

        addScan_canViewpager(obj);

        zuireshangpin_re.setAdapter(headerdAndFooterWrapper);

    }

    private void addScan_canViewpager(Obj obj) {

        List<Fragment> fragments = new ArrayList<>();
        int length = obj.getSan_can_titles().length + 2;
        for (int i = 0; i < length; i++) {
            fragments.add(Fragment_san_can.newInstance(i, obj));
        }

        adViewpager = new ADViewpager(mainActivity);
        adViewpager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        adViewpager.setIcon(R.mipmap.recipes_select_false, R.mipmap.recipes_select_true);
        adViewpager.setIconGravity(Gravity.CENTER);
        adViewpager.setAdapter(new FragmentViewPagerAdapter(fragments, getChildFragmentManager()));

        setTime();

        headerdAndFooterWrapper.addHearderView(adViewpager);
    }

    private void setTime() {
        /**
         * Calendar c = Calendar.getInstance();
         取得系统日期:year = c.get(Calendar.YEAR)
         month = c.grt(Calendar.MONTH)
         day = c.get(Calendar.DAY_OF_MONTH)
         取得系统时间：hour = c.get(Calendar.HOUR_OF_DAY);
         minute = c.get(Calendar.MINUTE)
         Calendar c = Calendar.getInstance();
         取得系统日期:year = c.get(Calendar.YEAR)
         month = c.grt(Calendar.MONTH)
         day = c.get(Calendar.DAY_OF_MONTH)
         取得系统时间：hour = c.get(Calendar.HOUR_OF_DAY);
         minute = c.get(Calendar.MINUTE)
         */
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        if (hour > 22 && hour <= 24){
            adViewpager.setCurrentItem(5);
        } else if (hour >= 20 && hour <= 22) {
            adViewpager.setCurrentItem(4);
        } else if (hour >= 14 && hour <= 16) {
            adViewpager.setCurrentItem(3);
        } else if (hour < 14 && hour > 11) {
            adViewpager.setCurrentItem(2);
        } else if (hour > 8 && hour <= 10) {
            adViewpager.setCurrentItem(1);
        }
        c.clear();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    public Fragment_recommend() {
    }

    public static Fragment_recommend newInstance() {
        Fragment_recommend fragment = new Fragment_recommend();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

}
