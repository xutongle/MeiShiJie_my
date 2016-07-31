package com.weibo.meishijie.adapter;

import android.content.Context;
import android.graphics.Color;

import com.weibo.meishijie.R;
import com.weibo.meishijie.base.recyclerView.CommonAdapter;
import com.weibo.meishijie.base.recyclerView.ViewHolder;
import com.weibo.meishijie.bean.recommend.Shops;

import java.util.List;

/**
 * Created by 巴巴 on 2016/7/31.
 * 推荐页的最热商品适配器
 */

public class Recommend_Zuireshangpin_Adapter extends CommonAdapter<Shops> {

    public Recommend_Zuireshangpin_Adapter(Context context, int LayoutId, List datas) {
        super(context, LayoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, Shops shops, int position) {
        holder.setText(R.id.zuire_title,shops.getTitle());
        holder.setTextAddColor(R.id.priceandguide,shops.getPrice() + "/" + shops.getGuige(), Color.RED);
        holder.setImageViewSrc(R.id.zuire_image,shops.getImage());
    }

}
