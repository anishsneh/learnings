package com.anishsneh.demo.spring.framework;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

/**
 * The Class LoggerPostProcessor.
 * 
 * @author Anish Sneh
 */
public class LoggerPostProcessor implements BeanPostProcessor {

  /* (non-Javadoc)
   * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
   */
  public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
    return bean;
  }

  /* (non-Javadoc)
   * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
   */
  public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
    ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {
      public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        if (field.getAnnotation(ApplicationLogger.class) != null) {
          final Logger logger = LoggerFactory.getLogger(bean.getClass());
          final boolean wasAccessible = field.isAccessible();
          field.setAccessible(true);
          field.set(bean, logger);
          field.setAccessible(wasAccessible);
        }
      }
    });
    return bean;
  }
}