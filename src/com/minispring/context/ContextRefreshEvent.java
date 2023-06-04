package com.minispring.context;

import com.minispring.core.ApplicationEvent;

/**
 * @author Zhijian.H
 * @since 2023/6/4 下午8:06
 */
public class ContextRefreshEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    public ContextRefreshEvent(Object arg0) {
        super(arg0);
    }

    public String toString() {
        return this.msg;
    }
}
