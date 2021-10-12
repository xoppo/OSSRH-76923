package com.fhs.jpa.wrapper;

import com.fhs.jpa.lambda.LambdaSett;
import com.fhs.jpa.lambda.SFunction;
import com.fhs.jpa.lambda.SerializedLambda;
import com.fhs.jpa.utils.LambdaUtils;
import java.util.*;

/**
 * Lambda JPA查询构造器
 *
 * @param <T>
 */
public class LambdaQueryWrapper<T>  extends AbstractWrapper<T>{


    /**
     * 缓存
     */
    private static Map<String, LambdaSett> lambdaSettMap = new HashMap<>();


    public LambdaQueryWrapper() {
    }

    /**
     * =
     *
     * @param condition   是否需要使用本条件
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> eq(boolean condition, SFunction<T, ?> fieldLambda, Object value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldLambda2FieldName(fieldLambda))
                    .operator(Operator.EQ).value(value).build());
        }
        return this;
    }

    /**
     * =
     *
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> eq(SFunction<T, ?> fieldLambda, Object value) {
        eq(true, fieldLambda, value);
        return this;
    }

    /**
     * !=
     *
     * @param condition   是否需要使用本条件
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> ne(boolean condition, SFunction<T, ?> fieldLambda, Object value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldLambda2FieldName(fieldLambda))
                    .operator(Operator.NE).value(value).build());
        }
        return this;
    }

    /**
     * !=
     *
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> ne(SFunction<T, ?> fieldLambda, Object value) {
        ne(true, fieldLambda, value);
        return this;
    }


    /**
     * >
     *
     * @param condition   是否需要使用本条件
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> gt(boolean condition, SFunction<T, ?> fieldLambda, Comparable<?> value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldLambda2FieldName(fieldLambda))
                    .operator(Operator.GT).value(value).build());
        }
        return this;
    }

    /**
     * >
     *
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> gt(SFunction<T, ?> fieldLambda, Comparable<?> value) {
        gt(true, fieldLambda, value);
        return this;
    }


    /**
     * >=
     *
     * @param condition   是否需要使用本条件
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> ge(boolean condition, SFunction<T, ?> fieldLambda, Comparable<?> value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldLambda2FieldName(fieldLambda))
                    .operator(Operator.GE).value(value).build());
        }
        return this;
    }

    /**
     * >=
     *
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> ge(SFunction<T, ?> fieldLambda, Comparable<?> value) {
        ge(true, fieldLambda, value);
        return this;
    }


    /**
     * <
     *
     * @param condition   是否需要使用本条件
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> lt(boolean condition, SFunction<T, ?> fieldLambda, Comparable<?> value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldLambda2FieldName(fieldLambda))
                    .operator(Operator.LT).value(value).build());
        }
        return this;
    }

    /**
     * <
     *
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> lt(SFunction<T, ?> fieldLambda, Comparable<?> value) {
        lt(true, fieldLambda, value);
        return this;
    }

    /**
     * <=
     *
     * @param condition   是否需要使用本条件
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> le(boolean condition, SFunction<T, ?> fieldLambda, Comparable<?> value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldLambda2FieldName(fieldLambda))
                    .operator(Operator.LE).value(value).build());
        }
        return this;
    }

    /**
     * <=
     *
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> le(SFunction<T, ?> fieldLambda, Comparable<?> value) {
        le(true, fieldLambda, value);
        return this;
    }

    /**
     * like '%xx%'
     *
     * @param condition   是否需要使用本条件
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> like(boolean condition, SFunction<T, ?> fieldLambda, String value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldLambda2FieldName(fieldLambda))
                    .operator(Operator.LIKE).value("%" + value + "%").build());
        }
        return this;
    }

    /**
     * like '%xx%'
     *
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> like(SFunction<T, ?> fieldLambda, String value) {
        like(true, fieldLambda, value);
        return this;
    }


    /**
     * like 'xx%'
     *
     * @param condition   是否需要使用本条件
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> likeRight(boolean condition, SFunction<T, ?> fieldLambda, String value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldLambda2FieldName(fieldLambda))
                    .operator(Operator.LIKE).value(value + "%").build());
        }
        return this;
    }

    /**
     * like 'xx%'
     *
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> likeRight(SFunction<T, ?> fieldLambda, String value) {
        likeRight(true, fieldLambda, value);
        return this;
    }

    /**
     * like '%xx'
     *
     * @param condition   是否需要使用本条件
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> likeLeft(boolean condition, SFunction<T, ?> fieldLambda, String value) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldLambda2FieldName(fieldLambda))
                    .operator(Operator.LIKE).value("%" + value).build());
        }
        return this;
    }

    /**
     * OR
     * @return this
     */
    public LambdaQueryWrapper<T> or(TempQueryWrapper<T> tempQueryWrapper) {
        LambdaQueryWrapper<T> lambdaQueryWrapper = new LambdaQueryWrapper<T>();
        tempQueryWrapper.exec(lambdaQueryWrapper);
        if(!lambdaQueryWrapper.tempPredicates.isEmpty()){
            this.orTempPredicates.add(lambdaQueryWrapper.tempPredicates);
        }
        return this;
    }





