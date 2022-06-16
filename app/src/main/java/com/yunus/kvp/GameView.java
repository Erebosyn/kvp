package com.yunus.kvp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameView extends SurfaceView implements Runnable{
private Thread thread;
private boolean isPlaying, isGameOver = false;
private int screenX, screenY, score = 0;
private final Paint paint;
private Random random;
private final ninja[]ninjas;
private final List<Bullet> bullets;
public static float screenRationX, screenRationY;
private final flight flight;
private GameActivity activity;
private SharedPreferences prefs;
private SoundPool soundPool;
private final Background background1;
private final Background background2;

public GameView (GameActivity activity, int screenX, int screenY){
    super(activity);


    prefs = activity.getSharedPreferences("game", Context.MODE_PRIVATE);



    this.screenX = screenX;
    this.screenY = screenY;
    screenRationX = 1920f / screenX;
    screenRationY = 1080f / screenY;



    background1 = new Background(screenX, screenY, getResources());
    background2 = new Background(screenX, screenY, getResources());

    flight = new flight(this, screenY, getResources());

    bullets = new ArrayList<>();

    background2.x = screenX;

    paint = new Paint();
    paint.setTextSize(128);
    paint.setColor(Color.WHITE);

    ninjas = new ninja[4];

    for (int i = 0;i < 4;i++) {

        ninja ninja = new ninja(getResources());
        ninjas[i] = ninja;

    }

    random = new Random();

}


@Override
public void run()
{

    while (isPlaying)
    {

        update ();
        draw ();
        sleep ();



    }
}
private void update ()
{

background1.x -=  10 * screenRationX;
background2.x -=  10 * screenRationX;

    if (background1.x + background1.background.getWidth() < 0)
    {
        background1.x = screenX;
    }

    if (background2.x + background2.background.getWidth() < 0)
    {
        background2.x = screenX;
    }

    if (flight.isGoingUp)
        flight.y -= 30 * screenRationY;
    else
        flight.y += 30 * screenRationY;

    if (flight.y < 0)
        flight.y = 0;

    if (flight.y >= screenY - flight.height)
        flight.y = screenY - flight.height;

    List<Bullet> trash = new ArrayList<>();

    for (Bullet bullet : bullets) {
    if (bullet.x > screenX)
        trash.add(bullet);
    bullet.x += 50 * screenRationX;
        for (ninja ninja : ninjas) {

            if (Rect.intersects(ninja.getCollisionShape(),
                    bullet.getCollisionShape())) {

                score++;
                ninja.x = -500;
                bullet.x = screenX + 500;
                ninja.wasShot = true;

            }

        }

    }
for (ninja ninja: ninjas){

    ninja.x -= ninja.speed;
    if (ninja.x + ninja.width < 0 ){


        if (!ninja.wasShot) {
            isGameOver = true;
            return;
        }

        int bound = (int) (30 * screenRationX);
        ninja.speed = random.nextInt(bound);


        if (ninja.speed < 10 * screenRationX)
            ninja.speed = (int) (10 * screenRationX);


        ninja.x = screenX;
        ninja.y = random.nextInt(screenY - ninja.height);

        ninja.wasShot = false;
    }

    if (Rect.intersects(ninja.getCollisionShape(), flight.getCollisionShape())) {

        isGameOver = true;
        return;
    }

}




}

private void draw (){
    if (getHolder().getSurface().isValid()) {

        Canvas canvas = getHolder().lockCanvas();
        canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
        canvas.drawBitmap(background2.background, background2.x, background2.y, paint);
        canvas.drawBitmap(flight.getFlight(), flight.x, flight.y, paint);
        for (ninja ninja : ninjas)
            canvas.drawBitmap(ninja.getNinja(), ninja.x, ninja.y, paint);

        canvas.drawText(score + "", screenX / 2f, 164, paint);


        if (isGameOver) {
            isPlaying = false;
            canvas.drawBitmap(flight.getDead(), flight.x, flight.y, paint);

            getHolder().unlockCanvasAndPost(canvas);
            waitBeforeExiting ();
            saveIfHighScore();
        }

        for (Bullet bullet : bullets)
            canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint);

        getHolder().unlockCanvasAndPost(canvas);

    }
}
    private void waitBeforeExiting() {

        try {
            Thread.sleep(3000);
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void saveIfHighScore() {

        if (prefs.getInt("highscore", 0) < score) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highscore", score);
            editor.apply();
        }

    }

    private void sleep()
     {
         try {
             Thread.sleep(17);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }


     }

    public void resume (){
        thread = new Thread(this);
    thread.start();



    }

    public void pause (){
    try{
        isPlaying = false;
        thread.join();

    } catch (InterruptedException e ){
        e.printStackTrace();
    }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < screenX / 2) {
                    flight.isGoingUp = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                flight.isGoingUp = false;
                if (event.getX() > screenX / 2)
                    flight.toShoot++;
                break;
        }

        return true;
    }
    public void newBullet() {

        Bullet bullet = new Bullet(getResources());
        bullet.x = flight.x + flight.width;
        bullet.y = flight.y + (flight.height / 2);
        bullets.add(bullet);

    }
}
