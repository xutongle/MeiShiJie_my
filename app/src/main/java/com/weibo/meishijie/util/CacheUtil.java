package com.weibo.meishijie.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 巴巴 on 2016/8/4.
 */

public class CacheUtil {
    /**
     * 图片缓存技术的核心类，用于缓存所有下载好的图片，在程序内存达到设定值时会将最少最近使用的图片移除掉。
     */
    private static LruCache<String, Bitmap> mMemoryCache;

    /**
     * 图片硬盘缓存核心类。
     */
    private static DiskLruCache mDiskLruCache;

    public static void clearMemoryCache() {
        if (mMemoryCache != null) {
            mMemoryCache.evictAll();
            mMemoryCache = null;
        }
    }

    public static void clearDiskCache(){
        MainApp.getService().execute(() -> {
            if (mDiskLruCache != null && !mDiskLruCache.isClosed()) {
                try {
                    mDiskLruCache.delete();
                    mDiskLruCache = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void initCache(Context context) {
        // 获取应用程序最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        // 设置图片缓存大小为程序最大可用内存的1/8
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
        try {
            // 获取图片缓存路径
            File cacheDir = getDiskCacheDir(context);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            // 创建DiskLruCache实例，初始化缓存数据
            mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(context), 1, 100 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void close(Closeable... closeables) {
        for (Closeable c : closeables) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 将一张图片存储到LruCache中。
     *
     * @param key    LruCache的键，这里传入图片的URL地址。
     * @param bitmap LruCache的键，这里传入从网络上下载的Bitmap对象。
     */
    private static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            if (!TextUtils.isEmpty(key) && bitmap != null) {
                mMemoryCache.put(key, bitmap);
            }
        }
    }

    /**
     * 从LruCache中获取一张图片，如果不存在就返回null。
     *
     * @param key LruCache的键，这里传入图片的URL地址。
     * @return 对应传入键的Bitmap对象，或者null。
     */
    private static Bitmap getBitmapFromMemoryCache(String key) {
        if (mMemoryCache == null){return null;}
        return mMemoryCache.get(key);
    }

    public static final String CACHEDIR_NAME = "meishijieCache";

    /**
     * 根据传入的uniqueName获取硬盘缓存的路径地址。
     */
    public static File getDiskCacheDir(Context context) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + CACHEDIR_NAME);
    }

    /**
     * 获取当前应用程序的版本号。
     */
    private static int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 使用MD5算法对传入的key进行加密并返回。
     */
    private static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 将缓存记录同步到journal文件中。
     */
    public static void fluchCache() {
        if (mDiskLruCache != null) {
            try {
                mDiskLruCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap getCacheBitmap(String imageUrl, int requestW, int requestH) {
        Bitmap bitmap = CacheUtil.getBitmapFromMemoryCache(imageUrl);
        if (bitmap != null) {
            return bitmap;
        }
        FileInputStream fileInputStream = null;
        DiskLruCache.Snapshot snapShot = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            // 生成图片URL对应的key
            final String key = hashKeyForDisk(imageUrl);
            // 查找key对应的缓存
            snapShot = mDiskLruCache.get(key);
            if (snapShot == null) {
                // 如果没有找到对应的缓存，则准备从网络上请求数据，并写入缓存
                DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                if (editor != null) {
                    OutputStream outputStream = editor.newOutputStream(0);
                    Request request = new Request.Builder().get().url(imageUrl).build();
                    Response response = OkUtil.getOkhttpClient().newCall(request).execute();
                    if (response.isSuccessful()) {
                        in = new BufferedInputStream(response.body().byteStream(), 8 * 1024);
                        out = new BufferedOutputStream(outputStream, 8 * 1024);
                        int b;
                        while ((b = in.read()) != -1) {
                            out.write(b);
                        }
                        editor.commit();
                    } else {
                        editor.abort();
                    }
                }
                // 缓存被写入后，再次查找key对应的缓存
                snapShot = mDiskLruCache.get(key);
            }
            if (snapShot != null) {
                fileInputStream = (FileInputStream) snapShot.getInputStream(0);
                bitmap = BitmapUtil.decodeBitmapFromStream(fileInputStream, requestW, requestH);
                addBitmapToMemoryCache(imageUrl, bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(fileInputStream,in,out);
        }
        return bitmap;
    }

}
