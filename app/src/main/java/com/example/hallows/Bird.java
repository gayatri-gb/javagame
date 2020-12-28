package com.example.hallows;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.hallows.GameView.screenRatioX;
import static com.example.hallows.GameView.screenRatioY;


public class Bird {

    public int speed = 20;
    public boolean wasShot = true;
    int x = 0, y, width, height, birdCounter = 1, birdbgCounter = 1;
    Bitmap bird1, bird2, bird3, bird4, bird5, bird6, hit, bgbird1, bgbird2, bgbird3, bgbird4;

    Bird (Resources res) {

        bird1 = BitmapFactory.decodeResource(res, R.drawable.waterghost);
        bird2 = BitmapFactory.decodeResource(res, R.drawable.waterghost);
        bird3 = BitmapFactory.decodeResource(res, R.drawable.waterghost2);
        bird4 = BitmapFactory.decodeResource(res, R.drawable.waterghost2);
        bird5 = BitmapFactory.decodeResource(res, R.drawable.pb);
        bird6 = BitmapFactory.decodeResource(res, R.drawable.pb);

        width = bird1.getWidth();
        height = bird1.getHeight();

        width /= 1.5;
        height /= 1.5;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        bird1 = Bitmap.createScaledBitmap(bird1, width, height, false);
        bird2 = Bitmap.createScaledBitmap(bird2, width, height, false);
        bird3 = Bitmap.createScaledBitmap(bird3, width, height, false);
        bird4 = Bitmap.createScaledBitmap(bird4, width, height, false);
        bird5 = Bitmap.createScaledBitmap(bird5, width, height, false);
        bird6 = Bitmap.createScaledBitmap(bird6, width, height, false);

        hit = BitmapFactory.decodeResource(res, R.drawable.waterghost);
        hit = Bitmap.createScaledBitmap(hit, width, height, false);

        y = -height;
    }

    Bitmap getBird () {

        if (birdCounter == 1) {
            birdCounter++;
            return bird1;
        }

        if (birdCounter == 2) {
            birdCounter++;
            return bird2;
        }

        if (birdCounter == 4) {
            birdCounter++;
            return bird4;
        }
        if (birdCounter == 5) {
            birdCounter++;
            return bird5;
        }
        if (birdCounter == 6) {
            birdCounter++;
            return bird6;
        }

        birdCounter = 1;

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
