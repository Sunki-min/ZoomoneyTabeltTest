package com.hardcopy.btchat;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by DELL on 2016-12-03.
 */

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
/*
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "Dosis-Medium.ttf"))
                .addBold(Typekit.createFromAsset(this, "Dosis-Bold.ttf"))
                .addCustom1(Typekit.createFromAsset(this, "Dosis-ExtraBold.ttf"));// "fonts/폰트.ttf"*/
    }
}
