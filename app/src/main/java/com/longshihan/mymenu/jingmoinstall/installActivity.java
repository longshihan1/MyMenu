package com.longshihan.mymenu.jingmoinstall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.longshihan.install.ApkInstaller;
import com.longshihan.mymenu.R;

import java.io.File;

import static com.longshihan.install.FileUtils.getDiskCacheDir;

public class installActivity extends AppCompatActivity {
    private static final String TAG = "[TAG]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install2);
        final String path1 = getDiskCacheDir(this) + "/download";
        File path = new File(path1);
        if (!path.exists()) {
            Log.d(TAG, "文件夹不存在:" + path1);
            path.mkdirs();
        }
        final String filepath = path + "/1.apk";
        File file = new File(filepath);
        if (file.exists()) {
            Log.d(TAG, "文件存在");

            ApkInstaller apkInstaller = new ApkInstaller(this, filepath);
            apkInstaller.install();
        } else {
            Log.d(TAG, "文件不存在");
        }
    }
}
