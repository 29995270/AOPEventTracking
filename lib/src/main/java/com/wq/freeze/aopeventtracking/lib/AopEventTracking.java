package com.wq.freeze.aopeventtracking.lib;

import android.app.Activity;
import android.content.Context;

import com.wq.freeze.aopeventtracking.lib.annotations.EventTracking;
import com.wq.freeze.aopeventtracking.lib.annotations.PagePause;
import com.wq.freeze.aopeventtracking.lib.annotations.PageResume;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;

/**
 * Created by wangqi on 2015/12/25.
 */
@Aspect
public class AopEventTracking {
    private static final String TRACKING_POINTCUT_METHOD =
            "execution(@com.wq.freeze.aopeventtracking.lib.annotations.EventTracking * *(..)) && @annotation(ann)";

    private static final String TRACKING_POINTCUT_CONSTRUCTOR =
            "execution(@com.wq.freeze.aopeventtracking.lib.annotations.EventTracking *.new(..)) && @annotation(ann)";

    private static final String INIT_POINTCUT_METHOD =
            "execution(@com.wq.freeze.aopeventtracking.lib.annotations.Init * android.app.Application+.onCreate(..))";

    private static final String ACTIVITY_RESUME_POINTCUT_METHOD =
            "execution(@com.wq.freeze.aopeventtracking.lib.annotations.ActivityResume * android.app.Activity+.onResume(..))";

    private static final String ACTIVITY_PAUSE_POINTCUT_METHOD =
            "execution(@com.wq.freeze.aopeventtracking.lib.annotations.ActivityPause * android.app.Activity+.onPause(..))";

    private static final String PAGE_RESUME_POINTCUT_METHOD =
            "execution(@com.wq.freeze.aopeventtracking.lib.annotations.PageResume * *(..)) && @annotation(ann)";

    private static final String PAGE_PAUSE_POINTCUT_METHOD =
            "execution(@com.wq.freeze.aopeventtracking.lib.annotations.PagePause * *(..)) && @annotation(ann)";

    private static final String ON_KILL_PROCESS_POINTCUT_METHOD =
            "execution(@com.wq.freeze.aopeventtracking.lib.annotations.OnKillProcess * *(..))";


    @Pointcut(INIT_POINTCUT_METHOD)
    public void methodAnnotatedWithInit() {}

    @Pointcut(TRACKING_POINTCUT_METHOD)
    public void methodAnnotatedWithTracking(EventTracking ann) {}

    @Pointcut(TRACKING_POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedWithTracking(EventTracking ann) {}

    @Pointcut(ACTIVITY_RESUME_POINTCUT_METHOD)
    public void methodAnnotatedWithActivityResume() {}

    @Pointcut(ACTIVITY_PAUSE_POINTCUT_METHOD)
    public void methodAnnotatedWithActivityPause() {}

    @Pointcut(PAGE_RESUME_POINTCUT_METHOD)
    public void methodAnnotatedWithPageResume(PageResume ann) {}

    @Pointcut(PAGE_PAUSE_POINTCUT_METHOD)
    public void methodAnnotatedWithPagePause(PagePause ann) {}

    @Pointcut(ON_KILL_PROCESS_POINTCUT_METHOD)
    public void methodAnnotatedWithKillProcess() {}


    @Around("methodAnnotatedWithInit()")
    public Object initJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Object sThis = joinPoint.getThis();
        Object result = joinPoint.proceed();

        if (sThis instanceof Context) {
            AnalyticsHelper.init(((Context) sThis));
        }

        return result;
    }

//    @Around("constructorAnnotatedWithTracking(ann) || methodAnnotatedWithTracking(ann)")
//    public Object trackingJoinPoint(ProceedingJoinPoint joinPoint, EventTracking ann) throws Throwable {
//        Object result = joinPoint.proceed();
//
//        if (ann.keys()[0].equals("[unimplemented]") || ann.values()[0].equals("[unimplemented]") || ann.keys() == null || ann.keys().length == 0 || ann.values() == null || ann.values().length == 0 || ann.keys().length != ann.values().length) {
//            AnalyticsHelper.trackingEvent(ann.eventId(), null);
//        } else {
//            HashMap<String, String> map = new HashMap<>();
//            for (int i = 0; i < ann.keys().length; i++) {
//                map.put(ann.keys()[i], ann.values()[i]);
//            }
//
//            AnalyticsHelper.trackingEvent(ann.eventId(), map);
//        }
//
//        return result;
//    }

    @After("constructorAnnotatedWithTracking(ann) || methodAnnotatedWithTracking(ann)")
    public void trackingJoinPoint(JoinPoint point, EventTracking ann) {

        if (ann.keys()[0].equals("[unimplemented]") || ann.values()[0].equals("[unimplemented]") || ann.keys() == null || ann.keys().length == 0 || ann.values() == null || ann.values().length == 0 || ann.keys().length != ann.values().length) {
            AnalyticsHelper.trackingEvent(ann.eventId(), null);
        } else {
            HashMap<String, String> map = new HashMap<>();
            for (int i = 0; i < ann.keys().length; i++) {
                map.put(ann.keys()[i], ann.values()[i]);
            }
            AnalyticsHelper.trackingEvent(ann.eventId(), map);
        }
    }

    @After("methodAnnotatedWithActivityResume()")
    public void activityResumeJoinPoint(JoinPoint point) {
        AnalyticsHelper.onActivityResume((Activity) point.getThis());
    }

    @After("methodAnnotatedWithActivityPause()")
    public void activityPauseJoinPoint(JoinPoint point) {
        AnalyticsHelper.onActivityPause((Activity) point.getThis());
    }

    @After("methodAnnotatedWithPageResume(ann)")
    public void pageResumeJoinPoint(JoinPoint point, PageResume ann) {
        if (ann.pageName().equals("no_name")) {
            AnalyticsHelper.onPageResume(point.getThis().getClass().getSimpleName());
        } else {
            AnalyticsHelper.onPageResume(ann.pageName());
        }
    }

    @After("methodAnnotatedWithPagePause(ann)")
    public void pagePauseJoinPoint(JoinPoint point, PagePause ann) {
        if (ann.pageName().equals("no_name")) {
            AnalyticsHelper.onPagePause(point.getThis().getClass().getSimpleName());
        } else {
            AnalyticsHelper.onPagePause(ann.pageName());
        }
    }

    @Before("methodAnnotatedWithKillProcess()")
    public void killPreocessJoinPoint(JoinPoint point) {
        AnalyticsHelper.onKillProcess();
    }

}
