package com.weibo.meishijie.model.recommend;

import com.weibo.meishijie.bean.recommend.youlike.YouLike;
import com.weibo.meishijie.util.Constant;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by 巴巴 on 2016/8/14.
 */

public interface YouLikeApi {
    @POST(Constant.DATA_INDEX)
    @FormUrlEncoded
    Observable<YouLike> getYouLikeData(@FieldMap() Map<String,String> map);
}
