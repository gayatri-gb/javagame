package com.example.hallows;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.hallows.GameView.screenRatioX;
import static com.example.hallows.GameView.screenRatioY;


public class Cop2 {

    int toShoot = 0;
    boolean isGoingUp = false;
    static int x ;
    static int y;
    static int width;
    static int height;
    int wingCounter = 0;
    int shootCounter = 1;
    Bitmap watershell, flight2, shoot1, shoot2, shoot3, shoot4, shoot5, dead;
    private GameView gameView;
    Cop2 (GameView gameView, int screenY, Resources res) {

        this.gameView = gameView;

        watershell = BitmapFactory.decodeResource(res, R.drawable.watershell);



        width = watershell.getWidth();
        height = watershell.getHeight();

        width /= 1.1;
        height /= 1.1;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        watershell = Bitmap.createScaledBitmap(watershell, width, height, false);


        dead = BitmapFactory.decodeResource(res, R.drawable.sol);
        dead = Bitmap.createScaledBitmap(dead, width, height, false);

        y = screenY / 2;
        x = (int) (64 * screenRatioX);


    }

 Bitmap getWatershell () {


        if (toShoot != 0) {

            if (shootCounter == 1) {
                shootCounter++;
                return shoot1;
            }

            if (shootCounter == 2) {
                shootCounter++;
                return shoot2;
            }

            if (shootCounter == 3) {
                shootCounter++;
                return shoot3;
            }

            if (shootCounter == 4) {
                shootCounter++;
                return shoot4;
            }

            shootCounter = 1;
            toShoot--;
            gameView.newBullet();

            return shoot5;
        }

        if (wingCounter == 0) {
            wingCounter++;
            return watershell;
        }
        wingCounter--;

        return watershell;
    }


    static Rect getCollisionShape() {


        return new Rect(x, y, x + width/2, y + height);
    }

    Bitmap getDead () {
        return dead;
    }
}
