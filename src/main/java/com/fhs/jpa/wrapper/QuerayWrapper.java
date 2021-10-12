package com.fhs.jpa.wrapper;

import com.fhs.jpa.lambda.SFunction;

/**
 * 普通的queryWrapper 根据字段名
 */
public class QuerayWrapper extends AbstractWrapper {
    public QuerayWrapper() {
    }

    /**
     * =
     *
     * @param condition 是否需要使用本条件
     * @param fieldName 字段名
     * @param value     值
     * @return this
     */
    public QuerayWrapper eq(boolean condition, String fieldName, Object value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldName)
                    .operator(Operator.EQ).value(value).build());
        }
        return this;
    }

    /**
     * =
     *
     * @param fieldName 字段名
     * @param value     值
     * @return this
     */
    public QuerayWrapper eq(String fieldName, Object value) {
        eq(true, fieldName, value);
        return this;
    }

    /**
     * !=
     *
     * @param condition 是否需要使用本条件
     * @param fieldName 字段名
     * @param value     值
     * @return this
     */
    public QuerayWrapper ne(boolean condition, String fieldName, Object value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldName)
                    .operator(Operator.NE).value(value).build());
        }
        return this;
    }

    /**
     * !=
     *
     * @param fieldName 字段名
     * @param value     值
     * @return this
     */
    public QuerayWrapper ne(String fieldName, Object value) {
        ne(true, fieldName, value);
        return this;
    }

    /**
     * >
     *
     * @param condition 是否需要使用本条件
     * @param fieldName 字段名
     * @param value     值
     * @return this
     */
    public QuerayWrapper gt(boolean condition, String fieldName, Comparable<?> value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldName)
                    .operator(Operator.GT).value(value).build());
        }
        return this;
    }

    /**
     * >
     * @param fieldName 字段名
     * @param value     值
     * @return this
     */
    public QuerayWrapper gt(String fieldName, Comparable<?> value) {
        gt(true, fieldName, value);
        return this;
    }
}
