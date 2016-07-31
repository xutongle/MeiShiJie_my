package com.weibo.meishijie.presenter.recommend.imp;

import com.weibo.meishijie.bean.recommend.Recomend;
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
    public void loadData(Recomend recomend) {
        recommentView.limnView(recomend);
    }

    @Override
    public void onStart() {
        model = new RecommendModelImp(this);
        model.Load();
    }
}
