package com.weibo.meishijie.presenter.recommend;

import com.weibo.meishijie.bean.recommend.Recommend;
import com.weibo.meishijie.bean.recommend.youlike.YouLike;
import com.weibo.meishijie.presenter.BasePresenter;

/**
 * Created by 巴巴 on 2016/7/31.
 */

public interface RecommendPresenter extends BasePresenter{
    void OnloadDataSuccess(Recommend recommend);
    void loadYouLike();
    void OnloadYouLikeDataSuccess(YouLike youLike);
    void OnloadDataError(Throwable throwable);
}
