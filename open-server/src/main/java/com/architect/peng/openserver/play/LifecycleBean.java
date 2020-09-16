package com.architect.peng.openserver.play;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author itpeng
 * @version V1.0
 * @Date 2020/9/16 10:01
 */

public class LifecycleBean implements  InitializingBean , DisposableBean{



    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Play  afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Play  destroy");
    }
}
