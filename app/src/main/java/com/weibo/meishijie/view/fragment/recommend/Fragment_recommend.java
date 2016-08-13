package com.weibo.meishijie.view.fragment.recommend;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.weibo.meishijie.R;
import com.weibo.meishijie.adapter.FragmentViewPagerAdapter;
import com.weibo.meishijie.adapter.Recommend_Zuireshangpin_Adapter;
import com.weibo.meishijie.base.BaseFragment;
import com.weibo.meishijie.base.recyclerView.HeaderdAndFooterWrapper;
import com.weibo.meishijie.bean.recommend.Fenlei;
import com.weibo.meishijie.bean.recommend.Obj;
import com.weibo.meishijie.bean.recommend.Recomend;
import com.weibo.meishijie.presenter.recommend.RecommendPresenter;
import com.weibo.meishijie.presenter.recommend.RecommentView;
import com.weibo.meishijie.presenter.recommend.imp.RecommendPresenterImp;
import com.weibo.meishijie.util.DLog;
import com.weibo.meishijie.util.ImageLoader;
import com.weibo.meishijie.util.Internet_utils;
import com.weibo.meishijie.view.activity.MainActivity;
import com.weibo.meishijie.view.custom.ADViewpager_san_can;
import com.weibo.meishijie.view.custom.NavigationBar_RadioButton;
import com.weibo.meishijie.view.fragment.Fragment_san_can;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class Fragment_recommend extends BaseFragment implements RecommentView {

    @BindView(R.id.zuireshangpin_Recycleview)
    RecyclerView zuireshangpin_re;

    private ADViewpager_san_can adViewpagerSancan;
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
        addFenlei(obj.getFenlei());

        zuireshangpin_re.setAdapter(headerdAndFooterWrapper);
        setTime();
    }

    private void addFenlei(Fenlei[] fenleis){
        LinearLayout feileiLinearLayout = new LinearLayout(mainActivity);
        feileiLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150));
        feileiLinearLayout.setGravity(Gravity.CENTER);
        int length = fenleis.length;
        for (int i = 0;i < length;i++) {
            Fenlei fenlei = fenleis[i];

            LinearLayout linearLayout = new LinearLayout(mainActivity);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1));
            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            ImageView image = new ImageView(mainActivity);
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));

            TextView title = new TextView(mainActivity);
            title.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            title.setGravity(Gravity.CENTER);

            ImageLoader.load(this,fenlei.getImage(),image);
            title.setText(fenlei.getTitle());

            linearLayout.addView(image);
            linearLayout.addView(title);

            feileiLinearLayout.addView(linearLayout);
        }
        headerdAndFooterWrapper.addHearderView(feileiLinearLayout);
    }

    private void addScan_canViewpager(Obj obj) {
        List<Fragment> fragments = new ArrayList<>();
        int length = obj.getSan_can_titles().length;
        for (int i = 0; i < length; i++) {
            fragments.add(Fragment_san_can.newInstance(i, obj));
        }

        adViewpagerSancan = new ADViewpager_san_can(mainActivity);
        adViewpagerSancan.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        adViewpagerSancan.setIcon(R.mipmap.recipes_select_false, R.mipmap.recipes_select_true);
        adViewpagerSancan.setIconGravity(Gravity.CENTER);
        adViewpagerSancan.setAdapter(new FragmentViewPagerAdapter(fragments, getChildFragmentManager()));

        headerdAndFooterWrapper.addHearderView(adViewpagerSancan);
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
        if (hour >= 22 || hour < 8){
            adViewpagerSancan.setCurrentItem(4);
        } else if (hour >= 17 && hour < 22) {
            adViewpagerSancan.setCurrentItem(3);
        } else if (hour >= 14 && hour < 17) {
            adViewpagerSancan.setCurrentItem(2);
        } else if (hour >= 11 && hour < 14) {
            adViewpagerSancan.setCurrentItem(1);
        } else if (hour >= 8 && hour < 11) {
            adViewpagerSancan.setCurrentItem(0);
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
