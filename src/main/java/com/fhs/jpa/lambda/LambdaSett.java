package com.fhs.jpa.lambda;

import com.fhs.core.trans.util.ReflectUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * 字段配置
 *
 * @author wanglei
 * @date 2020 -02-06 11:06:32
 */
@Data
public class LambdaSett {


    /**
     * 字符串
     */
    public static final int STR = 0;
    /**
     * 日期
     */
    public static final int DATE = 1;
    /**
     * 数字
     */
    public static final int NUMBER = 2;
    /**
     * 字段
     */
    private Field field;
    /**
     * 字段类型
     */
    private int fieldType;
    /**
     * 字段名
     */
    private String filedName;

    private Class implClass;

    public LambdaSett(SerializedLambda lambda, String filedName) {
        this.implClass = lambda.getImplClass();
        field = ReflectUtils.getDeclaredField(lambda.getImplClass(), filedName);
        this.filedName = filedName;
        Class<?> type = field.getType();
        if (type == String.class) {
            fieldType = STR;
        } else if (type == Number.class || Number.class.isAssignableFrom(type)) {
            fieldType = NUMBER;
        } else if (type == Date.class || Date.class.isAssignableFrom(type)) {
            fieldType = DATE;
        } else {
            throw new RuntimeException("Wrapper<T> 只能用于 number str date ");
        }
    }
}
