package com.yunus.kvp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.yunus.kvp.GameView.screenRationX;
import static com.yunus.kvp.GameView.screenRationY;

public class flight {
    int toShoot = 0;
    int x, y, width, height, wingCounter =0, shootCounter=1;
    Bitmap run1, run2, shoot1, shoot2,shoot3,shoot4,shoot5, dead;
    private GameView gameView;
    boolean isGoingUp = false;

    flight (GameView gameView, int screenY, Resources res) {

this.gameView =gameView;

        run1 = BitmapFactory.decodeResource(res, R.drawable.ninja1);
        run2 = BitmapFactory.decodeResource(res, R.drawable.ninja2);

        width = run1.getWidth();
        height = run2.getHeight();

        width /= 4;
        height /= 4;

        width = (int) (width * screenRationX);
        height = (int) (height * screenRationY);


        run1 =Bitmap.createScaledBitmap(run1, width, height,false );
        run2 =Bitmap.createScaledBitmap(run2, width,height,false);


        shoot1 = BitmapFactory.decodeResource(res, R.drawable.polat);
        shoot2 = BitmapFactory.decodeResource(res, R.drawable.polat1);
        shoot3 = BitmapFactory.decodeResource(res, R.drawable.polat2);
        shoot4 = BitmapFactory.decodeResource(res, R.drawable.polat3);
        shoot5 = BitmapFactory.decodeResource(res, R.drawable.shoot5);

        shoot1 = Bitmap.createScaledBitmap(shoot1, width, height, false);
        shoot2 = Bitmap.createScaledBitmap(shoot2, width, height, false);
        shoot3 = Bitmap.createScaledBitmap(shoot3, width, height, false);
        shoot4 = Bitmap.createScaledBitmap(shoot4, width, height, false);
        shoot5 = Bitmap.createScaledBitmap(shoot5, width, height, false);

        dead = BitmapFactory.decodeResource(res, R.drawable.dead);
        dead = Bitmap.createScaledBitmap(dead, width, height, false);

y = screenY /2;
x = (int) (64 * screenRationX);

    }
Bitmap getFlight(){
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



if (wingCounter ==0){
        wingCounter++;
        return run1;


}
    wingCounter--;
    return run2;
}
    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }

    Bitmap getDead () {
        return dead;
    }

}