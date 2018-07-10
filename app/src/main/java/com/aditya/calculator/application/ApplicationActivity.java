package com.aditya.calculator.application;

import android.app.Application;

import com.aditya.calculator.utils.TypefaceUtil;

public class ApplicationActivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "GoogleSans-Regular.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
    }
}
