package com.weibo.meishijie.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.weibo.meishijie.R;

public class NavigationBar_RadioButton extends RadioButton {

    private int mDrawableSize;// xml文件中设置的大小
    private Drawable drawableLeft = null, drawableTop = null, drawableRight = null, drawableBottom = null;

    public void setmDrawableSize(int mDrawableSize) {
        this.mDrawableSize = mDrawableSize;
    }

    public void setDrawableLeft(Drawable drawableLeft) {
        this.drawableLeft = drawableLeft;
    }

    public void setDrawableTop(Drawable drawableTop) {
        this.drawableTop = drawableTop;
    }

    public void setDrawableRight(Drawable drawableRight) {
        this.drawableRight = drawableRight;
    }

    public void setDrawableBottom(Drawable drawableBottom) {
        this.drawableBottom = drawableBottom;
    }

    public NavigationBar_RadioButton(Context context) {
        this(context, null, 0);
    }

    public NavigationBar_RadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationBar_RadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);//TODO Auto-generated constructor stub
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NavigationBar_RadioButton);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.NavigationBar_RadioButton_DrawableSize:
                    mDrawableSize = a.getDimensionPixelSize(
                            R.styleable.NavigationBar_RadioButton_DrawableSize, 0);
                    break;
                case R.styleable.NavigationBar_RadioButton_DrawableTop:
                    drawableTop = a.getDrawable(attr);
                    break;
                case R.styleable.NavigationBar_RadioButton_DrawableRight:
                    drawableRight = a.getDrawable(attr);
                    break;
                case R.styleable.NavigationBar_RadioButton_DrawableBottom:
                    drawableBottom = a.getDrawable(attr);
                    break;
                case R.styleable.NavigationBar_RadioButton_DrawableLeft:
                    drawableLeft = a.getDrawable(attr);
                    break;
                default:
                    break;
            }
        }
        //回收
        a.recycle();
        setCompoundDrawablesWithIntrinsicBounds(
                drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top,Drawable right, Drawable bottom) {
        if (mDrawableSize == 0){
            mDrawableSize = 30;
        }
        if (left != null) {
            left.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (right != null) {
            right.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (top != null) {
            top.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (bottom != null) {
            bottom.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        setCompoundDrawables(left, top, right, bottom);
    }
}