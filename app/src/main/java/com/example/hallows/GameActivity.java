package com.example.hallows;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.hallows.GameView.screenRatioX;
import static com.example.hallows.GameView.screenRatioY;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
   private MediaPlayer Player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startService(new Intent(GameActivity.this, SoundService.class));
        super.onCreate(savedInstanceState);



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x, point.y);

        setContentView(gameView);



    }
    protected void onDestroy() {
        //stop service and stop music
        stopService(new Intent(GameActivity.this, SoundService.class));
        super.onDestroy();
    }


    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
        //Player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
        //Player.start();
    }

    public static class Cop {

        int toShoot = 0;
        boolean isGoingUp = false, isGoingFront = false, isGoingDown = false;
        int x, y, width, height, wingCounter = 0, shootCounter = 1, wingCounter1 = 0;
        Bitmap flight1, flight2, flight3, shoot1, shoot2, shoot3, shoot4, shoot5, dead, hit, won;
        private GameView gameView;
        Cop (GameView gameView, int screenY, Resources res) {

            this.gameView = gameView;

            flight1 = BitmapFactory.decodeResource(res, R.drawable.waterfish1);
            flight2 = BitmapFactory.decodeResource(res, R.drawable.waterfish2);
           // flight3 = BitmapFactory.decodeResource(res, R.drawable.model2);

            width = flight1.getWidth();
            height = flight1.getHeight();

            width /= 1.3;
            height /= 1.3;

           width = (int) (width * screenRatioX);
          height = (int) (height * screenRatioY);

            flight1 = Bitmap.createScaledBitmap(flight1, width, height, false);
           flight2 = Bitmap.createScaledBitmap(flight2, width, height, false);
           // flight3 = Bitmap.createScaledBitmap(flight3, width, height, false);
            shoot1 = BitmapFactory.decodeResource(res, R.drawable.waterfish1);
            shoot2 = BitmapFactory.decodeResource(res, R.drawable.waterfish2);
            shoot3 = BitmapFactory.decodeResource(res, R.drawable.waterfish1);
            shoot4 = BitmapFactory.decodeResource(res, R.drawable.waterfish2);
            shoot5 = BitmapFactory.decodeResource(res, R.drawable.waterfish1);

            shoot1 = Bitmap.createScaledBitmap(shoot1, width, height, false);
            shoot2 = Bitmap.createScaledBitmap(shoot2, width, height, false);
            shoot3 = Bitmap.createScaledBitmap(shoot3, width, height, false);
            shoot4 = Bitmap.createScaledBitmap(shoot4, width, height, false);
            shoot5 = Bitmap.createScaledBitmap(shoot5, width, height, false);

            dead = BitmapFactory.decodeResource(res, R.drawable.waterfishdead1);
            dead = Bitmap.createScaledBitmap(dead, width, height, false);
            hit = BitmapFactory.decodeResource(res, R.drawable.waterfish2);
            hit = Bitmap.createScaledBitmap(hit, width, height, false);
            won = BitmapFactory.decodeResource(res, R.drawable.waterwon);
            won = Bitmap.createScaledBitmap(won, width, height, false);


            y = screenY / 2;
            x = (int) (340 * screenRatioX);

        }

        Bitmap getFlight () {


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
                return flight2;
            }
            if (wingCounter == 1) {
                wingCounter++;
                return flight1;
            }
            if (wingCounter == 2) {
                wingCounter= wingCounter-2;
                return flight1;
            }



            return flight2;
        }


        Rect getCollisionShape () {
            return new Rect(x, y, x + width/2, y + height);
        }
        Rect getCollisionShape1 () {
            return new Rect(x, y, x + width/2, y + height);
        }
        Bitmap getDead () {
            return dead;
        }
        Bitmap getHit () {
            return hit;
        }
        Bitmap getWon () {
            return won;
        }
    }
}

