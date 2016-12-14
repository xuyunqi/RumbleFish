package com.iguure.eaterofworlds.object;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.iguure.eaterofworlds.R;
import com.iguure.eaterofworlds.factory.GameObjectFactory;
import com.iguure.eaterofworlds.interfaces.IMyFish;
import com.iguure.eaterofworlds.view.MainView;

/**
 * Created by Administrator on 11/17 0017.
 */
public class MyFish extends GameObject implements IMyFish {

    // 鱼的中心坐标
    private float middleX;
    private float middleY;

    private Bitmap myfish;

    private MainView mainView;

    private GameObjectFactory factory;

    public MyFish(Resources resources) {
        super(resources);
        initBitmap(0);
        this.speed = 8;
        factory = new GameObjectFactory();
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void setScreenWH(float screenWidth, float screenHeight) {
        super.setScreenWH(screenWidth, screenHeight);
        objectX = screenWidth / 2 - objectWidth / 2;
        objectY = screenHeight / 2 - objectHeight / 2;
        middleX = objectX + objectWidth / 2;
        middleY = objectY + objectHeight / 2;
    }

    @Override
    public void initBitmap(int direction) {
        if (direction == 0)
            myfish = BitmapFactory.decodeResource(resources, R.drawable.myfish);
        else
            myfish = BitmapFactory.decodeResource(resources, R.drawable.myfish2);
        objectWidth = myfish.getWidth() / 2;
        objectHeight = myfish.getHeight();
    }

    @Override
    public void drawSelf(Canvas canvas) {
        int x = (int) (currentFrame * objectWidth);
        canvas.save();
        canvas.clipRect(objectX, objectY, objectX + objectWidth, objectY + objectHeight);
        canvas.drawBitmap(myfish, objectX - x, objectY, paint);
        canvas.restore();
        currentFrame++;
        if (currentFrame >= 2) {
            currentFrame = 0;
        }
    }

    @Override
    public void release() {
        if (!myfish.isRecycled()) {
            myfish.recycle();
        }
    }

    @Override
    public float getMiddleX() {
        return middleX;
    }

    @Override
    public float getMiddleY() {
        return middleY;
    }

    @Override
    public void setMiddleX(float middleX) {
        this.middleX = middleX;
        this.objectX = middleX - objectWidth / 2;
    }

    @Override
    public void setMiddleY(float middleY) {
        this.middleY = middleY;
        this.objectY = middleY - objectHeight / 2;
    }

}
