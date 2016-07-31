package com.weibo.meishijie.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;
    private static List<Activity> list = new ArrayList<>();

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        list.add(this);
    }

    protected void exit(){
        for (Activity a : list) {
                a.finish();
        }
    }

    protected void remove(Activity activity){
        list.remove(activity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

}
