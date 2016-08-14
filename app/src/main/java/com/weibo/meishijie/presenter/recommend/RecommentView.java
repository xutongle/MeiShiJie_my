package com.weibo.meishijie.presenter.recommend;

import com.weibo.meishijie.bean.recommend.Recommend;
import com.weibo.meishijie.bean.recommend.youlike.YouLike;

/**
 * Created by 巴巴 on 2016/7/31.
 */

public interface RecommentView {
    void limnView(Recommend recommend);
    void loadYoulikeDataSuccess(YouLike youLike);
}