    /**
     * like '%xx'
     *
     * @param fieldLambda lambda
     * @param value       值
     * @return this
     */
    public LambdaQueryWrapper<T> likeLeft(SFunction<T, ?> fieldLambda, String value) {
        likeLeft(true, fieldLambda, value);
        return this;
    }


    /**
     * between lower，upper
     *
     * @param condition   是否使用本条件
     * @param fieldLambda lambda
     * @param lower       最小值
     * @param upper       最大值
     * @return
     */
    public LambdaQueryWrapper<T> between(boolean condition, SFunction<T, ?> fieldLambda, Object lower, Object upper) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldLambda2FieldName(fieldLambda))
                    .operator(Operator.BETWEEN).value(new Object[]{lower, upper}).build());
        }
        return this;
    }

    /**
     * between lower，upper
     *
     * @param fieldLambda lambda
     * @param lower       最小值
     * @param upper       最大值
     * @return
     */
    public LambdaQueryWrapper<T> between(SFunction<T, ?> fieldLambda, Object lower, Object upper) {
        between(true, fieldLambda, lower, upper);
        return this;
    }

    /**
     * in  集合
     *
     * @param condition   是否需要使用本条件
     * @param fieldLambda lambda
     * @param values      值
     * @return this
     */
    public LambdaQueryWrapper<T> in(boolean condition, SFunction<T, ?> fieldLambda, Iterable<?> values) {
        List<Object> valuesList = new ArrayList<>();
        values.forEach(value -> {
            valuesList.add(value);
        });
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldLambda2FieldName(fieldLambda))
                    .operator(Operator.IN).value(valuesList.toArray()).build());
        }
        return this;
    }

    /**
     * in  集合
     *
     * @param fieldLambda lambda
     * @param values      值
     * @return this
     */
    public LambdaQueryWrapper<T> in(SFunction<T, ?> fieldLambda, Iterable<?> values) {
        in(true, fieldLambda, values);
        return this;
    }

    /**
     * in  数组/可变参
     *
     * @param condition   是否需要使用本条件
     * @param fieldLambda lambda
     * @param values      值
     * @return this
     */
    public LambdaQueryWrapper<T> in(boolean condition, SFunction<T, ?> fieldLambda, Object... values) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldLambda2FieldName(fieldLambda))
                    .operator(Operator.IN).value(values).build());
        }
        return this;
    }

    /**
     * in  数组/可变参
     *
     * @param fieldLambda lambda
     * @param values      值
     * @return this
     */
    public LambdaQueryWrapper<T> in(SFunction<T, ?> fieldLambda, Object... values) {
        in(true, fieldLambda, values);
        return this;
    }

    /**
     * notIn  数组/可变参
     *
     * @param condition   是否需要使用本条件
     * @param fieldLambda lambda
     * @param values      值
     * @return this
     */
    public LambdaQueryWrapper<T> notIn(boolean condition, SFunction<T, ?> fieldLambda, Object... values) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldLambda2FieldName(fieldLambda))
                    .operator(Operator.NOTIN).value(values).build());
        }
        return this;
    }

    /**
     * notIn  数组/可变参
     *
     * @param fieldLambda lambda
     * @param values      值
     * @return this
     */
    public LambdaQueryWrapper<T> notIn(SFunction<T, ?> fieldLambda, Object... values) {
        notIn(true, fieldLambda, values);
        return this;
    }

    /**
     *isNull
     * @param condition   是否需要使用本条件
     * @param fieldLambda lambda
     * @return this
     */
    public LambdaQueryWrapper<T> isNull(boolean condition,SFunction<T, ?> fieldLambda) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldLambda2FieldName(fieldLambda))
                    .operator(Operator.ISNULL).build());
        }
        return this;
    }

    /**
     * isNull
     * @param fieldLambda lambda
     * @return this
     */
    public LambdaQueryWrapper<T> isNull(SFunction<T, ?> fieldLambda) {
        isNull(true,fieldLambda);
        return this;
    }

    /**
     * isNotNull
     * @param condition   是否需要使用本条件
     * @param fieldLambda lambda
     * @return this
     */
    public LambdaQueryWrapper<T> isNotNull(boolean condition,SFunction<T, ?> fieldLambda) {
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldLambda2FieldName(fieldLambda))
                    .operator(Operator.NOTNULL).build());
        }
        return this;
    }

    /**
     * isNotNull
     * @param fieldLambda lambda
     * @return this
     */
    public LambdaQueryWrapper<T> isNotNull(SFunction<T, ?> fieldLambda) {
        isNotNull(true,fieldLambda);
        return this;
    }


    /**
     * notIn  集合
     *
     * @param condition   是否需要使用本条件
     * @param fieldLambda lambda
     * @param values      值
     * @return this
     */
    public LambdaQueryWrapper<T> notIn(boolean condition, SFunction<T, ?> fieldLambda, Iterable<?> values) {
        List<Object> valuesList = new ArrayList<>();
        values.forEach(value -> {
            valuesList.add(value);
        });
        if (condition) {
            tempPredicates.add(TempPredicate.builder().fieldName(fieldLambda2FieldName(fieldLambda))
                    .operator(Operator.NOTIN).value(valuesList.toArray()).build());
        }
        return this;
    }

    /**
     * notIn  集合
     *
     * @param fieldLambda lambda
     * @param values      值
     * @return this
     */
    public LambdaQueryWrapper<T> notIn(SFunction<T, ?> fieldLambda, Iterable<?> values) {
        notIn(true, fieldLambda, values);
        return this;
    }


    /**
     * 根据字段排序 正序
     *
     * @param fieldLambda lambda
     * @return
     */
    public LambdaQueryWrapper<T> orderByAsc(SFunction<T, ?> fieldLambda) {
        orderByList.add(OrderBy.builder().isDesc(false).property(fieldLambda2FieldName(fieldLambda)).build());
        return this;
    }

    /**
     * 根据字段排序 倒序
     *
     * @param fieldLambda lambda
     * @return
     */
    public LambdaQueryWrapper<T> orderByDesc(SFunction<T, ?> fieldLambda) {
        orderByList.add(OrderBy.builder().isDesc(true).property(fieldLambda2FieldName(fieldLambda)).build());
        return this;
    }


    /**
     * lambda转字段名
     *
     * @param fieldLambda
     * @return
     */
    private String fieldLambda2FieldName(SFunction<T, ?> fieldLambda) {
        return getLambdaSett(fieldLambda).getFiledName();
    }

    /**
     * 根据fieldLambda获取 LambdaSett
     *
     * @param fieldLambda 过滤字段get方法的
     * @return
     */
    protected LambdaSett getLambdaSett(SFunction<T, ?> fieldLambda) {
        SerializedLambda lambda = LambdaUtils.resolve(fieldLambda);
        String filedName = resolveFieldName(lambda.getImplMethodName());
        String key = lambda.getImplClass().getName() + filedName;
        LambdaSett lambdaSett;
        if (lambdaSettMap.containsKey(key)) {
            lambdaSett = lambdaSettMap.get(key);
        } else {
            lambdaSett = new LambdaSett(lambda, filedName);
            lambdaSettMap.put(key, lambdaSett);
        }
        return lambdaSett;
    }


    /**
     * 方法名转字段名
     *
     * @param methodName
     * @return
     */
    private static String resolveFieldName(String methodName) {
        methodName = methodName.replace("get", "");
        return (methodName.charAt(0) + "").toLowerCase() + methodName.substring(1);
    }




    /**
     * 用于or的情况，比如 订单号或者名称包含某个
     * @param <T>
     */
    public static interface TempQueryWrapper<T>{
        public void exec(LambdaQueryWrapper<T> wrapper);
    }
}
