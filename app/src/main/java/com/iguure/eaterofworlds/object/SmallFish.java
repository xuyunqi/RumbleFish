package com.iguure.eaterofworlds.object;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.iguure.eaterofworlds.R;

import java.util.Random;

/**
 * Created by Administrator on 11/18 0018.
 */
public class SmallFish extends EnemyFish {
    private static int currentCount = 0;
    private Bitmap smallFish;
    public static int sumCount = 6;

    public SmallFish(Resources resources) {
        super(resources);
        // TODO Auto-generated constructor stub
        initBitmap(direction);
    }

    @Override
    public void initial(int arg0,float arg1,float arg2){
        isAlive = true;
        speed = 10;
        Random ran = new Random();
        objectX = ran.nextInt((int)(screenWidth - objectWidth));
        if (direction == 1) {
            objectY = -objectHeight * (currentCount*2 + 1);
        } else if (direction == 0) {
            objectY = objectHeight * (currentCount*2 + 1) + screenHeight;
        }
        currentCount++;
        if(currentCount >= sumCount){
            currentCount = 0;
        }
    }

    @Override
    public void initBitmap(int direction) {
        // TODO Auto-generated method stub
        if (direction == 0)
            smallFish = BitmapFactory.decodeResource(resources, R.drawable.small);
        else if (direction == 1)
            smallFish = BitmapFactory.decodeResource(resources, R.drawable.small2);
        objectWidth = smallFish.getWidth();
        objectHeight = smallFish.getHeight();
    }

    @Override
    public void drawSelf(Canvas canvas) {
        if (isAlive) {
            if (isVisible) {
                canvas.save();
                canvas.clipRect(objectX, objectY, objectX + objectWidth, objectY + objectHeight);
                canvas.drawBitmap(smallFish, objectX, objectY, paint);
                canvas.restore();
            }
            logic();
        } else {
            int y = (int) (currentFrame * objectHeight);
            canvas.save();
            canvas.clipRect(objectX, objectY, objectX + objectWidth, objectY + objectHeight);
            canvas.drawBitmap(smallFish, objectX, objectY - y, paint);
            canvas.restore();
            currentFrame++;
            if(currentFrame >= 3){
                currentFrame = 0;
                isAlive = false;
            }
        }
    }

    @Override
    public void release() {
        // TODO Auto-generated method stub
        if(!smallFish.isRecycled()){
            smallFish.recycle();
        }
    }
}
