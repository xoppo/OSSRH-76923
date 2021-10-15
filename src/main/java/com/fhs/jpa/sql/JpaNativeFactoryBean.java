package com.fhs.jpa.sql;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.persistence.EntityManager;
import java.util.Objects;

/**
 * 用于生成代理类
 */
public class JpaNativeFactoryBean implements FactoryBean<Object>,  ApplicationContextAware {


    private Class<?> type;

    private ApplicationContext applicationContext;

    /**
     * 获取代理对象（这儿是关键之处）
     *
     * @return
     * @throws Exception
     */
    @Override
    public Object getObject() throws Exception {
        JpaNativeQueryProxy proxy = new JpaNativeQueryProxy();
        proxy.setEm(applicationContext.getBean(EntityManager.class));
        return proxy.getProxy(type);
    }

    @Override
    public Class<?> getObjectType() {
        return this.type;
    }



    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }



    public void setType(Class<?> type) {
        this.type = type;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JpaNativeFactoryBean that = (JpaNativeFactoryBean) o;
        return Objects.equals(this.applicationContext, that.applicationContext)
                && Objects.equals(this.type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.applicationContext, this.type);
    }

    @Override
    public String toString() {
        return "JpaNativeFactoryBean{" + "type=" +
                this.type + ", " +
                "', "  + "', " +
                "', " + ", " + "applicationContext=" +
                this.applicationContext + ", " + ", " + "}";
    }

}
