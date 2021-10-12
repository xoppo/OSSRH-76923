package com.fhs.jpa.wrapper;

/**
 * 普通的queryWrapper 根据字段名
 */
public class QueryWrapper extends AbstractWrapper {
    public QueryWrapper() {
    }

    /**
     * =
     *
     * @param condition 是否需要使用本条件
     * @param fieldName 字段名
     * @param value     值
     * @return this
     */
    public QueryWrapper eq(boolean condition, String fieldName, Object value) {
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
    public QueryWrapper eq(String fieldName, Object value) {
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
    public QueryWrapper ne(boolean condition, String fieldName, Object value) {
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
    public QueryWrapper ne(String fieldName, Object value) {
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
    public QueryWrapper gt(boolean condition, String fieldName, Comparable<?> value) {
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
    public QueryWrapper gt(String fieldName, Comparable<?> value) {
        gt(true, fieldName, value);
        return this;
    }
}
