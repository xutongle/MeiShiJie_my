package com.weibo.meishijie.view.fragment.recommend;

import android.content.Context;
import android.graphics.Color;
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
import android.widget.TextView;

import com.weibo.meishijie.R;
import com.weibo.meishijie.adapter.FragmentViewPagerAdapter;
import com.weibo.meishijie.base.BaseFragment;
import com.weibo.meishijie.base.recyclerView.CommonAdapter;
import com.weibo.meishijie.base.recyclerView.HeaderdAndFooterWrapper;
import com.weibo.meishijie.base.recyclerView.ViewHolder;
import com.weibo.meishijie.bean.recommend.Fenlei;
import com.weibo.meishijie.bean.recommend.Fun1;
import com.weibo.meishijie.bean.recommend.Fun2;
import com.weibo.meishijie.bean.recommend.Obj;
import com.weibo.meishijie.bean.recommend.Recommend;
import com.weibo.meishijie.bean.recommend.Shops;
import com.weibo.meishijie.bean.recommend.Top3;
import com.weibo.meishijie.bean.recommend.Top4;
import com.weibo.meishijie.bean.recommend.Zt;
import com.weibo.meishijie.bean.recommend.youlike.Data;
import com.weibo.meishijie.bean.recommend.youlike.YouLike;
import com.weibo.meishijie.presenter.recommend.RecommendPresenter;
import com.weibo.meishijie.presenter.recommend.RecommentView;
import com.weibo.meishijie.presenter.recommend.imp.RecommendPresenterImp;
import com.weibo.meishijie.util.DLog;
import com.weibo.meishijie.util.ImageLoader;
import com.weibo.meishijie.view.activity.MainActivity;
import com.weibo.meishijie.view.custom.ADViewpager;
import com.weibo.meishijie.view.custom.ADViewpager_san_can;
import com.weibo.meishijie.view.custom.RoundImageView;
import com.weibo.meishijie.view.fragment.Fragment_san_can;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

public class Fragment_recommend extends BaseFragment implements RecommentView {

    @BindView(R.id.zuireshangpin_Recycleview)
    RecyclerView zuireshangpin_re;

