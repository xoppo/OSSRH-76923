package com.fhs.jpa.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 比如姓名或者昵称都包含 张的就可以给fields 传name和nickName
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GroupOrLike {
    String[] fields();
}
