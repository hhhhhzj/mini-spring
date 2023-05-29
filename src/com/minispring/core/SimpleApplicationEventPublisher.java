package com.minispring.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhijian.H
 * @since 2023/5/28 下午11:51
 */
public class SimpleApplicationEventPublisher implements ApplicationEventPublisher{

    List<ApplicationListener> listeners = new ArrayList<>();

    @Override
    public void publishEvent(ApplicationEvent event) {
        for (ApplicationListener listener : listeners) {
            listener.onApplicationEvent(event);
        }
    }
    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.listeners.add(listener);
    }
}
