package com.weibo.meishijie.bean.recommend;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class Customized implements Parcelable {

    /**
     * title : 猜你喜欢
     * time : 12:42为您更新
     * total : 2
     * data: [ ]
     */

    private String title;
    private String time;
    private int total;
    //private Data[] data;


    @Override
    public String toString() {
        return "Customized{" +
                "title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", total=" + total +
                '}';
    }

    public Customized() {
    }

    public Customized(String title, String time, int total) {
        this.title = title;
        this.time = time;
        this.total = total;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.time);
        dest.writeInt(this.total);
    }

    protected Customized(Parcel in) {
        this.title = in.readString();
        this.time = in.readString();
        this.total = in.readInt();
    }

    public static final Parcelable.Creator<Customized> CREATOR = new Parcelable.Creator<Customized>() {
        @Override
        public Customized createFromParcel(Parcel source) {
            return new Customized(source);
        }

        @Override
        public Customized[] newArray(int size) {
            return new Customized[size];
        }
    };
}
