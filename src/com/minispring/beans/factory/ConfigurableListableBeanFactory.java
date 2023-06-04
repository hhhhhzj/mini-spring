package com.minispring.beans.factory;

import com.minispring.beans.factory.impl.AbstractAutowireCapableBeanFactory;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-06-01 19:36
 */
public interface ConfigurableListableBeanFactory
        extends ListableBeanFactory, AutowireCapableBeanFactory,
        ConfigurableBeanFactory {
}
