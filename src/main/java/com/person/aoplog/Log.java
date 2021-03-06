package com.person.aoplog;

import java.lang.annotation.*;

//定义注解的作用目标：方法参数、方法
@Target({ElementType.PARAMETER,ElementType.METHOD})
//定义注解保留策略：注解会在class字节码中存在
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 要执行的操作类型 如 add delete
     * return
     */

    public String operationType() default "";

    /**
     * 定义执行操作的操作操作人
     * @return
     */

    public String operationName()default "";

}
