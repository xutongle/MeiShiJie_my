package com.weibo.meishijie.view.custom;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.weibo.meishijie.R;

/**
 * Created by Administrator on 2016/6/8.
 */
public class ADViewpager_san_can extends FrameLayout implements ViewPager.OnPageChangeListener {

    private Context context;
    private int frontRes, reversRes, count, iconLeft = 5, iconRight = 5;
    private LinearLayout linearLayout;
    private ViewPager viewPager;
    private ImageView lastImageView;
    private ViewGroup.LayoutParams IconlayoutParams = new ViewGroup.LayoutParams(20, 20);

    public void setAdapter(PagerAdapter pagerAdapter) {
        viewPager.setAdapter(pagerAdapter);
        count = pagerAdapter.getCount();
        build();
    }

    public void setIconGravity(int gravity) {
        linearLayout.setGravity(gravity);
    }

    public void setIcon(int frontRes, int reversRes) {
        this.frontRes = frontRes;
        this.reversRes = reversRes;
    }

    public void setCurrentItem(int currentItem) {
        viewPager.setCurrentItem(currentItem);
    }

    private void init(Context context) {
        this.context = context;
        inflate(context, R.layout.viewpager, this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        linearLayout = (LinearLayout) findViewById(R.id.linear_icon);
    }

    private void build() {
        viewPager.addOnPageChangeListener(this);
        int icons = count;
        for (int i = 0; i < icons; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(IconlayoutParams);
            imageView.setPadding(iconLeft, 0, iconRight, 0);
            imageView.setImageResource(reversRes);
            linearLayout.addView(imageView);
        }

        viewPager.setCurrentItem(1);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        if (lastImageView != null) {
            lastImageView.setImageResource(reversRes);
        }
        ImageView imageView = (ImageView) linearLayout.getChildAt(position);
        imageView.setImageResource(frontRes);
        lastImageView = imageView;
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    public ADViewpager_san_can(Context context) {
        super(context);
        init(context);
    }

    public ADViewpager_san_can(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ADViewpager_san_can(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

}
