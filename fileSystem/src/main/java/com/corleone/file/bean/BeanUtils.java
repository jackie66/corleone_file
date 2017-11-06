package com.corleone.file.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring IOC上下文工具类
 * Created by jackie on
 * 2017/10/27.
 */
@Component
public class BeanUtils implements ApplicationContextAware {

    //当前IOC
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * bean都是采用类名作为bean的key，这里直接获取目的Class的bean
     *
     * @param beanClass 目标bean的Class对象
     * @param <T>       bean
     * @return 目标bean对象
     */
    public static <T> T getBean(Class<T> beanClass) {
        String className = beanClass.getSimpleName();
        String beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
        Object object = applicationContext.getBean(beanName);
        return (T) object;
    }
}
