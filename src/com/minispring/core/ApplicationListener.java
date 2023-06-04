package com.minispring.core;

import java.util.EventListener;

/**
 * @author Zhijian.H
 * @since 2023/5/28 下午11:49
 */
public class ApplicationListener implements EventListener {

    void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.toString());
    }
}