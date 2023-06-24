package com.test.service;

import com.minispring.core.Autowired;

/**
 * @author Zhijian.H
 * @since 2023/6/11 下午11:42
 */
public class Action1 implements IAction {

    @Override
    public void doAction() {
        System.out.println("really do action");
    }
}