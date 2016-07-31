package com.weibo.meishijie.bean.recommend;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class San_can implements Parcelable {

    /**
     * id : 597946
     * titlepic : http://images.meishij.net/p/20160728/bee6cfecc1663e905454a94643e6820d.jpg
     * title : 剩米饭的10种吃法
     * descr : 恭喜剩米饭找到了自己的最佳归属
     * click_type : 5
     * click_obj : 597946
     * pv_trackingURL :
     * click_trackingURL :
     * sft : 0
     * is_recipe : 0
     * is_tj : 1
     * tj_img : http://images.meishij.net/p/20131012/073052034bc0bc1d542d16de86d69841_150x150.jpg
     * fav_num : 8435
     * jump: "{"type":"51","class_name":"MSArticleDetailController","property":{"recipeId":"597946","goodsSource":""}}"
     */

    private int id;
    private String titlepic;
    private String title;
    private String descr;
    private int click_type;
    private String click_obj;
    private String pv_trackingURL;
    private String click_trackingURL;
    private int sft;
    private int is_recipe;
    private int is_tj;
    private String tj_img;
    private int fav_num;
    private String jump;

    @Override
    public String toString() {
        return "San_can{" +
                "id=" + id +
                ", titlepic='" + titlepic + '\'' +
                ", title='" + title + '\'' +
                ", descr='" + descr + '\'' +
                ", click_type=" + click_type +
                ", click_obj='" + click_obj + '\'' +
                ", pv_trackingURL='" + pv_trackingURL + '\'' +
                ", click_trackingURL='" + click_trackingURL + '\'' +
                ", sft=" + sft +
                ", is_recipe=" + is_recipe +
                ", is_tj=" + is_tj +
                ", tj_img='" + tj_img + '\'' +
                ", fav_num=" + fav_num +
                ", jump='" + jump + '\'' +
                '}';
    }

    public San_can() {
    }

    public San_can(int id, String titlepic, String title, String descr, int click_type, String click_obj, String pv_trackingURL, String click_trackingURL, int sft, int is_recipe, int is_tj, String tj_img, int fav_num, String jump) {
        this.id = id;
        this.titlepic = titlepic;
        this.title = title;
        this.descr = descr;
        this.click_type = click_type;
        this.click_obj = click_obj;
        this.pv_trackingURL = pv_trackingURL;
        this.click_trackingURL = click_trackingURL;
        this.sft = sft;
        this.is_recipe = is_recipe;
        this.is_tj = is_tj;
        this.tj_img = tj_img;
        this.fav_num = fav_num;
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

    public String getTitlepic() {
        return titlepic;
    }

    public void setTitlepic(String titlepic) {
        this.titlepic = titlepic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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

    public int getSft() {
        return sft;
    }

    public void setSft(int sft) {
        this.sft = sft;
    }

    public int getIs_recipe() {
        return is_recipe;
    }

    public void setIs_recipe(int is_recipe) {
        this.is_recipe = is_recipe;
    }

    public int getIs_tj() {
        return is_tj;
    }

    public void setIs_tj(int is_tj) {
        this.is_tj = is_tj;
    }

    public String getTj_img() {
        return tj_img;
    }

    public void setTj_img(String tj_img) {
        this.tj_img = tj_img;
    }

    public int getFav_num() {
        return fav_num;
    }

    public void setFav_num(int fav_num) {
        this.fav_num = fav_num;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.titlepic);
        dest.writeString(this.title);
        dest.writeString(this.descr);
        dest.writeInt(this.click_type);
        dest.writeString(this.click_obj);
        dest.writeString(this.pv_trackingURL);
        dest.writeString(this.click_trackingURL);
        dest.writeInt(this.sft);
        dest.writeInt(this.is_recipe);
        dest.writeInt(this.is_tj);
        dest.writeString(this.tj_img);
        dest.writeInt(this.fav_num);
        dest.writeString(this.jump);
    }

    protected San_can(Parcel in) {
        this.id = in.readInt();
        this.titlepic = in.readString();
        this.title = in.readString();
        this.descr = in.readString();
        this.click_type = in.readInt();
        this.click_obj = in.readString();
        this.pv_trackingURL = in.readString();
        this.click_trackingURL = in.readString();
        this.sft = in.readInt();
        this.is_recipe = in.readInt();
        this.is_tj = in.readInt();
        this.tj_img = in.readString();
        this.fav_num = in.readInt();
        this.jump = in.readString();
    }

    public static final Parcelable.Creator<San_can> CREATOR = new Parcelable.Creator<San_can>() {
        @Override
        public San_can createFromParcel(Parcel source) {
            return new San_can(source);
        }

        @Override
        public San_can[] newArray(int size) {
            return new San_can[size];
        }
    };
}
