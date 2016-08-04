package com.weibo.meishijie.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.weibo.meishijie.R;
import com.weibo.meishijie.base.BaseFragment;
import com.weibo.meishijie.bean.recommend.Obj;
import com.weibo.meishijie.bean.recommend.San_can;
import com.weibo.meishijie.bean.recommend.San_can_titles;
import com.weibo.meishijie.util.CacheUtil;
import com.weibo.meishijie.util.DLog;
import com.weibo.meishijie.view.custom.IrregularImageView;

import butterknife.BindView;

import static com.weibo.meishijie.R.id.irr_iv;

public class Fragment_san_can extends BaseFragment {

    @BindView(R.id.irr_iv)
    IrregularImageView irr_iv;
    @BindView(R.id.scan_can_title)
    TextView scanCanTitle;
    @BindView(R.id.scan_can_subtitle)
    TextView scanCanSubtitle;

    public static final String CUURRENTPAGER = "currentpager",OBJ_TAG = "obj";
    private int current,c;
    private Obj obj;

    private void initView(){
        San_can[] san_can = obj.getSan_can();
        San_can_titles[] san_can_titles = obj.getSan_can_titles();

        c = current * 3;
        San_can san_can1 = san_can[c];
        San_can san_can2 = san_can[c + 1];
        San_can san_can3 = san_can[c + 2];
        San_can_titles san_can_title = san_can_titles[current];

        irr_iv.setAllTitleAndDescr(san_can1.getTitle(),san_can1.getDescr(),san_can2.getTitle(),
                san_can2.getDescr(),san_can3.getTitle(),san_can3.getDescr());

        irr_iv.setTitlePic(san_can1.getTitlepic(),san_can2.getTitlepic(),san_can3.getTitlepic());

        scanCanTitle.setText(san_can_title.getTitle());
        scanCanSubtitle.setText(san_can_title.getSub_title());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        irr_iv.recycle();
    }

    @Override
    public void onPause() {
        super.onPause();
        CacheUtil.fluchCache();
    }
}
