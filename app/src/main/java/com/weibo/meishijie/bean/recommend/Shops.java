package com.weibo.meishijie.bean.recommend;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class Shops implements Parcelable {

    /**
     * id : 543
     * title : 原磨新养 葛根粉 2瓶
     * price : 42
     * guige : 300g*2瓶
     * image : http://site.meishij.net/shop/uploadfile/20160425/20160425161612.jpg
     * jump: "{"type":"10","class_name":"MSBuyGoodsDetailController","property":{"goodsID":"543","sourceContent":"index","goodsSource":"2,543"}}"
     */

    private int id;
    private String title;
    private String price;
    private String guige;
    private String image;
    private String jump;

    @Override
    public String toString() {
        return "Shops{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", guige='" + guige + '\'' +
                ", image='" + image + '\'' +
                ", jump='" + jump + '\'' +
                '}';
    }

    public Shops() {
    }

    public Shops(int id, String title, String price, String guige, String image, String jump) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.guige = guige;
        this.image = image;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGuige() {
        return guige;
    }

    public void setGuige(String guige) {
        this.guige = guige;
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
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.price);
        dest.writeString(this.guige);
        dest.writeString(this.image);
        dest.writeString(this.jump);
    }

    protected Shops(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.price = in.readString();
        this.guige = in.readString();
        this.image = in.readString();
        this.jump = in.readString();
    }

    public static final Parcelable.Creator<Shops> CREATOR = new Parcelable.Creator<Shops>() {
        @Override
        public Shops createFromParcel(Parcel source) {
            return new Shops(source);
        }

        @Override
        public Shops[] newArray(int size) {
            return new Shops[size];
        }
    };
}
