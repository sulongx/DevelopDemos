package com.sulongx.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.sulongx.springframework.beans.exception.BeansException;
import com.sulongx.springframework.beans.factory.PropertyValue;
import com.sulongx.springframework.beans.factory.config.BeanDefinition;
import com.sulongx.springframework.beans.factory.config.BeanReference;
import com.sulongx.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.sulongx.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.sulongx.springframework.core.io.Resource;
import com.sulongx.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2022/10/30
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {



    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry){
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try (InputStream inputStream = resource.getInputStream()){
            doLoadBeanDefinitions(inputStream);
        }catch (IOException | ClassNotFoundException e){
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for(Resource resource : resources){
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for(String location : locations){
            this.loadBeanDefinitions(location);
        }
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException{
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if(!(childNodes.item(i) instanceof Element)){
                continue;
            }
            if(!"bean".equals(childNodes.item(i).getNodeName())){
                continue;
            }

            //解析标签
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            String initMethod = bean.getAttribute("init-method");
            String destroyMethodName = bean.getAttribute("destroy-method");
            Class<?> clazz = Class.forName(className);
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if(StrUtil.isEmpty(beanName)){
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            //定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethodName);
            //读取属性并填充
            for(int j = 0; j < bean.getChildNodes().getLength(); j++){
                if(!(bean.getChildNodes().item(j) instanceof Element)){
                    continue;
                }
                if(!"property".equals(bean.getChildNodes().item(j).getNodeName())){
                    continue;
                }
                //解析标签
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                //获取属性值:引用对象、值对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                //创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if(getRegistry().containsBeanDefinition(beanName)){
                throw new BeansException("Duplicate beanName["+ beanName +"] is not allowed");
            }
            //注册BeanDefinition
            getRegistry().registryBeanDefinition(beanName, beanDefinition);
        }
    }
}
