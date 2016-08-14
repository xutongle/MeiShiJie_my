package com.weibo.meishijie.presenter.recommend;

import com.weibo.meishijie.bean.recommend.Recomend;
import com.weibo.meishijie.presenter.BasePresenter;

/**
 * Created by 巴巴 on 2016/7/31.
 */

public interface RecommendPresenter extends BasePresenter{
    void OnloadDataSuccess(Recomend recomend);
    void OnloadDataError(Throwable throwable);
}
