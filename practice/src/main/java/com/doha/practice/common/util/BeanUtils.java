package com.doha.practice.common.util;

import com.doha.practice.common.config.ApplicationContextProvider;
import org.springframework.context.ApplicationContext;

public class BeanUtils {
    public static Object getBean(String beanName){
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        return applicationContext.getBean(beanName);
    }
}
