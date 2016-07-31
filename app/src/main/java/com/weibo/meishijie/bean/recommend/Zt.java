package com.weibo.meishijie.bean.recommend;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class Zt implements Parcelable {

    /**
     * id : 1691833
     * title :
     * type : 4
     * tj_type : 0
     * f_s_type : http://m.meishij.net/html5/news.php?id=1691833
     * photo : http://images.meishij.net/p/20160727/6783babe20eefd85b7803e17444a6ea5.jpg
     * descr :
     * jump: "{"type":"51","class_name":"MSArticleDetailController","property":{"recipeId":"1691833","goodsSource":"4,1691833"}}"
     */

    private int id;
    private String title;
    private int type;
    private int tj_type;
    private String f_s_type;
    private String photo;
    private String descr;
    private String jump;

    @Override
    public String toString() {
        return "Zt{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", tj_type=" + tj_type +
                ", f_s_type='" + f_s_type + '\'' +
                ", photo='" + photo + '\'' +
                ", descr='" + descr + '\'' +
                ", jump='" + jump + '\'' +
                '}';
    }

    public Zt() {
    }

    public Zt(int id, String title, int type, int tj_type, String f_s_type, String photo, String descr, String jump) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.tj_type = tj_type;
        this.f_s_type = f_s_type;
        this.photo = photo;
        this.descr = descr;
        this.jump = jump;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTj_type() {
        return tj_type;
    }

    public void setTj_type(int tj_type) {
        this.tj_type = tj_type;
    }

    public String getF_s_type() {
        return f_s_type;
    }

    public void setF_s_type(String f_s_type) {
        this.f_s_type = f_s_type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.type);
        dest.writeInt(this.tj_type);
        dest.writeString(this.f_s_type);
        dest.writeString(this.photo);
        dest.writeString(this.descr);
        dest.writeString(this.jump);
    }

    protected Zt(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.type = in.readInt();
        this.tj_type = in.readInt();
        this.f_s_type = in.readString();
        this.photo = in.readString();
        this.descr = in.readString();
        this.jump = in.readString();
    }

    public static final Parcelable.Creator<Zt> CREATOR = new Parcelable.Creator<Zt>() {
        @Override
        public Zt createFromParcel(Parcel source) {
            return new Zt(source);
        }

        @Override
        public Zt[] newArray(int size) {
            return new Zt[size];
        }
    };
}
