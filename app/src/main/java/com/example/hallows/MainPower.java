package com.example.hallows;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.hallows.GameView.screenRatioX;
import static com.example.hallows.GameView.screenRatioY;

public class MainPower {
    public static float x1,y1;
    public int speed = 20;
    public boolean wasShot = true;
    static int x = 0;
    static int y;
    static int width;
    static int height;
    int powercounters = 1, foodCounter2 = 1;
    Bitmap mainpower,mainpowers ;
    Bitmap bird3;
    Bitmap bird4;

    MainPower (Resources res) {

        mainpower = BitmapFactory.decodeResource(res, R.drawable.watershell);
        mainpowers= BitmapFactory.decodeResource(res, R.drawable.watershell);
        width = mainpower.getWidth();
        height = mainpower.getHeight();


        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        mainpower = Bitmap.createScaledBitmap(mainpower, width, height, false);
        mainpowers =  Bitmap.createScaledBitmap(mainpowers, width, height, false);

        y = -height;
    }

    Bitmap getMainpower() {

        if (powercounters == 1) {
            powercounters++;
            return mainpower;
        }


        return mainpower;
    }


    static Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }

    static Rect getCollisionShape1() {
        return new Rect(x, y+height/2, x + width/2, y + height);
    }
    //   static Bitmap getPower() {
    Bitmap getWons() {
        return mainpowers;
    }
    //}

}
