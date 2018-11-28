package cn.com.admin.user.service;

import cn.com.common.utils.Tools;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by sky.song on 2018/11/27.
 */
public class PersonService implements BeanFactoryAware,ApplicationContextAware,BeanPostProcessor {

    private Tools tools;

    private BeanFactory factory;


    public Tools getToolInstance() {
        this.tools = (Tools) factory.getBean("tools");
        setTools(tools);
        return tools;
    }

    public void setTools(Tools tools) {
        this.tools = tools;
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.factory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return null;
    }
}
