package com.xujie.business.common.annotations;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface MyCache {
  String key() default "";

  long expire() default 60;

  TimeUnit timeUnit() default TimeUnit.SECONDS;
}
