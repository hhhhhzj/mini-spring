package com.minispring.core;

/**
 * @author Zhijian.H
 * @since 2023/5/28 下午11:50
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
    void addApplicationListener(ApplicationListener listener);
}
