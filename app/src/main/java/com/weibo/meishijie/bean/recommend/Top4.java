package com.weibo.meishijie.bean.recommend;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class Top4 implements Parcelable {

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

    private String photo;
    private int click_type;
    private String click_obj;
    private String pv_trackingURL;
    private String click_trackingURL;
    private int is_recipe;
    private int sft;
    private String jump;

    @Override
    public String toString() {
        return "Top4{" +
                "photo='" + photo + '\'' +
                ", click_type=" + click_type +
                ", click_obj='" + click_obj + '\'' +
                ", pv_trackingURL='" + pv_trackingURL + '\'' +
                ", click_trackingURL='" + click_trackingURL + '\'' +
                ", is_recipe=" + is_recipe +
                ", sft=" + sft +
                ", jump='" + jump + '\'' +
                '}';
    }

    public Top4() {
    }

    public Top4(String photo, int click_type, String click_obj, String pv_trackingURL, String click_trackingURL, int is_recipe, int sft,String jump) {
        this.photo = photo;
        this.click_type = click_type;
        this.click_obj = click_obj;
        this.pv_trackingURL = pv_trackingURL;
        this.click_trackingURL = click_trackingURL;
        this.is_recipe = is_recipe;
        this.sft = sft;
        this.jump = jump;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getPv_trackingURL() {
        return pv_trackingURL;
    }

    public void setPv_trackingURL(String pv_trackingURL) {
        this.pv_trackingURL = pv_trackingURL;
    }

    public String getClick_trackingURL() {
        return click_trackingURL;
    }

    public void setClick_trackingURL(String click_trackingURL) {
        this.click_trackingURL = click_trackingURL;
    }

    public int getIs_recipe() {
        return is_recipe;
    }

    public void setIs_recipe(int is_recipe) {
        this.is_recipe = is_recipe;
    }

    public int getSft() {
        return sft;
    }

    public void setSft(int sft) {
        this.sft = sft;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photo);
        dest.writeInt(this.click_type);
        dest.writeString(this.click_obj);
        dest.writeString(this.pv_trackingURL);
        dest.writeString(this.click_trackingURL);
        dest.writeInt(this.is_recipe);
        dest.writeInt(this.sft);
        dest.writeString(this.jump);
    }

    protected Top4(Parcel in) {
        this.photo = in.readString();
        this.click_type = in.readInt();
        this.click_obj = in.readString();
        this.pv_trackingURL = in.readString();
        this.click_trackingURL = in.readString();
        this.is_recipe = in.readInt();
        this.sft = in.readInt();
        this.jump = in.readString();
    }

    public static final Parcelable.Creator<Top4> CREATOR = new Parcelable.Creator<Top4>() {
        @Override
        public Top4 createFromParcel(Parcel source) {
            return new Top4(source);
        }

        @Override
        public Top4[] newArray(int size) {
            return new Top4[size];
        }
    };
}
