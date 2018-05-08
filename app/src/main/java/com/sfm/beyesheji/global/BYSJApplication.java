package com.sfm.beyesheji.global;

import android.app.Application;
import android.content.Context;

/**
 * Created by shanfuming on 2018/5/4.
 */

public class BYSJApplication extends Application {

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        BYSJApplication.sContext = getApplicationContext();

    }
}
