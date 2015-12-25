package com.wq.freeze.aopeventtracking.lib;

import android.content.Context;
import android.util.Log;

import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;

import java.util.Map;

/**
 * Created by wangqi on 2015/12/25.
 */
public class AnalyticsHelper {
    private static Context sContext;

    public static void init(Context context) {
        Log.v("AAA", "init");
        sContext = context;
        AnalyticsConfig.setChannel("YOUR_CHANNEL"); // or you can setChannel in androidManifest
        AnalyticsConfig.enableEncrypt(true);
//        MobclickAgent.openActivityDurationTrack(false);
    }

    public static void trackingEvent(String event, Map<String, String> args) {
        Log.v("AAA", "event " + event);
        if (args == null) {
            MobclickAgent.onEvent(sContext, event);
        } else {
            MobclickAgent.onEvent(sContext, event, args);
        }

    }
}
