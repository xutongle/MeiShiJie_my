package com.weibo.meishijie.model.recommend;

import com.weibo.meishijie.presenter.recommend.RecommendPresenter;
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
    public void loadData() {
        OkUtil.createRetrofit().create(RecommendApi.class)
                .getRecommendData(OkUtil.getTuijianMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tuiJian -> {
                    presenter.OnloadDataSuccess(tuiJian);
                }, throwable -> {
                    presenter.OnloadDataError(throwable);
                });
    }

    @Override
    public void loadYouLikeData() {
        OkUtil.createRetrofit().create(YouLikeApi.class)
                .getYouLikeData(OkUtil.getYouLikeMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(youLike -> {
                   presenter.OnloadYouLikeDataSuccess(youLike);
                });
    }

}
