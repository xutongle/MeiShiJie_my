package com.weibo.meishijie.model.recommend;

import com.weibo.meishijie.bean.recommend.Recomend;
import com.weibo.meishijie.util.Constant;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public interface RecommendApi {
    @POST(Constant.DATA_INDEX)
    @FormUrlEncoded
    Observable<Recomend> getRecommendData(@FieldMap() Map<String,String> map);
}
