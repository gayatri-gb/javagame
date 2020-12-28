package com.example.hallows;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying, isGameOver = false, isHitOver = false, delayTimeOver = false, isHitOver2 = false, isHitOver3 = false,isHitOver4 = false, isbgOver = false, isWon = false, pressedInida = false;
    private int screenX, screenY, score = 0;
    public static float screenRatioX, screenRatioY, screenRatioZ;
    private Paint paint, paint2, paint3, paint4, paint5;
    private Bird[] birds;
    private trash[] trashes;
     private GestureDetector mGestureDectector;
    private foodminus[] foodminuses;
    private flowerPower[] flowerpowers;
    private SecondPower[] secondPowers;
    private ThirdPower[] thirdPowers;
    private MainPower[] mainPowers;
    private  MainPower main;
   // private int counter1 = 0, counter0 = 0, counter4 = 0, counter8 = 0;
    private SharedPreferences prefs;
    private Random random;
    private SoundPool soundPool;
    private List<Bullet> bullets;
    private int sound;
    private GameActivity.Cop flight;
    private Cop2 watershell;
    private GameActivity activity;
    private Background background1;
    private Background background2;

    private Object Resources;
    private int timer;

    public GameView(GameActivity activity, int screenX, int screenY) {
        super(activity);

        this.activity = activity;

        prefs = activity.getSharedPreferences("game", Context.MODE_PRIVATE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        sound = soundPool.load(activity, R.raw.shoot, 1);

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        //screenRatioZ = screenX/3;


        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());


        flight = new GameActivity.Cop(this, screenY, getResources());
        watershell = new Cop2(this, screenY, getResources());
        bullets = new ArrayList<>();

       background1.x = screenX;


        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.BLACK);

        paint2 = new Paint();
        paint2.setTextSize(128);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(0.5f);



        paint3 = new Paint();
        paint3.setTextSize(128);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeWidth(0.5f);

        paint4 = new Paint();
        paint4.setTextSize(128);
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setStrokeWidth(0.5f);

        paint5 = new Paint();
        paint5.setTextSize(128);
        paint5.setStyle(Paint.Style.STROKE);
        paint5.setStrokeWidth(0.5f);


        birds = new Bird[15];

        for (int i = 0; i < 15; i++) {

            Bird bird = new Bird(getResources());
            birds[i] = bird;

        }
        trashes = new trash[3];

        for (int i = 0; i < 3; i++) {

           trash trash = new trash(getResources());
            trashes[i] = trash;

        }



        foodminuses = new foodminus[1];

        for (int i = 0; i < 1; i++) {

            foodminus foodminus = new foodminus(getResources());
            foodminuses[i] = foodminus;

        }


        flowerpowers = new flowerPower[1];

        for (int i = 0; i < 1; i++) {

            flowerPower flowerPower = new flowerPower(getResources());
            flowerpowers[i] = flowerPower;

        }

        secondPowers = new SecondPower[1];

        for (int i = 0; i < 1; i++) {

            SecondPower secondPower = new SecondPower(getResources());
            secondPowers[i] = secondPower;

        }
        thirdPowers = new ThirdPower[1];

        for (int i = 0; i < 1; i++) {

            ThirdPower thirdPower = new ThirdPower(getResources());
            thirdPowers[i] = thirdPower;

        }
        mainPowers = new MainPower[1];

        for (int i = 0; i < 1; i++) {

            MainPower mainPower = new MainPower(getResources());
            mainPowers[i] = mainPower;

        }



        random = new Random();

    }


    @Override
    public void run() {

        while (isPlaying) {

            update();
            draw();
            sleep();

        }

    }


    private void update() {
        //timer++;
        background1.x += screenRatioX;
        if(background1.x < background2.x) {
            background2.x = background1.x + background1.background.getWidth();}
        //(int)(background2.x - (2 * screenRatioX));
        else {background2.x =background1.x-background1.background.getWidth();




        }

        if (background1.x + background1.background.getWidth()<0){
            background1.x = background2.x + background2.background.getWidth();

        }
        if (background2.x + background2.background.getWidth()<0){
            background2.x = background1.x+background1.background.getWidth();
        }


   if (flight.isGoingUp) {
       flight.y -= 10 * screenRatioY;
   }

       if(flight.isGoingDown){
      flight.y += 5 * screenRatioY;
       //flight.y = (screenY- flight.height);

        }


        if (flight.y < 0) {
            flight.y = 0;

        }


        if (flight.y >= screenY - flight.height) {
            //flight.y -= 20 * screenRatioY;
          //  flight.y = (int) ((double) screenY- flight.height);
            isGameOver=true;

        }

        if (flight.isGoingFront) {

            flight.x = 0;



        } else{
            flight.x += 5 * screenRatioX;

    }

        if (flight.x < 0) {

            flight.x = 0;


        }


        if (flight.x >= screenX - flight.width*3) {

            flight.x = (int) ((double) screenX- flight.width*3);



        }

        List<Bullet> trash = new ArrayList<>();

        for (Bullet bullet : bullets) {

            if (bullet.x > screenX) {
                trash.add(bullet);

            }




            bullet.x += 50 * screenRatioX;




            for (Bird bird : birds) {

                if (bird.y >= screenY - bird.height) {

                    bird.y = (int) ((double) screenY- bird.height);
                    // screenY - flight.height
                }


                if (Rect.intersects(bird.getCollisionShape(),
                        bullet.getCollisionShape())) {



                    score++;
                    bird.x = -500;
                    bullet.x = screenX + 500;
                    bird.wasShot = true;

                    if (isHitOver2) {
                        score=score+2;
                        bird.x = -500;
                        bullet.x = screenX + 500;
                        bird.wasShot = true;


                    }

                    if (isHitOver3) {
                        score=score+3;
                        bird.x = -500;
                        bullet.x = screenX + 500;
                        bird.wasShot = true;


                    }


                }

                if (isHitOver) {
                    bird.x = -500;
                    bird.y = 0;


                    // bird.wasShot = true;
                }



            }

            for (trash trash1 : trashes) {

                if (trash1.x <= screenX - trash1.width) {

                    trash1.x = (int) ((double) screenX - trash1.width);
                    // screenY - flight.height
                }
                if (trash1.x >= screenX - trash1.width*2) {

                    trash1.x = (int) ((double) screenX - trash1.width);
                    // screenY - flight.height
                }

            }


           /* for (foodminus foodminus : foodminuses) {

                if (Rect.intersects(foodminus.getCollisionShape(),
                        bullet.getCollisionShape())) {

                    // score++;
                    //food.x = -500;
                    //  bullet.x = screenX + 500;
                    // bird.wasShot = true;


                }

            }*/



        }




        for (Bullet bullet : trash)
            bullets.remove(bullet);

      for (Bird bird : birds) {

            bird.x -= bird.speed;
            if(bird.x < 0) {

            }
         if(bird.x > screenX) {

                      }


            if (bird.x + bird.width  <= 0) {


                int bound = (int) (30 * screenRatioX);
                bird.speed = random.nextInt(bound);

                if (bird.speed < 10 * screenRatioX)
                    bird.speed = (int) (10 * screenRatioX);

                bird.x = screenX;

                bird.y = random.nextInt(screenY - bird.height);

                bird.wasShot = false;
            }



                if (Rect.intersects(bird.getCollisionShape(), flight.getCollisionShape())) {

                    isGameOver = true;
                    return;
                }
            }

        for (trash trash1 : trashes) {

            trash1.y += trash1.speed;
            if(trash1.y < 0) {

            }
            if(trash1.y > screenY) {
                trash1.y= screenY-trash1.height;

            }


            if (trash1.y + trash1.height  <= 0) {


                int bound = (int) (0.3* screenRatioX);
                //trash1.speed = random.nextInt(bound);
                trash1.speed = (int) (10 * screenRatioX);

                if (trash1.speed < 10 * screenRatioY)
                trash1.speed = random.nextInt(bound);

                trash1.y = screenY;

               trash1.x = random.nextInt(bound);

               trash1.wasShot = false;
            }




        }


       
        /*for (foodminus foodminus : foodminuses) {

            foodminus.x -= foodminus.speed;

            if (foodminus.x + foodminus.width < 0) {


                int bound = (int) (2 * screenRatioX);
                foodminus.speed = random.nextInt(bound);

                if (foodminus.speed < 10 * screenRatioX)
                    foodminus.speed = (int) (10 * screenRatioX);

                foodminus.x = screenX;
                foodminus.y = random.nextInt(screenY - foodminus.height);

                foodminus.wasShot = false;
            }



            if (Rect.intersects(foodminus.getCollisionShape(), flight.getCollisionShape())) {

                // isGameOver = true;
                score--;
                foodminus.x = -500;
                if(score < 0){
                    isGameOver = true;
                }

                // return;
            }

        }*/


       for (flowerPower flowerPower : flowerpowers) {

            flowerPower.x -= flowerPower.speed;

           if (flowerPower.x + flowerPower.width  <= 0) {


               int bound = (int) (30 * screenRatioX);
               flowerPower.speed = random.nextInt(bound);

               if (flowerPower.speed < 10 * screenRatioX)
                   flowerPower.speed = (int) (10 * screenRatioX);

               flowerPower.x = screenX;

               flowerPower.y = random.nextInt(screenY*2 - flowerPower.height);

               flowerPower.wasShot = false;
           }


// power collide cop

            if (Rect.intersects(flowerPower.getCollisionShape1(), flight.getCollisionShape())) {


               isHitOver2 = true;

                soundPool.play(sound, 30, 30, 0, 0, 5);




                //  Reminder(15);
                //  return;
            }

        }

        for (SecondPower secondPower : secondPowers) {

            secondPower.x -= secondPower.speed;
            if (secondPower.x + secondPower.width  <= 0) {


                int bound = (int) (30 * screenRatioX);
                secondPower.speed = random.nextInt(bound);

                if (secondPower.speed < 10 * screenRatioX)
                    secondPower.speed = (int) (10 * screenRatioX);

                secondPower.x = screenX;

                secondPower.y = random.nextInt(screenY*4 - secondPower.height);

                secondPower.wasShot = false;
            }

// power collide cop


        }

        for (ThirdPower thirdPower : thirdPowers) {

            thirdPower.x -= thirdPower.speed;
            if (thirdPower.x + thirdPower.width  <= 0) {


                int bound = (int) (30 * screenRatioX);
                thirdPower.speed = random.nextInt(bound);

                if (thirdPower.speed < 10 * screenRatioX)
                    thirdPower.speed = (int) (10 * screenRatioX);

                thirdPower.x = screenX;

                thirdPower.y = random.nextInt(screenY*6- thirdPower.height);

                thirdPower.wasShot = false;
            }


// power collide cop
        }

        for (MainPower mainPower : mainPowers) {


            mainPower.y -= mainPower.speed;
            if (mainPower.y + mainPower.height  < screenY) {

                int bound = (int) (30 * screenRatioY);
                mainPower.speed = random.nextInt(bound);
                if (mainPower.speed < 10 * screenRatioY)
                    mainPower.speed = (int) (10 * screenRatioY);

               // mainPower.x = screenX;

                mainPower.y = screenY*2 - mainPower.height;
                mainPower.x = (int) ((double) screenX- mainPower.width*3);
                mainPower.wasShot = false;
            }


// power collide cop
        }


   
      

        }


    private void draw() {

        if (getHolder().getSurface().isValid()) {

            final Canvas canvas = getHolder().lockCanvas();
           canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);





            for (Bird bird : birds)
                canvas.drawBitmap(bird.getBird(), bird.x, bird.y, paint);

            //for (trash trash : trashes)
               // canvas.drawBitmap(trash.getTrash(), trash.x, trash.y, paint);



           /* for (foodminus foodminus : foodminuses)
                canvas.drawBitmap(foodminus.getfoodminus(), foodminus.x, foodminus.y, paint);
*/
            for (flowerPower flowerPower : flowerpowers)
                canvas.drawBitmap(flowerPower.getFlowerPower(), flowerPower.x, flowerPower.y, paint);


if(!isGameOver) {
    canvas.drawBitmap(flight.getFlight(), flight.x, flight.y, paint);

}

if (isHitOver2) {
                isPlaying = true;

                canvas.drawBitmap(background1.background3, background1.x, background1.y, paint);
                canvas.drawBitmap(background2.background4, background2.x, background2.y, paint);
                if (!isGameOver) {
                    for (Bird bird : birds)
                        canvas.drawBitmap(bird.getBird(), bird.x, bird.y, paint);

                    canvas.drawBitmap(flight.getFlight(), flight.x, flight.y, paint);
                    
                    for (SecondPower secondPower : secondPowers)
                        canvas.drawBitmap(secondPower.getSecondPower(), secondPower.x, secondPower.y, paint);
                    if (Rect.intersects(SecondPower.getCollisionShape(), flight.getCollisionShape())) {

                        isHitOver3 = true;

                        soundPool.play(sound, 30, 30, 0, 0, 5);
                    }


                }


            }
if (isHitOver3) {
                isPlaying = true;
                canvas.drawBitmap(background1.background5, background1.x, background1.y, paint);
                canvas.drawBitmap(background2.background6, background2.x, background2.y, paint);
                if (!isGameOver) {
                    for (Bird bird : birds)
                        canvas.drawBitmap(bird.getBird(), bird.x, bird.y, paint);
                    canvas.drawBitmap(flight.getFlight(), flight.x, flight.y, paint);

                    for (ThirdPower thirdPower : thirdPowers)
                        canvas.drawBitmap(thirdPower.getThirdPower(), thirdPower.x, thirdPower.y, paint);
                    if (Rect.intersects(ThirdPower.getCollisionShape(), flight.getCollisionShape())) {

                        isHitOver2= true;

                        soundPool.play(sound, 30, 30, 0, 0, 5);
                    }


                }


            }

            if (isHitOver4) {
                isPlaying = true;
                canvas.drawBitmap(background1.background7, background1.x, background1.y, paint);
                canvas.drawBitmap(background2.background8, background2.x, background2.y, paint);
                if (!isGameOver) {
                    for (Bird bird : birds)
                        canvas.drawBitmap(bird.getBird(), bird.x, bird.y, paint);
                    canvas.drawBitmap(flight.getFlight(), flight.x, flight.y, paint);

                   // for (MainPower mainPower : mainPowers)
                        //canvas.drawBitmap(mainPower.getMainpower(), mainPower.x, mainPower.y, paint);
                    //if (Rect.intersects(MainPower.getCollisionShape(), flight.getCollisionShape())) {

                      // isWon= true;

                      //  soundPool.play(sound, 30, 30, 0, 0, 5);
                   // }

                    if (prefs.getInt("highscore", 0) < score) {
               /* paint2.setColor(Color.WHITE);
                paint2.setStyle(Paint.Style.FILL);
                paint3.setColor(Color.WHITE);
                paint3.setStyle(Paint.Style.FILL);
                paint4.setColor(Color.WHITE);
                paint4.setStyle(Paint.Style.FILL);
                paint5.setColor(Color.WHITE);
                paint5.setStyle(Paint.Style.FILL);*/

                        //canvas.drawBitmap(flight.getWon(), flight.x, flight.y, paint);
                        canvas.drawBitmap(watershell.getWatershell(), 0, screenY/2, paint);

                        if (Rect.intersects(watershell.getCollisionShape(), flight.getCollisionShape())) {
                            //flight.x=-500;

                            //canvas.drawBitmap(flight.getWon(), screenX,screenY, paint);
                            isWon= true;

                            soundPool.play(sound, 30, 30, 0, 0, 5);
                        }


                    }


                }


            }


            canvas.drawText(score + "", screenX / 2f, 164, paint);

            if (isGameOver) {
                isPlaying = false;
                canvas.drawBitmap(flight.getDead(), flight.x,(int) ((double) screenY- flight.height), paint);
                soundPool.play(sound, 35, 35, 0, 0, 6);
                getHolder().unlockCanvasAndPost(canvas);
                saveIfHighScore();
                saveScore();
                saveTimer();
                waitBeforeExiting();
                return;
            }



          if (isWon) {
               /* paint2.setColor(Color.WHITE);
                paint2.setStyle(Paint.Style.FILL);
                paint3.setColor(Color.WHITE);
                paint3.setStyle(Paint.Style.FILL);
                paint4.setColor(Color.WHITE);
                paint4.setStyle(Paint.Style.FILL);
                paint5.setColor(Color.WHITE);
                paint5.setStyle(Paint.Style.FILL);*/

               // canvas.drawBitmap(flight.getWon(), watershell.x,watershell.y, paint);
                saveTimer();
                isPlaying = false;
                getHolder().unlockCanvasAndPost(canvas);
                waitBeforeExitingWon();
                return;
            }

            for (Bullet bullet : bullets)
                canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint);

            getHolder().unlockCanvasAndPost(canvas);

        }

    }

    private void waitBeforeExiting() {
        Log.v("text0", "check");

        try {
            Thread.sleep(3000);
            activity.startActivity(new Intent(activity, Splashscreen.class));
            activity.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void waitBeforeExitingWon() {
        Log.v("text0", "check");


        try {
            Thread.sleep(3000);
            activity.startActivity(new Intent(activity, Splashscreenwon.class));
            activity.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    private void saveIfHighScore() {

        if (prefs.getInt("highscore", 0)<score) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highscore", score);
            editor.apply();
        }

    }


    private void saveScore() {


            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("score", score);
            editor.apply();


    }

   private void saveTimer() {

        if (prefs.getInt("Time", 0) < timer) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("Time", timer);
            editor.apply();
        }

    }

    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {

        isPlaying = true;
        thread = new Thread(this);
        thread.start();

    }

    public void pause() {

        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {





        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_POINTER_UP:
            {
                int index = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                int pointId = event.getPointerId(index);
                flight.isGoingDown = true;
                flight.isGoingUp=false;
                if (event.getX() > screenX / 2)
                    // flight.isGoingUp= true;
                    flight.toShoot++;

                break;
            }


            case MotionEvent.ACTION_MOVE:

                if (!flight.isGoingUp && !flight.isGoingFront) {

                    flight.x = (int) event.getX() - 100;
                    flight.y = (int) event.getY() - 50;
                    //flight.toShoot++;
                }

                break;


          case MotionEvent.ACTION_DOWN:
              case MotionEvent.ACTION_POINTER_DOWN:
                if (event.getX() < screenX / 2) {
                    flight.isGoingUp = true;
                    flight.isGoingDown = false;
                    flight.toShoot++;
                }
                break;
            case MotionEvent.ACTION_UP:
               flight.isGoingDown = true;
               flight.isGoingUp=false;
                if (event.getX() > screenX / 2)
                   // flight.isGoingUp= true;
                    flight.toShoot++;
                break;
        }






        return true;
    }



    public void newBullet() {
         //   if (!prefs.getBoolean("isMute", false))
               // soundPool.play(sound, 0, 0, 0, 0, 1);

            Bullet bullet = new Bullet(getResources());
            bullet.x = flight.x + flight.width;
            bullet.y = flight.y + (flight.height / 2);
            bullets.add(bullet);
        }



    public void Reminder(int seconds) {
        Timer timer;
       timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
    }
  class RemindTask extends TimerTask {
        Timer timer;
        public void run() {

            isHitOver= false;
            isHitOver2=false;



        }

    }


}
