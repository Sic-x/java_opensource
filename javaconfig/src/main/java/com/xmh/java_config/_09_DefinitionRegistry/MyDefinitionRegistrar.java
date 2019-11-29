package com.xmh.java_config._09_DefinitionRegistry;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyDefinitionRegistrar implements ImportBeanDefinitionRegistrar{

    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        beanDefinitionRegistry.registerBeanDefinition("myBean",new RootBeanDefinition(MyBean.class));
        beanDefinitionRegistry.registerBeanDefinition("otherBean",new RootBeanDefinition(OtherBean.class));
    }
}
