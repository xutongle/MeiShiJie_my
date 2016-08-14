package com.weibo.meishijie.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.widget.RadioGroup;

import com.weibo.meishijie.R;
import com.weibo.meishijie.base.BaseActivity;
import com.weibo.meishijie.util.CacheUtil;
import com.weibo.meishijie.util.Constant;
import com.weibo.meishijie.util.MainApp;
import com.weibo.meishijie.view.fragment.Fragment_discover;
import com.weibo.meishijie.view.fragment.Fragment_shop;
import com.weibo.meishijie.view.fragment.Fragment_topic;
import com.weibo.meishijie.view.fragment.Fragment_wode;
import com.weibo.meishijie.view.fragment.recommend.Fragment_recommend;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.navigationGroup)
    RadioGroup rg;

    private FragmentManager fm;
    private Fragment lastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
        rg.setOnCheckedChangeListener(this);
        rg.check(R.id.tuijian);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i){
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.tuijian:
                showFragment(Constant.RECOMMENT);
                break;
            case R.id.faxain:
                showFragment(Constant.DISCOVER);
                break;
            case R.id.shangcheng:
                showFragment(Constant.SHOP);
                break;
            case R.id.shihua:
                showFragment(Constant.TOPIC);
                break;
            case R.id.wode:
                showFragment(Constant.WODE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (fm.getBackStackEntryCount() == 0){
            new AlertDialog.Builder(this)
                    .setCancelable(true)
                    .setTitle("提示")
                    .setMessage("确定退出吗？")
                    .setPositiveButton("确定", (dialogInterface, i) -> {
                        exit();
                    })
                    .setNeutralButton("取消", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                    })
                    .show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheUtil.clearMemoryCache();
        MainApp.destroyPool();
        MainApp.closeSp();
    }

    private void showFragment(String tag) {
        hide(lastFragment);
        Fragment fi = fm.findFragmentByTag(tag);
        if (fi == null) {
            if (tag.equals(Constant.RECOMMENT)) {
                fi = Fragment_recommend.newInstance();
            } else if (tag.equals(Constant.DISCOVER)) {
                fi = Fragment_discover.newInstance();
            } else if (tag.equals(Constant.SHOP)) {
                fi = Fragment_shop.newInstance();
            } else if (tag.equals(Constant.TOPIC)) {
                fi = Fragment_topic.newInstance();
            }else if (tag.equals(Constant.WODE)) {
                fi = Fragment_wode.newInstance();
            }
            fm.beginTransaction().add(R.id.main_fragmentGroup, fi, tag).commit();
        } else {
            show(fi);
        }
        lastFragment = fi;
    }

    private void hide(Fragment fragment) {
        if (fragment != null) {
            fm.beginTransaction().hide(fragment).commit();
        }
    }

    private void show(Fragment fragment) {
        if (fragment != null) {
            fm.beginTransaction().show(fragment).commit();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}
