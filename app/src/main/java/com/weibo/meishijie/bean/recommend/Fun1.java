package com.weibo.meishijie.bean.recommend;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class Fun1 implements Parcelable {

    /**
     * title : 最受欢迎
     * image : http://static.meishij.net/wap6/images/v6/paihangbangnew.png
     */

    private String title;
    private String image;

    @Override
    public String toString() {
        return "Fun1{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public Fun1() {
    }

    public Fun1(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.image);
    }

    protected Fun1(Parcel in) {
        this.title = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Fun1> CREATOR = new Parcelable.Creator<Fun1>() {
        @Override
        public Fun1 createFromParcel(Parcel source) {
            return new Fun1(source);
        }

        @Override
        public Fun1[] newArray(int size) {
            return new Fun1[size];
        }
    };
}
