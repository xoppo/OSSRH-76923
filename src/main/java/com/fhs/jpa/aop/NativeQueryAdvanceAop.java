package com.fhs.jpa.aop;

import com.fhs.jpa.anno.ResultClass;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

@Slf4j
@Aspect
@Component
public class NativeQueryAdvanceAop {


    @Autowired
    private EntityManager em;

    @Around("@annotation(com.fhs.jpa.anno.ResultClass)")
    public Object transResult(ProceedingJoinPoint point) throws Throwable {
        /**
         *  切入点 得到被增强方法的方法签名
         */
        MethodSignature methodSignature = (MethodSignature) point.getSignature();

        /**
         *  方法签名 到被增强方法的方法
         */
        Method method = methodSignature.getMethod();
        if(!method.isAnnotationPresent(Query.class)){
            throw new IllegalArgumentException("请在方法上添加Query注解");
        }
        Object[] args = point.getArgs();
        //拿到sql
        String sql = method.getAnnotation(Query.class).value();
        javax.persistence.Query query = em.createNativeQuery(sql);
        query.unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.aliasToBean(method.getAnnotation(ResultClass.class).value()));
        //如果是使用?1 那么久根据下标赋值
        if(sql.contains("?1")){
            for (int i = 0; i < args.length; i++) {
                query.setParameter((i+1),args[i]);
            }
        }else{
            Class<?>[] parameterTypes = method.getParameterTypes();
            Annotation[][] pa = method.getParameterAnnotations();
            // 二维数组遍历，第一维是参数的个数，第二维是具体的注解值
            for (int i = 0; i < parameterTypes.length; i++) {
                // 每个参数对应的注解数组
                for (Annotation annotation : pa[i]) {
                    if (annotation instanceof Param) {
                        Param param = (Param)annotation;
                        //根据paramtername设置参数
                        query.setParameter(param.value(),args[i]);
                    }
                }
            }
        }
        Type retunType = method.getAnnotatedReturnType().getType();
        //集合的话返回集合
        if(retunType.getTypeName().contains("java.util")){
            return query.getResultList();
        }else if(retunType.getTypeName().startsWith("java.")){
            throw new IllegalArgumentException("返回值不是Javabean，你不要骗我");
        }
        return query.getSingleResult();
    }
}
