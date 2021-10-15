package com.fhs.jpa.anno;

import com.fhs.jpa.sql.JpaNativeRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用原生查询扩展支持
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(JpaNativeRegistrar.class)
public @interface EnableJpaExtNativeQuery {
    String[] basePackages();
}
