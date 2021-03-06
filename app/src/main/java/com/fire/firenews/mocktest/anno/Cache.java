package com.fire.firenews.mocktest.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * Created by bernie on 2017/6/13.
 */

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    TimeUnit timeUnit()  default TimeUnit.NANOSECONDS;
    long time() default -1;
    boolean forceCacheNoNet() default true;

}
