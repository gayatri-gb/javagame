package com.example.hallows;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.hallows.GameView.screenRatioX;
import static com.example.hallows.GameView.screenRatioY;

public class ThirdPower {
    public static float x1,y1;
    public int speed = 20;
    public boolean wasShot = true;
    static int x = 0;
    static int y;
    static int width;
    static int height;
    int powercounter = 1, foodCounter2 = 1;
    Bitmap thirdpower;
    Bitmap bird3;
    Bitmap bird4;

    ThirdPower (Resources res) {

        thirdpower = BitmapFactory.decodeResource(res, R.drawable.waterfood2);

        width = thirdpower.getWidth();
        height = thirdpower.getHeight();


        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        thirdpower = Bitmap.createScaledBitmap(thirdpower, width, height, false);


        y = -height;
    }

    Bitmap getThirdPower() {

        if (powercounter == 1) {
            powercounter++;
            return thirdpower;        }


        return thirdpower;
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
