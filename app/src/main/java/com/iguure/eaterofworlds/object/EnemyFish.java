package com.iguure.eaterofworlds.object;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by Administrator on 11/18 0018.
 */
public class EnemyFish extends GameObject {

    protected boolean isVisible;
    protected int direction;

    public EnemyFish(Resources resources) {
        super(resources);

        int rd = Math.random() > 0.5 ? 1 : 0;
        direction = rd;
    }

    @Override
    public void initial(int arg0,float arg1,float arg2){

    }

    @Override
    public void initBitmap(int direction) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawSelf(Canvas canvas) {
        // TODO Auto-generated method stub

    }

    @Override
    public void release() {
        // TODO Auto-generated method stub

    }

    public void logic() {
        // TODO Auto-generated method stub
        if (direction == 1) {
            if (objectY < screenHeight) {
                objectY += speed;
            }
            else {
                isAlive = false;
            }
            if(objectY + objectHeight > 0){
                isVisible = true;
            }
            else{
                isVisible = false;
            }
        } else if (direction == 0) {
            if (objectY > 0) {
                objectY -= speed;
            }
            else {
                isAlive = false;
            }
            if(objectY - objectHeight < screenHeight){
                isVisible = true;
            }
            else{
                isVisible = false;
            }
        }
    }
}
