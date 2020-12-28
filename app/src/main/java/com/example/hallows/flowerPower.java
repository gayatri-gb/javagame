package com.example.hallows;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.hallows.GameView.screenRatioX;
import static com.example.hallows.GameView.screenRatioY;

public class flowerPower {
    public static float x1,y1;
    public int speed = 20;
    public boolean wasShot = true;
    static int x = 0;
    static int y;
    static int width;
    static int height;
    int foodCounter = 1;
    Bitmap flowerPower, flowerPower3;
    static Bitmap flowerPower2;
    Bitmap bird3;
    Bitmap bird4;

    flowerPower (Resources res) {

        flowerPower = BitmapFactory.decodeResource(res, R.drawable.waterfood3);
        flowerPower2 = BitmapFactory.decodeResource(res, R.drawable.catchballon);
        flowerPower3 = BitmapFactory.decodeResource(res, R.drawable.waterfood);

        width = flowerPower.getWidth();
        height = flowerPower.getHeight();


        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        flowerPower = Bitmap.createScaledBitmap(flowerPower, width, height, false);
        flowerPower2 = Bitmap.createScaledBitmap(flowerPower2, width, height, false);
        flowerPower3 = Bitmap.createScaledBitmap(flowerPower3, width, height, false);

        y = -height;
    }

    Bitmap getFlowerPower () {

        if (foodCounter == 1) {
            foodCounter++;
            return flowerPower;
        }


        return flowerPower;
    }

Bitmap getFlowerPower3 () {

        if (foodCounter == 1) {
            foodCounter++;
            return flowerPower3;
        }


        return flowerPower3;
    }

    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }

    Rect getCollisionShape1 () {
        return new Rect(x, y, x + width, y + height);
    }
    static Bitmap getPower() {
        return flowerPower2;
    }

}


