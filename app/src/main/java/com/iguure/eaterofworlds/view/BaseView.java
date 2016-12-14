package com.iguure.eaterofworlds.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.iguure.eaterofworlds.MainActivity;

/**
 * Created by Administrator on 11/17 0017.
 */
public class BaseView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    protected int currentFrame;

    protected float scaleX;
    protected float scaleY;

    protected float screenWidth;
    protected float screenHeight;

    protected boolean threadFlag;

    protected Paint paint;
    protected Canvas canvas;
    protected Thread thread;
    protected SurfaceHolder sfh;
    protected MainActivity mainActivity;

    public BaseView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.mainActivity = (MainActivity) context;
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        screenWidth = this.getWidth();
        screenHeight = this.getHeight();
        threadFlag = true;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        threadFlag = false;
    }

    public void initBitmap() {}

    public void release() {}

    public void drawSelf() {}

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

    public void setThreadFlag(boolean threadFlag) {
        this.threadFlag = threadFlag;
    }

}
