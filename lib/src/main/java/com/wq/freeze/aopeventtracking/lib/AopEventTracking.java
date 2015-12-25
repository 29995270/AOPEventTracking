package com.wq.freeze.aopeventtracking.lib;

import android.content.Context;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by wangqi on 2015/12/25.
 */
@Aspect
public class AopEventTracking {
    private static final String TRACKING_POINTCUT_METHOD =
            "execution(@com.wq.freeze.aopeventtracking.lib.EventTracking * *(..)) && @annotation(ann)";

    private static final String TRACKING_POINTCUT_CONSTRUCTOR =
            "execution(@com.wq.freeze.aopeventtracking.lib.EventTracking *.new(..)) && @annotation(ann)";

    private static final String INIT_POINTCUT_METHOD =
            "execution(@com.wq.freeze.aopeventtracking.lib.Init * *(..))";

    @Pointcut(INIT_POINTCUT_METHOD)
    public void methodAnnotatedWithInit() {}

    @Pointcut(TRACKING_POINTCUT_METHOD)
    public void methodAnnotatedWithTracking(EventTracking ann) {}

    @Pointcut(TRACKING_POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedWithTracking(EventTracking ann) {}

    @Around("methodAnnotatedWithInit()")
    public Object initJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Object sThis = joinPoint.getThis();
        Object result = joinPoint.proceed();

        if (sThis instanceof Context) {
            AnalyticsHelper.init(((Context) sThis));
        }

        return result;
    }

    @Around("constructorAnnotatedWithTracking(ann) || methodAnnotatedWithTracking(ann)")
    public Object trackingJoinPoint(ProceedingJoinPoint joinPoint, EventTracking ann) throws Throwable {
        Object result = joinPoint.proceed();

        AnalyticsHelper.trackingEvent(ann.eventId(), null);

        return result;
    }

}
