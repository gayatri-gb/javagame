package com.example.hallows;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background {
    int x = 0, y = 0;
    Bitmap background, background2, background3, background4, background5,background6, background7,background8;

    Background(int screenX, int screenY, Resources res) {

        background = BitmapFactory.decodeResource(res, R.drawable.waters);


        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
       // background2 = BitmapFactory.decodeResource(res, R.drawable.under1);
        //background2 = Bitmap.createScaledBitmap(background2, screenX, screenY, false);
        background3 = BitmapFactory.decodeResource(res, R.drawable.back1);
        background3 = Bitmap.createScaledBitmap(background3, screenX, screenY, false);
        background4 = BitmapFactory.decodeResource(res, R.drawable.back1);
        background4 = Bitmap.createScaledBitmap(background4, screenX, screenY, false);
        background5 = BitmapFactory.decodeResource(res, R.drawable.back2);
        background5 = Bitmap.createScaledBitmap(background5, screenX, screenY, false);
        background6 = BitmapFactory.decodeResource(res, R.drawable.back2);
        background6 = Bitmap.createScaledBitmap(background6, screenX, screenY, false);
        background7 = BitmapFactory.decodeResource(res, R.drawable.vv);
        background7 = Bitmap.createScaledBitmap(background7, screenX, screenY, false);
        background8 = BitmapFactory.decodeResource(res, R.drawable.vv);
        background8 = Bitmap.createScaledBitmap(background8, screenX, screenY, false);



    }



    Bitmap getbgover () {
        return background2;
    }
}