    private RecommendPresenter recommendPresenter;
    private HeaderdAndFooterWrapper headerdAndFooterWrapper;
    private ADViewpager_san_can adViewpagerSancan;
    private ADViewpager top3VP;
    private MainActivity mainActivity;
    private LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recommendPresenter = new RecommendPresenterImp(this);
        recommendPresenter.onStart();
    }

    @Override
    public void limnView(Recommend recommend) {
        Obj obj = recommend.getObj();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity, 2);
        zuireshangpin_re.setLayoutManager(gridLayoutManager);
        CommonAdapter<Shops> commonAdapter = new CommonAdapter<Shops>(
                mainActivity, R.layout.zuireshangpin_item, Arrays.asList(obj.getShops())) {
            @Override
            public void convert(ViewHolder holder, Shops shops, int position) {
                holder.setText(R.id.zuire_title,shops.getTitle());
                holder.setTextAndColor(R.id.priceandguide,shops.getPrice() + "/" + shops.getGuige(), Color.RED);
                holder.setImageViewSrc(R.id.zuire_image,shops.getImage());
            }
        };
        headerdAndFooterWrapper = new HeaderdAndFooterWrapper(commonAdapter);

        addScan_canViewpager(obj);
        addFenlei(obj.getFenlei());
        addFun(obj);
        addZuiReShangPin();
        addTop3(obj.getTop3());

        addJinRiTuiJian();
        addZt(obj.getZt());
        addTop4(obj.getTop4());
        addCainixihuan();

        zuireshangpin_re.setAdapter(headerdAndFooterWrapper);
        setTime();
    }

    @Override
    public void loadYoulikeDataSuccess(YouLike youLike) {
        String time = youLike.getObj().getCustomized().getTime();
        TextView tv_time = new TextView(mainActivity);
        tv_time.setLayoutParams(layoutParams);
        tv_time.setGravity(Gravity.CENTER);
        tv_time.setText(time);
        headerdAndFooterWrapper.addFooterView(tv_time);

        Data[] datas = youLike.getObj().getCustomized().getDatas();
        if (datas == null || datas.length == 0){return;}
        RecyclerView youlikeR = new RecyclerView(mainActivity);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity,2);
        youlikeR.setLayoutManager(gridLayoutManager);
        CommonAdapter<Data> commonAdapter = new CommonAdapter<Data>(
                mainActivity,R.layout.youlike_item,Arrays.asList(datas)) {
            @Override
            public void convert(ViewHolder holder, Data data, int position) {
                holder.setTextAndColor(R.id.tv_youlike,data.getTitle(), Color.WHITE);
                holder.setImageViewSrc(R.id.iv_youlike,data.getTitlepic());
            }
        };
        youlikeR.setAdapter(commonAdapter);
        headerdAndFooterWrapper.addFooterView(youlikeR);
    }

    private void addCainixihuan(){
        View cainixihuan = LayoutInflater.from(mainActivity).inflate(R.layout.zuireshangpin,null,false);
        ImageView iconIv = findView(cainixihuan,R.id.icon_iv);
        iconIv.setImageResource(R.mipmap.tj_guess_youlike);
        TextView icon_Tv = findView(cainixihuan,R.id.icon_tv);
        icon_Tv.setText(R.string.cainixihuan);
        headerdAndFooterWrapper.addFooterView(cainixihuan);
        recommendPresenter.loadYouLike();
    }

    private void addTop4(Top4[] top4s){
        LinearLayout ztL = new LinearLayout(mainActivity);
        ztL.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ztL.setOrientation(LinearLayout.VERTICAL);
        int length = top4s.length;
        Top4 top4;
        ImageView imageView;
        for (int i = 0;i < length;i++){
            top4 = top4s[i];
            imageView = new ImageView(mainActivity);
            imageView.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300));
            imageView.setPadding(0,10,0,10);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.load(this,top4.getPhoto(),imageView);
            ztL.addView(imageView);
        }

        headerdAndFooterWrapper.addFooterView(ztL);
    }

    private void addZt(Zt[] zts){
        LinearLayout ztL = new LinearLayout(mainActivity);
        ztL.setLayoutParams(layoutParams);
        ztL.setOrientation(LinearLayout.VERTICAL);
        int length = zts.length;
        Zt zt;
        RoundImageView riv;
        for (int i = 0;i < length;i++){
            zt = zts[i];
            riv = new RoundImageView(mainActivity);
            riv.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            riv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.load(this,zt.getPhoto(),riv);
            LinearLayout linearLayout = new LinearLayout(mainActivity);
            linearLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
            linearLayout.setPadding(10,10,10,10);
            linearLayout.addView(riv);
            ztL.addView(linearLayout);
        }

        headerdAndFooterWrapper.addFooterView(ztL);
    }

    private void addJinRiTuiJian(){
        View jinrituijianL = LayoutInflater.from(mainActivity).inflate(R.layout.jinrituijain,null,false);
        ImageView iconIv = findView(jinrituijianL,R.id.icon_iv);
        iconIv.setImageResource(R.mipmap.tj_zhuanti);
        TextView icon_Tv = findView(jinrituijianL,R.id.icon_tv);
        icon_Tv.setText(R.string.jinrituijian);
        headerdAndFooterWrapper.addFooterView(jinrituijianL);
    }

    private void addTop3(Top3[] top3){
        top3VP = new ADViewpager(mainActivity);
        top3VP.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200));
        top3VP.setAdapter(new ADViewpager.CommonViewPagerAdapter<Top3>(Arrays.asList(top3)) {
            @Override
            public View convert(Top3 top3, int position) {
                ImageView imageView = new ImageView(mainActivity);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                ImageLoader.load(Fragment_recommend.this,top3.getPhoto(),imageView);
                return imageView;
            }
        });
        top3VP.setIcon(R.mipmap.recipes_select_true,R.mipmap.recipes_select_false);
        top3VP.setIconGravity(Gravity.CENTER);
        top3VP.startCycle();
        headerdAndFooterWrapper.addHearderView(top3VP);
    }

    private void addZuiReShangPin(){
        View zuireL = LayoutInflater.from(mainActivity).inflate(R.layout.zuireshangpin,null,false);
        headerdAndFooterWrapper.addHearderView(zuireL);
    }

    private void addFun(Obj obj){
        LinearLayout funL = new LinearLayout(mainActivity);
        funL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200));
        funL.setGravity(Gravity.CENTER);

        Fun1 fun1 = obj.getFunc1();
        ImageView imageView1 = getFunImageView();
        funL.addView(imageView1);
        ImageLoader.load(this,fun1.getImage(),imageView1);

        Fun2 fun2 = obj.getFunc2();
        ImageView imageView2 = getFunImageView();
        funL.addView(imageView2);
        ImageLoader.load(this,fun2.getImage(),imageView2);

        headerdAndFooterWrapper.addHearderView(funL);
    }

    private ImageView getFunImageView(){
        ImageView imageView = new ImageView(mainActivity);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
        imageView.setPadding(5,0,5,0);
        return imageView;
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
            title.setLayoutParams(layoutParams);
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
    public void onDestroy() {
        super.onDestroy();
        top3VP.recycle();
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
