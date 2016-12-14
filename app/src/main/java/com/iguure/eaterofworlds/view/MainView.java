package com.iguure.eaterofworlds.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Message;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.iguure.eaterofworlds.R;
import com.iguure.eaterofworlds.factory.GameObjectFactory;
import com.iguure.eaterofworlds.object.EnemyFish;
import com.iguure.eaterofworlds.object.GameObject;
import com.iguure.eaterofworlds.object.KillerFish;
import com.iguure.eaterofworlds.object.MyFish;
import com.iguure.eaterofworlds.object.Shark;
import com.iguure.eaterofworlds.object.SmallFish;
import com.iguure.eaterofworlds.object.StarFish;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 11/17 0017.
 */
public class MainView extends BaseView {

    private float bg_y;
    private float bg_y2;
    private Bitmap background;
    private boolean isPlay;
    private boolean isTouchFish;
    private MyFish myFish;
    private List<EnemyFish> enemyFishes;
    private GameObjectFactory factory;
    private int direction;
    private int speedTime;

    public MainView(Context context) {
        super(context);
        isPlay = true;
        direction = 0;
        speedTime = 1;
        factory = new GameObjectFactory();
        enemyFishes = new ArrayList<EnemyFish>();

        myFish = (MyFish) factory.createMyFish(getResources());
        myFish.setMainView(this);

        for (int i = 0; i < SmallFish.sumCount; i++) {
            SmallFish smallFish = (SmallFish) factory.createSmallFish(getResources());
            enemyFishes.add(smallFish);
        }

        for (int i = 0; i < KillerFish.sumCount; i++) {
            KillerFish killerFish = (KillerFish) factory.createKillerFish(getResources());
            enemyFishes.add(killerFish);
        }

        for (int i = 0; i < StarFish.sumCount; i++) {
            StarFish starFish = (StarFish) factory.createStarFish(getResources());
            enemyFishes.add(starFish);
        }

        for (int i = 0; i < Shark.sumCount; i++) {
            Shark shark = (Shark) factory.createShark(getResources());
            enemyFishes.add(shark);
        }

        thread = new Thread(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        super.surfaceChanged(arg0, arg1, arg2, arg3);
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        super.surfaceCreated(arg0);
        initBitmap();

        for(GameObject obj:enemyFishes){
            obj.setScreenWH(screenWidth,screenHeight);
        }

        myFish.setScreenWH(screenWidth,screenHeight);
        myFish.setAlive(true);
        if(thread.isAlive()){
            thread.start();
        }
        else{
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        super.surfaceDestroyed(arg0);
        release();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            isTouchFish = false;
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            if (x > myFish.getObjectX() && x < myFish.getObjectX() + myFish.getObjectWidth()
                    && y > myFish.getObjectY() && y < myFish.getObjectY() + myFish.getObjectHeight()) {
                if (isPlay) {
                    isTouchFish = true;
                }
                return true;
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE && event.getPointerCount() == 1) {

            if (isTouchFish) {
                float x = event.getX();
                float y = event.getY();
                if (x > myFish.getMiddleX() + 5) {
                    if (myFish.getMiddleX() + myFish.getSpeed() <= screenWidth) {
                        myFish.setMiddleX(myFish.getMiddleX() + myFish.getSpeed());
                    }
                }
                else if (x < myFish.getMiddleX() - 5) {
                    if (myFish.getMiddleX() - myFish.getSpeed() >= 0) {
                        myFish.setMiddleX(myFish.getMiddleX() - myFish.getSpeed());
                    }
                }
                if (y > myFish.getMiddleY() + 5) {
                    if (myFish.getMiddleY() + myFish.getSpeed() <= screenHeight) {
                        myFish.setMiddleY(myFish.getMiddleY() + myFish.getSpeed());
                        if (direction == 1) {
                            myFish.initBitmap(0);
                            direction = 0;
                        }
                    }
                }
                else if (y < myFish.getMiddleY() - 5) {
                    if (myFish.getMiddleY() - myFish.getSpeed() >= 0) {
                        myFish.setMiddleY(myFish.getMiddleY() - myFish.getSpeed());
                        if (direction == 0) {
                            myFish.initBitmap(1);
                            direction = 1;
                        }
                    }
                }
                return true;
            }

        }
        return false;
    }

    @Override
    public void initBitmap() {
        // TODO Auto-generated method stub
        background = BitmapFactory.decodeResource(getResources(), R.drawable.bg_01);
        scaleX = screenWidth / background.getWidth();
        scaleY = screenHeight / background.getHeight();
        bg_y = 0;
    }

    public void initObject() {
        for (EnemyFish obj : enemyFishes) {
            if (obj instanceof SmallFish) {
                if (!obj.isAlive()) {
                    obj.initial(speedTime, 0, 0);
                    break;
                }
            }
            if (obj instanceof KillerFish) {
                if (!obj.isAlive()) {
                    obj.initial(speedTime, 0, 0);
                    break;
                }
            }
            if (obj instanceof StarFish) {
                if (!obj.isAlive()) {
                    obj.initial(speedTime, 0, 0);
                    break;
                }
            }
            if (obj instanceof Shark) {
                if (!obj.isAlive()) {
                    obj.initial(speedTime, 0, 0);
                    break;
                }
            }
        }
    }

    @Override
    public void release() {
        for(GameObject obj:enemyFishes){
            obj.release();
        }

        myFish.release();
        if (!background.isRecycled()) {
            background.recycle();
        }
    }

    @Override
    public void drawSelf() {
        // TODO Auto-generated method stub
        try {
            canvas = sfh.lockCanvas();
            canvas.drawColor(Color.BLACK);
            canvas.save();

            canvas.scale(scaleX, scaleY, 0, 0);
            canvas.drawBitmap(background, 0, bg_y, paint);
            canvas.restore();
            canvas.save();
            canvas.restore();

            for (EnemyFish obj:enemyFishes) {
                if (obj.isAlive()) {
                    obj.drawSelf(canvas);
                }
            }

            if (!myFish.isAlive()) {
                threadFlag = false;
            }
            myFish.drawSelf(canvas);
            paint.setColor(Color.rgb(235, 161, 1));
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            if (canvas != null)
                sfh.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (threadFlag) {
            initObject();
            drawSelf();
            if(!isPlay){
                synchronized (thread) {
                    try {
                        thread.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
