package com.yunus.kvp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.yunus.kvp.GameView.screenRationX;
import static com.yunus.kvp.GameView.screenRationY;


public class ninja {

    public int speed = 20;
    public boolean wasShot = true;
    int x = 0, y, width, height, ninjaCounter = 1;
    Bitmap ninja1, ninja2, ninja3, ninja4;

    ninja(Resources res) {

        ninja1 = BitmapFactory.decodeResource(res, R.drawable.ninja1);
        ninja2 = BitmapFactory.decodeResource(res, R.drawable.ninja2);
        ninja3 = BitmapFactory.decodeResource(res, R.drawable.ninja3);
        ninja4 = BitmapFactory.decodeResource(res, R.drawable.ninja4);

        width = ninja1.getWidth();
        height = ninja1.getHeight();

        width /= 6;
        height /= 6;

        width = (int) (width * screenRationX);
        height = (int) (height * screenRationY);

        ninja1 = Bitmap.createScaledBitmap(ninja1, width, height, false);
        ninja2 = Bitmap.createScaledBitmap(ninja2, width, height, false);
        ninja3 = Bitmap.createScaledBitmap(ninja3, width, height, false);
        ninja4 = Bitmap.createScaledBitmap(ninja4, width, height, false);

        y = -height;
    }

    Bitmap getNinja () {

        if (ninjaCounter == 1) {
            ninjaCounter++;
            return ninja1;
        }

        if (ninjaCounter == 2) {
            ninjaCounter++;
            return ninja2;
        }

        if (ninjaCounter == 3) {
            ninjaCounter++;
            return ninja3;
        }

        ninjaCounter = 1;

        return ninja4;
    }

    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }

}
