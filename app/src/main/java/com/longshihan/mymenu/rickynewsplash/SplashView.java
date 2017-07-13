package com.longshihan.mymenu.rickynewsplash;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

/**
 * Created by longshihan on 2017/7/10.
 */

public class SplashView extends android.support.v7.widget.AppCompatImageView {
    private float mRotationRadius = 120, mCircleRadius = 18;
    int[] mCircleColors;
    private long mRotationDuration = 1600;
    private long mSplashDuration = 1600;
    private int mSplashBgColor = Color.WHITE;

    float mHoleRadius = 0F;
    float mCurrentRotationAngle = 0F;
    float mCurrentRotationRadius = mRotationRadius;

    public SplashView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制三个
        if (mState == null) {
            mState = new RotationState();
        }
        mState.drawState(canvas);
        super.onDraw(canvas);
    }


    public void splashDisappear() {

        mState=new MerginState();
    }

    private SplashState mState = null;

    private abstract class SplashState {
        public abstract void drawState(Canvas canvas);
    }

    public class RotationState extends SplashState {

        @Override
        public void drawState(Canvas canvas) {
            //绘制第一种



        }
    }

    public class MerginState extends SplashState {

        @Override
        public void drawState(Canvas canvas) {

        }
    }

    public class ExpandState extends SplashState {

        @Override
        public void drawState(Canvas canvas) {

        }
    }
}
