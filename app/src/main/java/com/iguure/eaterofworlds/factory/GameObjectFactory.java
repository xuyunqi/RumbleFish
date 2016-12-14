package com.iguure.eaterofworlds.factory;

import android.content.res.Resources;

import com.iguure.eaterofworlds.object.GameObject;
import com.iguure.eaterofworlds.object.KillerFish;
import com.iguure.eaterofworlds.object.MyFish;
import com.iguure.eaterofworlds.object.Shark;
import com.iguure.eaterofworlds.object.SmallFish;
import com.iguure.eaterofworlds.object.StarFish;

/**
 * Created by Administrator on 11/17 0017.
 */
public class GameObjectFactory {

    public GameObject createMyFish(Resources resources) {
        return new MyFish(resources);
    }

    public GameObject createSmallFish(Resources resources) {
        return new SmallFish(resources);
    }

    public GameObject createKillerFish(Resources resources) {
        return new KillerFish(resources);
    }

    public GameObject createStarFish(Resources resources) {
        return new StarFish(resources);
    }

    public GameObject createShark(Resources resources) {
        return new Shark(resources);
    }

}
