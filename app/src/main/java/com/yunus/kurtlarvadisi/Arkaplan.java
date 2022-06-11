package com.yunus.kurtlarvadisi;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ResourceBundle;

public class Arkaplan   {
    int x = 0, y= 0 ;
    Bitmap arkaplan;

    Arkaplan (int ekranX, int ekranY, Resources res) {

        arkaplan = BitmapFactory.decodeFile(res, R.drawable.arkaplan);
        arkaplan = Bitmap.createScaledBitmap(arkaplan, ekranX, ekranY, false);


    }


}
