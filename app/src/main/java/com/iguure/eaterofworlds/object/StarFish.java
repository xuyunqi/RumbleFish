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
public class StarFish extends EnemyFish {
    private static int currentCount = 0;
    private Bitmap starFish;
    public static int sumCount = 3;

    public StarFish(Resources resources) {
        super(resources);
        // TODO Auto-generated constructor stub
        initBitmap(direction);
    }

    @Override
    public void initial(int arg0,float arg1,float arg2){
        isAlive = true;
        speed = 4;
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
            starFish = BitmapFactory.decodeResource(resources, R.drawable.star);
        else if (direction == 1)
            starFish = BitmapFactory.decodeResource(resources, R.drawable.star2);
        objectWidth = starFish.getWidth();
        objectHeight = starFish.getHeight();
    }

    @Override
    public void drawSelf(Canvas canvas) {
        if (isAlive) {
            if (isVisible) {
                canvas.save();
                canvas.clipRect(objectX, objectY, objectX + objectWidth, objectY + objectHeight);
                canvas.drawBitmap(starFish, objectX, objectY, paint);
                canvas.restore();
            }
            logic();
        } else {
            int y = (int) (currentFrame * objectHeight);
            canvas.save();
            canvas.clipRect(objectX, objectY, objectX + objectWidth, objectY + objectHeight);
            canvas.drawBitmap(starFish, objectX, objectY - y, paint);
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
        if(!starFish.isRecycled()){
            starFish.recycle();
        }
    }
}
