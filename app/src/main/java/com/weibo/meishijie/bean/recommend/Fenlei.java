package com.weibo.meishijie.bean.recommend;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class Fenlei implements Parcelable {

    /**
     * title : 菜谱分类
     * image : http://static.meishij.net/wap6/images/v6/quanbunew.png
     * click_type : 24
     * click_obj : 全部菜谱
     */
    private String jump;
    private String title;
    private String image;
    private int click_type;
    private String click_obj;

    @Override
    public String toString() {
        return "Fenlei{" +
                "jump='" + jump + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", click_type=" + click_type +
                ", click_obj='" + click_obj + '\'' +
                '}';
    }

    public Fenlei() {
    }

    public Fenlei(String jump, String title, String image, int click_type, String click_obj) {
        this.jump = jump;
        this.title = title;
        this.image = image;
        this.click_type = click_type;
        this.click_obj = click_obj;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
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

    public int getClick_type() {
        return click_type;
    }

    public void setClick_type(int click_type) {
        this.click_type = click_type;
    }

    public String getClick_obj() {
        return click_obj;
    }

    public void setClick_obj(String click_obj) {
        this.click_obj = click_obj;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.jump);
        dest.writeString(this.title);
        dest.writeString(this.image);
        dest.writeInt(this.click_type);
        dest.writeString(this.click_obj);
    }

    protected Fenlei(Parcel in) {
        this.jump = in.readString();
        this.title = in.readString();
        this.image = in.readString();
        this.click_type = in.readInt();
        this.click_obj = in.readString();
    }

    public static final Parcelable.Creator<Fenlei> CREATOR = new Parcelable.Creator<Fenlei>() {
        @Override
        public Fenlei createFromParcel(Parcel source) {
            return new Fenlei(source);
        }

        @Override
        public Fenlei[] newArray(int size) {
            return new Fenlei[size];
        }
    };
}
