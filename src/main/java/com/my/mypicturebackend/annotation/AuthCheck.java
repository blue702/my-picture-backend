package com.my.mypicturebackend.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 注解的范围是方法范围
@Target(ElementType.METHOD)
// 注解在项目运行时生效
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    /**
     * 必须有某个角色
     * 用户登录了才可以使用的功能
     */
    String mustRole() default "";
}
