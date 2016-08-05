package com.weibo.meishijie.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weibo.meishijie.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.R.attr.id;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static butterknife.ButterKnife.bind;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    protected abstract int getLayoutId();
    private Unbinder unbinder;
    protected View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(),container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null){
            unbinder.unbind();
        }
    }

    public BaseFragment() {}

    protected <T extends View> T findView(View v,int id){
        return (T) v.findViewById(id);
    }

    protected <T extends View> T findView(int id){
        return (T) view.findViewById(id);
    }

}
