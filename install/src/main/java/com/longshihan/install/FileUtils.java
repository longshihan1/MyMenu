package com.longshihan.install;

import android.content.Context;
import android.os.Environment;

/**
 * @author longshihan
 * @time 2017/7/14 11:34
 * @des
 */

public class FileUtils {
    public static String getDiskCacheDir(Context context) {
        String cachePath;
        //isExternalStorageEmulated()设备的外存是否是用内存模拟的，是则返回true
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageEmulated()) {
            cachePath = context.getExternalCacheDir().getAbsolutePath();
        } else {
            cachePath = context.getCacheDir().getAbsolutePath();
        }
        return cachePath;
    }
}
