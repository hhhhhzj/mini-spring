package com.minispring.core;

import java.util.EventObject;

/**
 * @author Zhijian.H
 * @since 2023/5/28 下午11:48
 */

public class ApplicationEvent extends EventObject {
    private static final long serialVersionUID = 1L;
    protected String msg = null;
    public ApplicationEvent(Object arg0) {
        super(arg0);
        this.msg = arg0.toString();
    }
}