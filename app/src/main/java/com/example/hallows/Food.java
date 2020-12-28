package com.example.hallows;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.hallows.GameView.screenRatioX;
import static com.example.hallows.GameView.screenRatioY;


public class Food{

    public int speed = 20;
    public boolean wasShot = true;
    int x = 0, y, width, height, foodCounter = 1;
    Bitmap food, bird2, bird3, bird4;

    Food (Resources res) {

        food = BitmapFactory.decodeResource(res, R.drawable.copcoin);


        width = food.getWidth();
        height = food.getHeight();

        width /= 4;
        height /= 4;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        food = Bitmap.createScaledBitmap(food, width, height, false);


        y = -height;
    }

    Bitmap getFood () {

        if (foodCounter == 1) {
            foodCounter++;
            return food;
        }


        return food;
    }

    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }

}
