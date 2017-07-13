package com.longshihan.mymenu.rickynewsplash;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

public class RickySplahActivity extends AppCompatActivity {

    private FrameLayout mFrameLayout;
    SplashView splashView;
    ContentView contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mFrameLayout = new FrameLayout(this);

        contentView = new ContentView(this);

        mFrameLayout.addView(contentView);

        splashView = new SplashView(this);
        mFrameLayout.addView(splashView);
        setContentView(mFrameLayout);

        //开启加载数据--数据加载前，开启动画，完毕后进进厂动画
        startSplashDataLoad();
    }

    Handler mHandler = new Handler();

    private void startSplashDataLoad() {
        //模拟数据的加载
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //数据加载完毕，1动画结束2,3动画开始
                splashView.splashDisappear();
            }
        }, 5000);
    }
}
