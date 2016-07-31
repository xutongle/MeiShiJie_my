package com.weibo.meishijie.bean.recommend;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class Top3 extends Top4 implements Parcelable {

    /**
     * photo : http://site.meishij.net/adm/additive/2016-07-27/579877516f031.jpg
     * click_type : 4
     * click_obj : APP首页轮播-黑刃刀;http://i.meishi.cc/g/transit.php?ename=1733&from=mobile_android
     * pv_trackingURL :
     * click_trackingURL :
     * is_recipe : 0
     * sft : 0
     * aid : 293
     */

    private int aid;

    public Top3() {
    }

    public Top3(String photo, int click_type, String click_obj, String pv_trackingURL, String click_trackingURL, int is_recipe, int sft, String jump, int aid) {
        super(photo, click_type, click_obj, pv_trackingURL, click_trackingURL, is_recipe, sft, jump);
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "Top3{" +
                "aid=" + aid +
                '}';
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.aid);
    }

    protected Top3(Parcel in) {
        this.aid = in.readInt();
    }

    public static final Parcelable.Creator<Top3> CREATOR = new Parcelable.Creator<Top3>() {
        @Override
        public Top3 createFromParcel(Parcel source) {
            return new Top3(source);
        }

        @Override
        public Top3[] newArray(int size) {
            return new Top3[size];
        }
    };
}
