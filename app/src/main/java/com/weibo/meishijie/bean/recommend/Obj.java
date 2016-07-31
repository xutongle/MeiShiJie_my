package com.weibo.meishijie.bean.recommend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class Obj implements Parcelable {
    private San_can[] san_can;
    private San_can_titles[] san_can_titles;
    private Fenlei[] fenlei;
    private Fun1 func1;
    private Fun2 func2;
    private Top3[] top3;
    private Top4[] top4;
    private Shops[] shops;
    private Zt[] zt;
    private Customized customized;

    public San_can[] getSan_can() {
        return san_can;
    }

    public void setSan_can(San_can[] san_can) {
        this.san_can = san_can;
    }

    public San_can_titles[] getSan_can_titles() {
        return san_can_titles;
    }

    public void setSan_can_titles(San_can_titles[] san_can_titles) {
        this.san_can_titles = san_can_titles;
    }

    public Fenlei[] getFenlei() {
        return fenlei;
    }

    public void setFenlei(Fenlei[] fenlei) {
        this.fenlei = fenlei;
    }

    public Fun1 getFunc1() {
        return func1;
    }

    public void setFunc1(Fun1 func1) {
        this.func1 = func1;
    }

    public Fun2 getFunc2() {
        return func2;
    }

    public void setFunc2(Fun2 func2) {
        this.func2 = func2;
    }

    public Top3[] getTop3() {
        return top3;
    }

    public void setTop3(Top3[] top3) {
        this.top3 = top3;
    }

    public Top4[] getTop4() {
        return top4;
    }

    public void setTop4(Top4[] top4) {
        this.top4 = top4;
    }

    public Shops[] getShops() {
        return shops;
    }

    public void setShops(Shops[] shops) {
        this.shops = shops;
    }

    public Zt[] getZt() {
        return zt;
    }

    public void setZt(Zt[] zt) {
        this.zt = zt;
    }

    public Customized getCustomized() {
        return customized;
    }

    public void setCustomized(Customized customized) {
        this.customized = customized;
    }

    @Override
    public String toString() {
        return "Obj{" +
                "san_can=" + Arrays.toString(san_can) +
                ", san_can_titles=" + Arrays.toString(san_can_titles) +
                ", fenlei=" + Arrays.toString(fenlei) +
                ", func1=" + func1 +
                ", func2=" + func2 +
                ", top3=" + Arrays.toString(top3) +
                ", top4=" + Arrays.toString(top4) +
                ", shops=" + Arrays.toString(shops) +
                ", zt=" + Arrays.toString(zt) +
                ", customized=" + customized +
                '}';
    }

    public Obj() {
    }

    public Obj(San_can[] san_can, San_can_titles[] san_can_titles, Fenlei[] fenlei, Fun1 func1, Fun2 func2, Top3[] top3, Top4[] top4, Shops[] shops, Zt[] zt, Customized customized) {
        this.san_can = san_can;
        this.san_can_titles = san_can_titles;
        this.fenlei = fenlei;
        this.func1 = func1;
        this.func2 = func2;
        this.top3 = top3;
        this.top4 = top4;
        this.shops = shops;
        this.zt = zt;
        this.customized = customized;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(this.san_can, flags);
        dest.writeTypedArray(this.san_can_titles, flags);
        dest.writeTypedArray(this.fenlei, flags);
        dest.writeParcelable(this.func1, flags);
        dest.writeParcelable(this.func2, flags);
        dest.writeTypedArray(this.top3, flags);
        dest.writeTypedArray(this.top4, flags);
        dest.writeTypedArray(this.shops, flags);
        dest.writeTypedArray(this.zt, flags);
        dest.writeParcelable(this.customized, flags);
    }

    protected Obj(Parcel in) {
        this.san_can = in.createTypedArray(San_can.CREATOR);
        this.san_can_titles = in.createTypedArray(San_can_titles.CREATOR);
        this.fenlei = in.createTypedArray(Fenlei.CREATOR);
        this.func1 = in.readParcelable(Fun1.class.getClassLoader());
        this.func2 = in.readParcelable(Fun2.class.getClassLoader());
        this.top3 = in.createTypedArray(Top3.CREATOR);
        this.top4 = in.createTypedArray(Top4.CREATOR);
        this.shops = in.createTypedArray(Shops.CREATOR);
        this.zt = in.createTypedArray(Zt.CREATOR);
        this.customized = in.readParcelable(Customized.class.getClassLoader());
    }

    public static final Parcelable.Creator<Obj> CREATOR = new Parcelable.Creator<Obj>() {
        @Override
        public Obj createFromParcel(Parcel source) {
            return new Obj(source);
        }

        @Override
        public Obj[] newArray(int size) {
            return new Obj[size];
        }
    };
}
