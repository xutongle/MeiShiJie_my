package com.weibo.meishijie.base.recyclerView;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weibo.meishijie.util.ImageLoader;
import com.weibo.meishijie.util.MainApp;

import static android.R.attr.id;

/**
 * Created by Administrator on 2016/7/9.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    private View itemView;
    private Context context;

    private SparseArrayCompat<View> views = new SparseArrayCompat<>();

    public ViewHolder(View itemView,Context context) {
        super(itemView);
        this.itemView = itemView;
        this.context = context;
    }

    public static ViewHolder createViewHolder(Context context,View itemView){
        ViewHolder holder = new ViewHolder(itemView,context);
        return holder;
    }

    public static ViewHolder createViewHolder(Context context, int LaoutId, ViewGroup parent){
        View view = LayoutInflater.from(context).inflate(LaoutId,parent,false);
        ViewHolder holder = new ViewHolder(view, context);
        return holder;
    }

    public <T extends View> T getView(int id){
        View view = views.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            views.put(id,view);
        }
        return (T) view;
    }

    public void setText(int id,String text){
        TextView textView = getView(id);
        textView.setText(text);
    }

    public void setTextAddColor(int id,String text,int color){
        TextView textView = getView(id);
        textView.setText(text);
        textView.setTextColor(color);
    }

    public void setImageViewSrc(int id,String url){
        ImageView imageView = getView(id);
        ImageLoader.load(MainApp.getAppContext(),url,imageView);
    }

}
