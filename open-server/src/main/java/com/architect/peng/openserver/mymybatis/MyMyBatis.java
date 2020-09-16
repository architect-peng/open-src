package com.architect.peng.openserver.mymybatis;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author itpeng
 * @version V1.0
 * @Date 2020/9/16 10:24
 */
@Component
public class MyMyBatis implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        final MyDao declaredAnnotation = bean.getClass().getDeclaredAnnotation(MyDao.class);
        if (declaredAnnotation!=null){
            System.out.println("mymybatis  处理 之前");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        final MyDao declaredAnnotation = bean.getClass().getDeclaredAnnotation(MyDao.class);
        if (declaredAnnotation!=null){
            System.out.println("mymybatis  处理  之后");
        }
        return bean;
    }
}
