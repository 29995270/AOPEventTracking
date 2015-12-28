package com.wq.freeze.aopeventtracking.lib;

import android.app.Activity;
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
        if (args == null) {
            Log.v("AAA", "event " + event);
            MobclickAgent.onEvent(sContext, event);
        } else {
            Log.v("AAA", "event " + event);
            for (Map.Entry<String, String> entry : args.entrySet()) {
                Log.v("AAA", entry.getKey() + "__" + entry.getValue());
            }
            MobclickAgent.onEvent(sContext, event, args);
        }

    }

    public static void onActivityResume(Activity activity) {
        Log.v("AAA", "onActivityResume " + activity.getClass().getSimpleName());
        MobclickAgent.onResume(activity);
    }

    public static void onActivityPause(Activity activity) {
        Log.v("AAA", "onActivityPause " + activity.getClass().getSimpleName());
        MobclickAgent.onPause(activity);
    }

    public static void onPageResume(String pageName) {
        Log.v("AAA", "onPageResume " + pageName);
        MobclickAgent.onPageStart(pageName);
    }

    public static void onPagePause(String pageName) {
        Log.v("AAA", "onPagePause " + pageName);
        MobclickAgent.onPageEnd(pageName);
    }

    public static void onKillProcess() {
        Log.v("AAA", "onKillProcess");
        MobclickAgent.onKillProcess(sContext);
    }
}
