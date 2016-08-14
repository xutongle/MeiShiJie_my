package com.weibo.meishijie.bean.recommend.youlike;

import java.util.Arrays;

/**
 * Created by 巴巴 on 2016/8/14.
 */

public class Customized {

    /**
     * title : 猜你喜欢
     * time : 17:44为您更新
     * total : 2
     * time_app : 1471167850
     */

    private String title;
    private String time;
    private int total;
    private String time_app;
    private Data[] data;

    public Customized() {
    }

    public Customized(String title, String time, int total, String time_app, Data[] data) {
        this.title = title;
        this.time = time;
        this.total = total;
        this.time_app = time_app;
        this.data = data;
    }

    public Data[] getDatas() {
        return data;
    }

    @Override
    public String toString() {
        return "Customized{" +
                "title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", total=" + total +
                ", time_app='" + time_app + '\'' +
                ", datas=" + Arrays.toString(data) +
                '}';
    }

    public void setDatas(Data[] datas) {
        this.data = datas;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTime_app() {
        return time_app;
    }

    public void setTime_app(String time_app) {
        this.time_app = time_app;
    }
}
