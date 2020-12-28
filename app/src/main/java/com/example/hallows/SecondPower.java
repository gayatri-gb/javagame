package com.example.hallows;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.hallows.GameView.screenRatioX;
import static com.example.hallows.GameView.screenRatioY;

public class SecondPower {
    public static float x1,y1;
    public int speed = 20;
    public boolean wasShot = true;
    static int x = 0;
    static int y;
    static int width;
    static int height;
    int foodCounters = 1, foodCounter2 = 1;
    Bitmap secondpower;
    Bitmap bird3;
    Bitmap bird4;

    SecondPower (Resources res) {

        secondpower = BitmapFactory.decodeResource(res, R.drawable.waterfood1);

        width = secondpower.getWidth();
        height = secondpower.getHeight();


        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        secondpower = Bitmap.createScaledBitmap(secondpower, width, height, false);


        y = -height;
    }

    Bitmap getSecondPower() {

        if (foodCounters == 1) {
            foodCounters++;
            return secondpower;
        }


        return secondpower;
    }


    static Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }

    Rect getCollisionShape1 () {
        return new Rect(x, y, x + width, y*2 + height);
    }
    //   static Bitmap getPower() {
    //  return flowerPower2;
    //}

}