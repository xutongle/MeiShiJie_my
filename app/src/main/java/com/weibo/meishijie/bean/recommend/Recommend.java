package com.weibo.meishijie.bean.recommend;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class Recommend implements Parcelable {
    /**
     * code: "11",
     obj: {}
     */
    private int code;
    private Obj obj;

    public Recommend() {
    }

    public Recommend(int code, Obj obj) {
        this.code = code;
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "TuiJian{" +
                "code=" + code +
                ", obj=" + obj +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeParcelable(this.obj, flags);
    }

    protected Recommend(Parcel in) {
        this.code = in.readInt();
        this.obj = in.readParcelable(Obj.class.getClassLoader());
    }

    public static final Parcelable.Creator<Recommend> CREATOR = new Parcelable.Creator<Recommend>() {
        @Override
        public Recommend createFromParcel(Parcel source) {
            return new Recommend(source);
        }

        @Override
        public Recommend[] newArray(int size) {
            return new Recommend[size];
        }
    };
}
