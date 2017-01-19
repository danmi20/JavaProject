package com.foodie.app.Helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;


/**
 * Created by David on 24/12/2016.
 */

public class DebugHelper {
    public static final String TAG = "Foodie";
    public static void Log(final String msg)
    {
        final Handler UIHandler = new Handler(Looper.getMainLooper());
        UIHandler .post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,msg);

            }
        });
    }

}
