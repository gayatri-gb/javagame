package com.example.hallows;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.hallows.GameView.screenRatioX;
import static com.example.hallows.GameView.screenRatioY;


public class trash {

    public int speed = 20;
    public boolean wasShot = true;
    int x = 0, y, width, height, trashCounter = 1, birdbgCounter = 1;
    Bitmap bird1, bird2, bird3, bird4, hit, bgbird1, bgbird2, bgbird3, bgbird4;

    trash (Resources res) {

        bird1 = BitmapFactory.decodeResource(res, R.drawable.pb);
        bird2 = BitmapFactory.decodeResource(res, R.drawable.pb);
        bird3 = BitmapFactory.decodeResource(res, R.drawable.pb);
        bird4 = BitmapFactory.decodeResource(res, R.drawable.pb);


        width = bird1.getWidth();
        height = bird1.getHeight();

       // width /= 1.5;
       // height /= 1.5;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        bird1 = Bitmap.createScaledBitmap(bird1, width, height, false);
        bird2 = Bitmap.createScaledBitmap(bird2, width, height, false);
        bird3 = Bitmap.createScaledBitmap(bird3, width, height, false);
        bird4 = Bitmap.createScaledBitmap(bird4, width, height, false);

        hit = BitmapFactory.decodeResource(res, R.drawable.waterghost);
        hit = Bitmap.createScaledBitmap(hit, width, height, false);

        y = -height;
    }

    Bitmap getTrash() {

        if (trashCounter == 1) {
           trashCounter++;
            return bird1;
        }

        if (trashCounter == 2) {
            trashCounter++;
            return bird2;
        }

        if (trashCounter == 3) {
          trashCounter++;
            return bird3;
        }

        trashCounter = 1;

        return bird4;
    }


    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }
    Rect getCollisionShape1 () {
        return new Rect(x, y, x + width, y + height);
    }
    Bitmap getHitBird () {
        return hit;
    }

}

