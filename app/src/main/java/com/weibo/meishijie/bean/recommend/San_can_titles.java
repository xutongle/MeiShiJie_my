package com.weibo.meishijie.bean.recommend;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class San_can_titles implements Parcelable {

    /**
     * title : 早餐
     * sub_title : 剩饭也疯狂，傲娇脸.jpg
     * titlepic : http://i5.meishij.net/app/mobile/guyu_icon_1.png
     * click_type : 0
     * click_obj :
     * pv_trackingURL :
     * click_trackingURL :
     */

    private String title;
    private String sub_title;
    private String titlepic;
    private int click_type;
    private String click_obj;
    private String pv_trackingURL;
    private String click_trackingURL;

    @Override
    public String toString() {
        return "San_can_titles{" +
                "title='" + title + '\'' +
                ", sub_title='" + sub_title + '\'' +
                ", titlepic='" + titlepic + '\'' +
                ", click_type=" + click_type +
                ", click_obj='" + click_obj + '\'' +
                ", pv_trackingURL='" + pv_trackingURL + '\'' +
                ", click_trackingURL='" + click_trackingURL + '\'' +
                '}';
    }

    public San_can_titles() {
    }

    public San_can_titles(String title, String sub_title, String titlepic, int click_type, String click_obj, String pv_trackingURL, String click_trackingURL) {
        this.title = title;
        this.sub_title = sub_title;
        this.titlepic = titlepic;
        this.click_type = click_type;
        this.click_obj = click_obj;
        this.pv_trackingURL = pv_trackingURL;
        this.click_trackingURL = click_trackingURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getTitlepic() {
        return titlepic;
    }

    public void setTitlepic(String titlepic) {
        this.titlepic = titlepic;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.sub_title);
        dest.writeString(this.titlepic);
        dest.writeInt(this.click_type);
        dest.writeString(this.click_obj);
        dest.writeString(this.pv_trackingURL);
        dest.writeString(this.click_trackingURL);
    }

    protected San_can_titles(Parcel in) {
        this.title = in.readString();
        this.sub_title = in.readString();
        this.titlepic = in.readString();
        this.click_type = in.readInt();
        this.click_obj = in.readString();
        this.pv_trackingURL = in.readString();
        this.click_trackingURL = in.readString();
    }

    public static final Parcelable.Creator<San_can_titles> CREATOR = new Parcelable.Creator<San_can_titles>() {
        @Override
        public San_can_titles createFromParcel(Parcel source) {
            return new San_can_titles(source);
        }

        @Override
        public San_can_titles[] newArray(int size) {
            return new San_can_titles[size];
        }
    };
}
