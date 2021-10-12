package com.fhs.jpa.wrapper;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * >=
     *
     * @param condition   是否需要使用本条件
     * @param @param fieldName 字段名
     * @param value       值
     * @return this
     */
    public QueryWrapper ge(boolean condition, String fieldName, Comparable<?> value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldName)
                    .operator(Operator.GE).value(value).build());
        }
        return this;
    }

    /**
     * >=
     *
     * @param @param fieldName 字段名
     * @param value       值
     * @return this
     */
    public QueryWrapper ge(String fieldName, Comparable<?> value) {
        ge(true, fieldName, value);
        return this;
    }
    
    /**
     * <
     *
     * @param condition   是否需要使用本条件
     * @param fieldName 字段名
     * @param value       值
     * @return this
     */
    public QueryWrapper lt(boolean condition, String fieldName, Comparable<?> value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldName)
                    .operator(Operator.LT).value(value).build());
        }
        return this;
    }

    /**
     * <
     *
     * @param fieldName 字段名
     * @param value       值
     * @return this
     */
    public QueryWrapper lt(String fieldName, Comparable<?> value) {
        lt(true, fieldName, value);
        return this;
    }

    /**
     * <=
     *
     * @param condition   是否需要使用本条件
     * @param fieldName 字段名
     * @param value       值
     * @return this
     */
    public QueryWrapper le(boolean condition, String fieldName, Comparable<?> value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldName)
                    .operator(Operator.LE).value(value).build());
        }
        return this;
    }

    /**
     * <=
     *
     * @param fieldName 字段名
     * @param value       值
     * @return this
     */
    public QueryWrapper le(String fieldName, Comparable<?> value) {
        le(true, fieldName, value);
        return this;
    }

    /**
     * like '%xx%'
     *
     * @param condition   是否需要使用本条件
     * @param fieldName 字段名
     * @param value       值
     * @return this
     */
    public QueryWrapper like(boolean condition, String fieldName, String value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldName)
                    .operator(Operator.LIKE).value("%" + value + "%").build());
        }
        return this;
    }

    /**
     * like '%xx%'
     *
     * @param fieldName 字段名
     * @param value       值
     * @return this
     */
    public QueryWrapper like(String fieldName, String value) {
        like(true, fieldName, value);
        return this;
    }


    /**
     * like 'xx%'
     *
     * @param condition   是否需要使用本条件
     * @param fieldName 字段名
     * @param value       值
     * @return this
     */
    public QueryWrapper likeRight(boolean condition, String fieldName, String value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldName)
                    .operator(Operator.LIKE).value(value + "%").build());
        }
        return this;
    }

    /**
     * like 'xx%'
     *
     * @param fieldName 字段名
     * @param value       值
     * @return this
     */
    public QueryWrapper likeRight(String fieldName, String value) {
        likeRight(true, fieldName, value);
        return this;
    }

    /**
     * like '%xx'
     *
     * @param condition   是否需要使用本条件
     * @param fieldName 字段名
     * @param value       值
     * @return this
     */
    public QueryWrapper likeLeft(boolean condition, String fieldName, String value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldName)
                    .operator(Operator.LIKE).value("%" + value).build());
        }
        return this;
    }

    /**
     * OR
     * @return this
     */
    public QueryWrapper or(TempQueryWrapper tempQueryWrapper) {
        QueryWrapper queryWrapper = new QueryWrapper();
        tempQueryWrapper.exec(queryWrapper);
        if(!queryWrapper.tempPredicates.isEmpty()){
            this.orTempPredicates.add(queryWrapper.tempPredicates);
        }
        return this;
    }





    /**
     * like '%xx'
     *
     * @param fieldName 字段名
     * @param value       值
     * @return this
     */
    public QueryWrapper likeLeft(String fieldName, String value) {
        likeLeft(true, fieldName, value);
        return this;
    }


    /**
     * between lower，upper
     *
     * @param condition   是否使用本条件
     * @param fieldName 字段名
     * @param lower       最小值
     * @param upper       最大值
     * @return
     */
    public QueryWrapper between(boolean condition, String fieldName, Object lower, Object upper) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldName)
                    .operator(Operator.BETWEEN).value(new Object[]{lower, upper}).build());
        }
        return this;
    }

    /**
     * between lower，upper
     *
     * @param fieldName 字段名
     * @param lower       最小值
     * @param upper       最大值
     * @return
     */
    public QueryWrapper between(String fieldName, Object lower, Object upper) {
        between(true, fieldName, lower, upper);
        return this;
    }

    /**
     * in  集合
     *
     * @param condition   是否需要使用本条件
     * @param fieldName 字段名
     * @param values      值
     * @return this
     */
    public QueryWrapper in(boolean condition, String fieldName, Iterable<?> values) {
        List<Object> valuesList = new ArrayList<>();
        values.forEach(value -> {
            valuesList.add(value);
        });
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldName)
                    .operator(Operator.IN).value(valuesList.toArray()).build());
        }
        return this;
    }

    /**
     * in  集合
     *
     * @param fieldName 字段名
     * @param values      值
     * @return this
     */
    public QueryWrapper in(String fieldName, Iterable<?> values) {
        in(true, fieldName, values);
        return this;
    }

    /**
     * in  数组/可变参
     *
     * @param condition   是否需要使用本条件
     * @param fieldName 字段名
     * @param values      值
     * @return this
     */
    public QueryWrapper in(boolean condition, String fieldName, Object... values) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldName)
                    .operator(Operator.IN).value(values).build());
        }
        return this;
    }

    /**
     * in  数组/可变参
     *
     * @param fieldName 字段名
     * @param values      值
     * @return this
     */
    public QueryWrapper in(String fieldName, Object... values) {
        in(true, fieldName, values);
        return this;
    }

    /**
     * notIn  数组/可变参
     *
     * @param condition   是否需要使用本条件
     * @param fieldName 字段名
     * @param values      值
     * @return this
     */
    public QueryWrapper notIn(boolean condition, String fieldName, Object... values) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldName)
                    .operator(Operator.NOTIN).value(values).build());
        }
        return this;
    }

    /**
     * notIn  数组/可变参
     *
     * @param fieldName 字段名
     * @param values      值
     * @return this
     */
    public QueryWrapper notIn(String fieldName, Object... values) {
        notIn(true, fieldName, values);
        return this;
    }

    /**
     *isNull
     * @param condition   是否需要使用本条件
     * @param fieldName 字段名
     * @return this
     */
    public QueryWrapper isNull(boolean condition,String fieldName) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldName)
                    .operator(Operator.ISNULL).build());
        }
        return this;
    }

    /**
     * isNull
     * @param fieldName 字段名
     * @return this
     */
    public QueryWrapper isNull(String fieldName) {
        isNull(true,fieldName);
        return this;
    }

    /**
     * isNotNull
     * @param condition   是否需要使用本条件
     * @param fieldName 字段名
     * @return this
     */
    public QueryWrapper isNotNull(boolean condition,String fieldName) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldName)
                    .operator(Operator.NOTNULL).build());
        }
        return this;
    }

    /**
     * isNotNull
     * @param fieldName 字段名
     * @return this
     */
    public QueryWrapper isNotNull(String fieldName) {
        isNotNull(true,fieldName);
        return this;
    }


    /**
     * notIn  集合
     *
     * @param condition   是否需要使用本条件
     * @param fieldName 字段名
     * @param values      值
     * @return this
     */
    public QueryWrapper notIn(boolean condition, String fieldName, Iterable<?> values) {
        List<Object> valuesList = new ArrayList<>();
        values.forEach(value -> {
            valuesList.add(value);
        });
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldName)
                    .operator(Operator.NOTIN).value(valuesList.toArray()).build());
        }
        return this;
    }

    /**
     * notIn  集合
     *
     * @param fieldName 字段名
     * @param values      值
     * @return this
     */
    public QueryWrapper notIn(String fieldName, Iterable<?> values) {
        notIn(true, fieldName, values);
        return this;
    }


    /**
     * 根据字段排序 正序
     *
     * @param fieldName 字段名
     * @return
     */
    public QueryWrapper orderByAsc(String fieldName) {
        orderByList.add(OrderBy.builder().isDesc(false).property(fieldName).build());
        return this;
    }

    /**
     * 根据字段排序 倒序
     *
     * @param fieldName 字段名
     * @return
     */
    public QueryWrapper orderByDesc(String fieldName) {
        orderByList.add(OrderBy.builder().isDesc(true).property(fieldName).build());
        return this;
    }


    /**
     * 合并另外一个wrapper
     * @param wrapper 另外的wrapper
     * @return
     */
    public QueryWrapper merge(AbstractWrapper wrapper){
        if(!wrapper.orderByList.isEmpty()){
            this.orderByList.addAll(wrapper.orderByList);
        }
        if(!wrapper.orTempPredicates.isEmpty()){
            this.orTempPredicates.addAll(wrapper.orTempPredicates);
        }
        if(!wrapper.tempPredicates.isEmpty()){
            this.tempPredicates.addAll(wrapper.tempPredicates);
        }
        return  this;
    }


    /**
     * 用于or的情况，比如 订单号或者名称包含某个
     */
    public static interface TempQueryWrapper{
        public void exec(QueryWrapper wrapper);
    }
}
