package com.weibo.meishijie.view.custom;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/6/9.
 */
public class JumpViewPager extends ViewPager {

    private OnPagerChangeListener onPagerChangeListener;
    private OnEdgeFinishedListener onEdgeFinishedListener;
    private OnTouchListener onTouchListener;

    private int currentPosition,count,lastX;

    public JumpViewPager(Context context) {
        super(context);
        init();
    }

    public JumpViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        count = adapter.getCount();
    }

    private void init(){
        this.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (onPagerChangeListener != null) {
                    onPagerChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                if (onPagerChangeListener != null) {
                    onPagerChangeListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (onPagerChangeListener != null) {
                    onPagerChangeListener.onPageScrollStateChanged(state);
                }
            }
        });
        this.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    lastX = (int)event.getX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (onEdgeFinishedListener != null) {
                        if ((lastX - event.getX()) > 100 && (currentPosition == count - 1)) {
                            onEdgeFinishedListener.OnRightEdgeFinished(v);
                        }
                        if ((event.getX() - lastX) > 100 && (currentPosition == 0)) {
                            onEdgeFinishedListener.OnleftEdgeFinished(v);
                        }
                    }
                    break;
                default:
                    break;
            }
            if (onTouchListener != null){
                onTouchListener.onTouch(v);
            }
            return false;
        });
    }

    public void setOnPagerChangeListener(OnPagerChangeListener onPagerChangeListener) {
        this.onPagerChangeListener = onPagerChangeListener;
    }

    public interface OnPagerChangeListener {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }

    public void setOnEdgeFinishedListener(OnEdgeFinishedListener onEdgeFinishedListener) {
        this.onEdgeFinishedListener = onEdgeFinishedListener;
    }

    public interface OnEdgeFinishedListener {
        void OnRightEdgeFinished(View view);
        void OnleftEdgeFinished(View view);
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    public interface OnTouchListener {
        void onTouch(View view);
    }

}
