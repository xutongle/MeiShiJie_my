package com.weibo.meishijie.bean.recommend.youlike;

/**
 * Created by 巴巴 on 2016/8/14.
 */

public class YouLike{
    /**
     * code: "11",
     obj: {}
     */
    private int code;
    private Obj obj;

    public YouLike() {
    }

    public YouLike(int code, Obj obj) {
        this.code = code;
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "YouLike{" +
                "code=" + code +
                ", obj=" + obj +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }
}
