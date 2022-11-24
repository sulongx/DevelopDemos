package com.sulongx.springframework.context;

import com.sulongx.springframework.beans.factory.HierarchicalBeanFactory;
import com.sulongx.springframework.beans.factory.ListableBeanFactory;
import com.sulongx.springframework.core.io.ResourceLoader;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/10/31 14:47
 **/
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
