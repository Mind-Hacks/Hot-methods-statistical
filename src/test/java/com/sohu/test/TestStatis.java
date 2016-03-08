package com.sohu.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sohu.dao.DaoMethod;
import com.sohu.util.AspectUtil;
import com.sohu.util.DataHandleOut;
/*
 * Copyright (c) 2015 Sohu TV. All rights reserved.
 */
/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2015年12月25日下午6:24:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-aop-aspectj.xml"})
public class TestStatis {
    @Autowired
    DaoMethod daoMethod;

    @Autowired
    AspectUtil aspectUtil;

    @Autowired
    DataHandleOut dataHandleOut;

    @Test
    public void Method(){

        new Thread(){
            @Override
            public void run() {
                while(true){
                    aspectUtil.entrance();
                }
            }
        }.start();  //放入队列

        new Thread(){
            @Override
            public void run() {
                while(true){
                    System.out.println("每隔0.1秒，输出一次");
                    try {
                        Thread.currentThread().sleep(100);
                        dataHandleOut.HandAndPut(aspectUtil.getMinuteInvokeCostTime());
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        //输出结果、

        while(true){
            daoMethod.method1();
            daoMethod.method2();
            daoMethod.method3();
            daoMethod.method4();
            daoMethod.method5();

            daoMethod.method2();
            daoMethod.method3();
            daoMethod.method4();
        }
    }

}