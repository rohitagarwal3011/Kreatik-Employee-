package com.app.rbc.employee.app;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.orm.SugarContext;

import io.fabric.sdk.android.Fabric;

/**
 * Created by rohit on 21/6/17.
 */

public class AppController extends Application {
    private static final AppController ourInstance = new AppController();

    static AppController getInstance() {
        return ourInstance;
    }

    public AppController() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        Fresco.initialize(this);
        SugarContext.init(this);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }


}
