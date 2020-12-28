package com.example.hallows;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.hallows.GameView.screenRatioX;
import static com.example.hallows.GameView.screenRatioY;


public class foodminus{

    public int speed = 20;
    public boolean wasShot = true;
    int x = 0, y, width, height, foodCounter = 1;
    Bitmap foodminus, bird2, bird3, bird4;

    foodminus(Resources res) {

        foodminus = BitmapFactory.decodeResource(res, R.drawable.butcoin);


        width = foodminus.getWidth();
        height = foodminus.getHeight();

        width /= 4;
        height /= 4;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        foodminus = Bitmap.createScaledBitmap(foodminus, width, height, false);


        y = -height;
    }

    Bitmap getfoodminus () {

        if (foodCounter == 1) {
            foodCounter++;
            return foodminus;
        }


        return foodminus;
    }

    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }

}
