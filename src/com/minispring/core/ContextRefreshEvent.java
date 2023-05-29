package com.minispring.core;

/**
 * @author Zhijian.H
 * @since 2023/5/28 下午11:49
 */

public class ContextRefreshEvent extends ApplicationEvent{
    private static final long serialVersionUID = 1L;
    public ContextRefreshEvent(Object arg0) {
        super(arg0);
    }

    public String toString() {
        return this.msg;
    }
}