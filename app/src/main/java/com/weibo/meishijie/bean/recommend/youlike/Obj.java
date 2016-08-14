package com.weibo.meishijie.bean.recommend.youlike;

/**
 * Created by 巴巴 on 2016/8/14.
 */

public class Obj {
    private Customized customized;
    private String like_mem_key;

    public Obj() {
    }

    public Obj(Customized customized, String like_mem_key) {
        this.customized = customized;
        this.like_mem_key = like_mem_key;
    }

    @Override
    public String toString() {
        return "Obj{" +
                "customized=" + customized +
                ", like_mem_key='" + like_mem_key + '\'' +
                '}';
    }

    public Customized getCustomized() {
        return customized;
    }

    public void setCustomized(Customized customized) {
        this.customized = customized;
    }

    public String getLike_mem_key() {
        return like_mem_key;
    }

    public void setLike_mem_key(String like_mem_key) {
        this.like_mem_key = like_mem_key;
    }
}
