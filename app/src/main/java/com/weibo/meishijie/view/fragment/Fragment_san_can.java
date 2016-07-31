package com.weibo.meishijie.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.weibo.meishijie.R;
import com.weibo.meishijie.base.BaseFragment;
import com.weibo.meishijie.bean.recommend.Obj;
import com.weibo.meishijie.bean.recommend.San_can;
import com.weibo.meishijie.bean.recommend.San_can_titles;
import com.weibo.meishijie.util.DLog;
import com.weibo.meishijie.util.ImageLoader;
import com.weibo.meishijie.util.MainApp;

import butterknife.BindView;

public class Fragment_san_can extends BaseFragment {

    @BindView(R.id.titlepic1)
    ImageView titlepic1;
    @BindView(R.id.subtitle1)
    TextView subtitle1;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.titlepic2)
    ImageView titlepic2;
    @BindView(R.id.subtitle2)
    TextView subtitle2;
    @BindView(R.id.title2)
    TextView title2;
    @BindView(R.id.titlepic3)
    ImageView titlepic3;
    @BindView(R.id.subtitle3)
    TextView subtitle3;
    @BindView(R.id.title3)
    TextView title3;
    @BindView(R.id.scan_can_title)
    TextView scanCanTitle;
    @BindView(R.id.scan_can_subtitle)
    TextView scanCanSubtitle;

    public static final String CUURRENTPAGER = "currentpager",OBJ_TAG = "obj";
    private int current;
    private Obj obj;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        //subtitle一个对应三张图片，例如第一个subtitle对应0,1,2，第二个对应3,4,5
        San_can[] san_can = obj.getSan_can();
        San_can_titles[] san_can_titles = obj.getSan_can_titles();
        int length = san_can_titles.length;
        if (current == length + 1){
            current = 0;
        }else if (current == 0){
            current = length - 1;
        }else {
            current -= 1;
        }
        int i = (current) * 3;

        San_can san_can1 = san_can[i];
        title1.setText(san_can1.getTitle());
        subtitle1.setText(san_can1.getDescr());
        ImageLoader.load(MainApp.getAppContext(),san_can1.getTitlepic(),titlepic1);

        San_can san_can2 = san_can[i+1];
        title2.setText(san_can2.getTitle());
        subtitle2.setText(san_can2.getDescr());
        ImageLoader.load(MainApp.getAppContext(),san_can2.getTitlepic(),titlepic2);

        San_can san_can3 = san_can[i+2];
        title3.setText(san_can3.getTitle());
        subtitle3.setText(san_can3.getDescr());
        ImageLoader.load(MainApp.getAppContext(),san_can3.getTitlepic(),titlepic3);

        setTextColor(title1,subtitle1,title2,subtitle2,title3,subtitle3);

        San_can_titles san_can_title = san_can_titles[current];
        scanCanTitle.setText(san_can_title.getTitle());
        scanCanSubtitle.setText(san_can_title.getSub_title());
    }

    private void setTextColor(TextView...views){
        for (TextView v : views) {
            v.setTextColor(Color.WHITE);
        }
    }

    public static Fragment_san_can newInstance(int current, Obj obj) {
        Fragment_san_can fragment = new Fragment_san_can();
        Bundle bundle = new Bundle();
        bundle.putInt(CUURRENTPAGER,current);
        bundle.putParcelable(OBJ_TAG,obj);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            current = bundle.getInt(CUURRENTPAGER);
            obj = bundle.getParcelable(OBJ_TAG);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_san_can;
    }

    public Fragment_san_can() {}

}
