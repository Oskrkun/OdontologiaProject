package com.myagenda.BL;

import android.app.Application;
import android.content.Context;

/**
 * Created by lulaa on 12/3/2017.
 */

public class MyAgenda extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyAgenda.context = getApplicationContext();
    }

    public static Context getContext() {
        return MyAgenda.context;
    }
}
