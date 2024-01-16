package com.sulongx.springframework.context.event;

import com.sulongx.springframework.beans.factory.BeanFactory;
import com.sulongx.springframework.context.ApplicationEvent;
import com.sulongx.springframework.context.ApplicationListener;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2022/11/24
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{


    public SimpleApplicationEventMulticaster(BeanFactory beanFactory){
        setBeanFactory(beanFactory);
    }


    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(ApplicationEvent event) {
        for(final ApplicationListener listener : getApplicationListeners(event)){
            listener.onApplicationEvent(event);
        }
    }

}
