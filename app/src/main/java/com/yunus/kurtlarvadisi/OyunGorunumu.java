package com.yunus.kurtlarvadisi;

import static java.lang.Thread.sleep;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Surface;
import android.view.SurfaceView;

import com.google.android.material.canvas.CanvasCompat;

public class OyunGorunumu<public_void> extends SurfaceView implements Runnable {
    private Thread thread;
    private boolean isPlaying;
    private int ekranX, ekranY;
    private float ekranBuyuklukX, getEkranBuyuklukY;
    private Arkaplan arkaplan1, arkaplan2;
    private Paint paint;

    public OyunGorunumu(Context context, int ekranX, int ekranY) {
        super(context);

        this.ekranX = ekranX;
        this.ekranY = ekranY;
        ekranBuyuklukX = 1920f / ekranX;
        getEkranBuyuklukY = 1000f / ekranY;

        arkaplan1 = new Arkaplan(ekranX, ekranY, getResources());
        arkaplan2 = new Arkaplan(ekranX, ekranY, getResources());

        arkaplan2.x = ekranX;

        paint = new paint();
    }

    @Override
    public void run() {
        while(isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    private void sleep() {
    }

    private void draw() {
    }

    private void update() {
        arkaplan1.x -= 10 * ekranBuyuklukX;
        arkaplan2.x -= 10 * ekranBuyuklukX;

        if (arkaplan1.x + arkaplan1.arkaplan.getWidth() < 0){
            arkaplan1.x = ekranX;
        }
        if (arkaplan2.x + arkaplan2.arkaplan.getWidth() < 0){
            arkaplan2.x = ekranX;

    }

    private void draw{

            if (getHolder().getSurface().isValid()){

                Canvas canvas = getHolder().lockCanvas();
                canvas.drawBitmap(arkaplan1.arkaplan, arkaplan1.x ,arkaplan1.y, paint);
                canvas.drawBitmap(arkaplan2.arkaplan, arkaplan2.x ,arkaplan2.y, paint);

                getHolder().unlockCanvasAndPost(canvas);
            }


    }
    public_void resume() {
        isPlaying = true;
        thread = new Thread(target: this);
        thread.start();

    }
    private void sleep() {
        try {
            sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public_void pause () {
        try {
            isPlaying = false;
            thread.join();
    }catch (InterruptedException e) {
        e.printStackTrace();
    }
}

    public void resume() {
    }

    public void pause() {
    }

    public void resume() {
    }