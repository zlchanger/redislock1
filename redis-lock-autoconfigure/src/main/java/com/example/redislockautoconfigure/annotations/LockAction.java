package com.example.redislockautoconfigure.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
/**
 * @Auther: changzhaoliang
 * @Date: 2018/8/29 19:56
 * @Description:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LockAction {
    /** 锁的资源，key。支持spring El表达式*/
    @AliasFor("key")
    String value() default "'default'";

    @AliasFor("value")
    String key() default "'default'";

    /** 持锁时间,单位毫秒*/
    long keepMills() default 30000;

    /** 当获取失败时候动作*/
    LockFailAction action() default LockFailAction.CONTINUE;

    public enum LockFailAction{
        /** 放弃 */
        GIVEUP,
        /** 继续 */
        CONTINUE;
    }

    /** 重试的间隔时间,设置GIVEUP忽略此项*/
    long sleepMills() default 200;

    /** 重试次数*/
    int retryTimes() default 5;
}
