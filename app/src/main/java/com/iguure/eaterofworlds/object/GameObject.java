package com.iguure.eaterofworlds.object;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Administrator on 11/17 0017.
 */
abstract public class GameObject {

    protected int currentFrame;     // 当前动画帧

    protected int speed;

    // 对象的左下角坐标
    protected float objectX;
    protected float objectY;

    protected float objectWidth;
    protected float objectHeight;

    protected float screenWidth;
    protected float screenHeight;

    protected boolean isAlive;

    protected Paint paint;          // 画笔对象

    protected Resources resources;  // 资源类

    public GameObject(Resources resources) {
        this.resources = resources;
        this.paint = new Paint();
    }

    public void setScreenWH(float screenWidth, float screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void initial(int speed, float objectX, float objectY) {};

    // 初始化图片资源
    public abstract void initBitmap(int direction);

    // 对象的绘图
    public abstract void drawSelf(Canvas canvas);

    // 释放资源
    public abstract void release();

    // 检测碰撞
    public boolean isCollide(GameObject obj) {
        return true;
    }

    // 对象的逻辑
    public void logic() {}

    // getter和setter方法
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public float getObjectX() {
        return objectX;
    }
    public void setObjectX(float objectX) {
        this.objectX = objectX;
    }
    public float getObjectY() {
        return objectY;
    }
    public void setObjectY(float objectY) {
        this.objectY = objectY;
    }
    public float getObjectWidth() {
        return objectWidth;
    }
    public void setObjectWidth(float objectWidth) {
        this.objectWidth = objectWidth;
    }
    public float getObjectHeight() {
        return objectHeight;
    }
    public void setObjectHeight(float objectHeight) {
        this.objectHeight = objectHeight;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public Resources getResources() {
        return resources;
    }

}
