package com.weibo.meishijie.model.recommend;

import com.weibo.meishijie.presenter.recommend.RecommendPresenter;
import com.weibo.meishijie.util.Internet_utils;
import com.weibo.meishijie.util.MainApp;
import com.weibo.meishijie.util.OkUtil;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class RecommendModelImp implements RecommendModel {

    private RecommendPresenter presenter;

    public RecommendModelImp(RecommendPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void Load() {
        OkUtil.createRetrofit().create(RecommendApi.class)
                .getTuijianData(OkUtil.getTuijianMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tuiJian -> {
                    presenter.loadData(tuiJian);
                });
    }

    @Override
    public void update() {

    }

    @Override
    public void NetError() {

    }
}
