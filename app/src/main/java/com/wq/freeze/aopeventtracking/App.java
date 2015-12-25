package com.wq.freeze.aopeventtracking;

import android.app.Application;

import com.wq.freeze.aopeventtracking.lib.Init;

/**
 * Created by wangqi on 2015/12/25.
 */
public class App extends Application {
    @Init
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
