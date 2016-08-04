package com.weibo.meishijie.view.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.weibo.meishijie.R;
import com.weibo.meishijie.util.BitmapUtil;
import com.weibo.meishijie.util.CacheUtil;
import com.weibo.meishijie.util.DLog;
import com.weibo.meishijie.util.MainApp;
import com.weibo.meishijie.util.OkUtil;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 巴巴 on 2016/8/1.
 */

public class IrregularImageView extends View {

    private Bitmap bitmap1, bitmap2, bitmap3;
    private float briefLength = 10, itemSpace = 5, titleTextSize = 40, descrTextSize = 25;
    private String titlePic1, titlePic2, titlePic3, title1, descr1, title2, descr2, title3, descr3;
    private Paint painText, paint1, paint2, paint3;
    private Path path1, path2, path3;
    private int textColor = Color.WHITE;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!bitmapIsNull(bitmap1, bitmap2, bitmap3)) {
            //设置画布没有锯齿
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));

            int h = getHeight();
            int w = getWidth();
            float hItem = (h - itemSpace * 2) / 3;

            getItem1(hItem, w, canvas, h);

            if (!isEmpty(title1, descr1, title2, descr2, title3, descr3)) {
                setItemTitleText(canvas, hItem, w, h);
            }

        }
    }

    public void recycle() {
        recycle(bitmap1, bitmap2, bitmap3);
    }

    private void recycle(Bitmap... bitmaps) {
        for (Bitmap b : bitmaps) {
            if (b != null && !b.isRecycled()) {
                b.recycle();
            }
        }
    }

    private void loadBitmap() {
        Runnable runnable = () -> {
            int requestW = getWidth();
            int requestH = (int) ((getHeight() - itemSpace) / 3 + briefLength);
            int requestH2 = (int) ((getHeight() - itemSpace) / 3 + briefLength * 2);

            bitmap1 = CacheUtil.getCacheBitmap(titlePic1,requestW,requestH);
            bitmap2 = CacheUtil.getCacheBitmap(titlePic2,requestW,requestH2);
            bitmap3 = CacheUtil.getCacheBitmap(titlePic3,requestW,requestH);

            postInvalidate();
        };
        MainApp.getService().execute(runnable);
    }

    private boolean bitmapIsNull(Bitmap... bitmaps) {
        for (Bitmap b : bitmaps) {
            if (b == null) {
                return true;
            }
        }
        return false;
    }

    private void setItemTitleText(Canvas canvas, float hItem, int w, int h) {
        painText.setColor(textColor);

        float y1 = hItem - 70;
        float y2 = hItem * 2 - 70 + briefLength;
        float y3 = h - 70;

        painText.setTextSize(titleTextSize);
        canvas.drawText(title1, w - title1.length() * titleTextSize - 5, y1, painText);
        canvas.drawText(title2, 5, y2, painText);
        canvas.drawText(title3, w - title3.length() * titleTextSize - 5, y3, painText);

        painText.setTextSize(descrTextSize);
        canvas.drawText(descr1, w - descr1.length() * descrTextSize - 5, y1 + titleTextSize, painText);
        canvas.drawText(descr2, 5, y2 + titleTextSize, painText);
        canvas.drawText(descr3, w - descr3.length() * descrTextSize - 5, y3 + titleTextSize, painText);

        painText.reset();
    }

    private boolean isEmpty(String... texts) {
        for (String text : texts) {
            if (TextUtils.isEmpty(text)) {
                return true;
            }
        }
        return false;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setBriefLength(float briefLength) {
        this.briefLength = briefLength;
    }

    public void setItemSpace(float itemSpace) {
        this.itemSpace = itemSpace;
    }

    public void setTitleTextSize(float titleTextSize) {
        this.titleTextSize = titleTextSize;
    }

    public void setDescrTextSize(float descrTextSize) {
        this.descrTextSize = descrTextSize;
    }

    public void setAllTitleAndDescr(String title1, String descr1, String title2, String descr2, String title3, String descr3) {
        this.title1 = title1;
        this.descr1 = descr1;
        this.title2 = title2;
        this.descr2 = descr2;
        this.title3 = title3;
        this.descr3 = descr3;
    }

    public void setTitlePic(String titlePic1, String titlePic2, String titlePic3) {
        this.titlePic1 = titlePic1;
        this.titlePic2 = titlePic2;
        this.titlePic3 = titlePic3;
        loadBitmap();
    }

    private void getItem1(final float hItem, final int w, final Canvas canvas, final int h) {
        BitmapShader shader1 = new BitmapShader(bitmap1, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        paint1.setAntiAlias(true);
        paint1.setShader(shader1);

        //第一部分的图片左下角定点坐标y轴
        float leftY1 = hItem - briefLength;
        //第一部分的图片右下角定点坐标y轴
        float rightY1 = hItem + briefLength;

        path1.lineTo(w, 0);
        path1.lineTo(w, rightY1);
        path1.lineTo(0, leftY1);
        path1.close();
        canvas.drawPath(path1, paint1);

        paint1.reset();

        getItem2(hItem, leftY1, rightY1, w, h, canvas);
    }

    private void getItem2(final float hItem, final float leftY1, final float rightY1, final int w, final int h, final Canvas canvas) {
        BitmapShader shader2 = new BitmapShader(bitmap2, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        paint2.setShader(shader2);

        //第一部分的图片左下角定点坐标y轴
        float leftY2 = leftY1 + hItem + itemSpace;
        //第一部分的图片右下角定点坐标y轴
        float rightY2 = rightY1 * 2 + itemSpace;

        path2.moveTo(0, leftY1 + itemSpace);
        path2.lineTo(w, rightY1 + itemSpace);
        path2.lineTo(w, leftY2);
        path2.lineTo(0, rightY2);
        path2.close();
        canvas.drawPath(path2, paint2);
        paint2.reset();
        getItem3(rightY2, leftY2, w, h, canvas);

    }

    private void getItem3(final float rightY2, final float leftY2, final int w, final int h, final Canvas canvas) {
        BitmapShader shader3 = new BitmapShader(bitmap3, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        paint3.setShader(shader3);
        path3.moveTo(0, rightY2 + itemSpace);
        path3.lineTo(w, leftY2 + itemSpace);
        path3.lineTo(w, h);
        path3.lineTo(0, h);
        path3.close();
        canvas.drawPath(path3, paint3);
        paint3.reset();
    }

    private void init() {
        paint1 = new Paint();
        paint2 = new Paint();
        paint3 = new Paint();
        painText = new Paint();

        path1 = new Path();
        path2 = new Path();
        path3 = new Path();
    }

    public IrregularImageView(Context context) {
        super(context);
        init();
    }

    public IrregularImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.IrregularImageView);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.IrregularImageView_briefLength:
                    briefLength = array.getDimension(attr, 10);
                    break;
                case R.styleable.IrregularImageView_ItemSpace:
                    itemSpace = array.getDimension(attr, 10);
                    break;
            }

        }
        array.recycle();
    }

    public IrregularImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

}
