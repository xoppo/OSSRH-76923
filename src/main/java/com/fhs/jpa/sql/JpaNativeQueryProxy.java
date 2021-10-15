package com.fhs.jpa.sql;

import com.fhs.jpa.anno.ResultClass;
import lombok.Data;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.hibernate.SQLQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * 原生sql查询返回dto
 */
@Data
public class JpaNativeQueryProxy implements MethodInterceptor, Serializable  {

    private static final long serialVersionUID = 1L;


    private final Enhancer enhancer = new Enhancer();

    private EntityManager em;

    /**
     * 获取代理对象
     *
     * @param clazz
     * @return
     */
    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * 这一段代码是重点，为了获取参数名，我此处使用的cglib代理。
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = exec(method, objects);
        return result;
    }

    private Object exec(Method method, Object[] objects) {
        if(!method.isAnnotationPresent(ResultClass.class)){
            throw new IllegalArgumentException("请在方法上添加DtoClass注解");
        }
        if(!method.isAnnotationPresent(Query.class)){
            throw new IllegalArgumentException("请在方法上添加Query注解");
        }
        //拿到sql
        String sql = method.getAnnotation(Query.class).value();
        javax.persistence.Query query = em.createNativeQuery(sql);
        query.unwrap(SQLQuery.class)
                .setResultTransformer(Transformers.aliasToBean(method.getAnnotation(ResultClass.class).value()));
        if(sql.contains("?1")){
            for (int i = 0; i < objects.length; i++) {
                query.setParameter((i+1),objects[i]);
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
                        query.setParameter(param.value(),objects[i]);
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
