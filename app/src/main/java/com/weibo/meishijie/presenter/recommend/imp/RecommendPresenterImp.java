package com.weibo.meishijie.presenter.recommend.imp;

import com.weibo.meishijie.bean.recommend.Recommend;
import com.weibo.meishijie.bean.recommend.youlike.YouLike;
import com.weibo.meishijie.model.recommend.RecommendModel;
import com.weibo.meishijie.model.recommend.RecommendModelImp;
import com.weibo.meishijie.presenter.recommend.RecommendPresenter;
import com.weibo.meishijie.presenter.recommend.RecommentView;

/**
 * Created by 巴巴 on 2016/7/31.
 */

public class RecommendPresenterImp implements RecommendPresenter{

    private RecommentView recommentView;
    private RecommendModel model;

    public RecommendPresenterImp(RecommentView recommentView){
        this.recommentView = recommentView;
    }

    @Override
    public void onStart() {
        model = new RecommendModelImp(this);
        model.loadData();
    }

    @Override
    public void OnloadDataSuccess(Recommend recommend) {
        recommentView.limnView(recommend);
    }

    @Override
    public void loadYouLike() {
        model.loadYouLikeData();
    }

    @Override
    public void OnloadYouLikeDataSuccess(YouLike youLike) {
        recommentView.loadYoulikeDataSuccess(youLike);
    }


    @Override
    public void OnloadDataError(Throwable throwable) {

    }
}
