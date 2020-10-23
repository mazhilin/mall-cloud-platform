package com.mall.cloud.common.container;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * <p>封装Qicloud项目LazyInitProcessorServer类.<br></p> 
 * <p>//TODO...<br></p> 
 * @author Powered by marklin 2020-10-23 22:16
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p> 
 */
@Component
public class LazyInitProcessorServer implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableList) throws BeansException {
		Arrays.stream(configurableList.getBeanDefinitionNames()).forEach(item -> configurableList.getBeanDefinition(item).setLazyInit(Boolean.TRUE));
	}
}
