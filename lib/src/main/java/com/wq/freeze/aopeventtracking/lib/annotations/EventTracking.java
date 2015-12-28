package com.wq.freeze.aopeventtracking.lib.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wangqi on 2015/12/25.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface EventTracking {
    String eventId();
    String[] keys() default "[unimplemented]";
    String[] values() default "[unimplemented]";
}
