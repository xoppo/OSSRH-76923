package com.fhs.jpa.pojo;

import com.fhs.core.trans.util.ReflectUtils;
import com.fhs.jpa.anno.*;
import com.fhs.jpa.wrapper.QueryWrapper;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 自动转换为一个wrapper
 */
public interface WrapperAble {

    /**
     * 转换为一个wrapper
     *
     * @return
     */
    default QueryWrapper asWrapper() {
        QueryWrapper wrapper = new QueryWrapper();
        List<Field> fields = ReflectUtils.getAllField(this.getClass());
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object tempValue = field.get(this);
                if (tempValue == null || "".equals(tempValue)) {
                    continue;
                }
                if (field.isAnnotationPresent(Eq.class)) {
                    wrapper.eq(field.getName(), tempValue);
                } else if (field.isAnnotationPresent(Like.class)) {
                    wrapper.like(field.getName(), tempValue.toString());
                } else if (field.isAnnotationPresent(Ge.class)) {
                    wrapper.ge(field.getName(), (Comparable<?>) tempValue);
                } else if (field.isAnnotationPresent(Le.class)) {
                    wrapper.le(field.getName(), (Comparable<?>) tempValue);
                }
                else if (field.isAnnotationPresent(GroupOrLike.class)) {
                    final String[] tempFields = field.getAnnotation(GroupOrLike.class).fields();
                    wrapper.or(tempWrapper -> {
                        for (String tempField : tempFields) {
                            tempWrapper.like(tempField,tempValue.toString());
                        }
                    });
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return wrapper;
    }
}
