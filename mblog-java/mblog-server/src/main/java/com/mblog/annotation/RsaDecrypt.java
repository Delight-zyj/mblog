package com.mblog.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 解密注解和拦截器
 */
public @interface RsaDecrypt {
}
