package com.example.hallows;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.hallows.GameView.screenRatioX;
import static com.example.hallows.GameView.screenRatioY;


public class Bullet {

    int x, y, width, height;
    Bitmap bullet, hit;

    Bullet (Resources res) {

        bullet = BitmapFactory.decodeResource(res, R.drawable.ballbulllet);
        hit = BitmapFactory.decodeResource(res, R.drawable.ballbulllet);
        width = bullet.getWidth();
        height = bullet.getHeight();

        width /= 4.3;
        height /= 4.3;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        bullet = Bitmap.createScaledBitmap(bullet, width, height, false);
        hit = Bitmap.createScaledBitmap(hit, width, height, false);

    }

    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }
    Bitmap getHit () {
        return hit;
    }
}
