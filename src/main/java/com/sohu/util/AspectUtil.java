/*
 * Copyright (c) 2015 Sohu TV. All rights reserved.
 */
package com.sohu.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sohu.model.User;

/**
 * <P>
 * Description:
 * </p>
 *
 * @author wenbozhang
 * @version 1.0
 * @Date 2015年12月25日下午6:02:12
 */
@Component
@Aspect
// 定义
public class AspectUtil {
    private final Logger logger = LoggerFactory.getLogger(AspectUtil.class);
    private final LinkedBlockingQueue<User> queue = new LinkedBlockingQueue<User>(100);

    private Map<String, Map<Long, Long>> minuteInvokeCostTime = new HashMap<String, Map<Long, Long>>();

    private Map<String, Map<Long, Long>> hourInvokeCostTime = new HashMap<String, Map<Long, Long>>();
    private Map<String, Map<Long, Long>> dayInvokeCostTime = new HashMap<String, Map<Long, Long>>();

    private final long minuteStartTime = System.currentTimeMillis(); // 5分钟map容器开始存放数据的时间

    private final long hourStartTime = System.currentTimeMillis(); // 1小时map容器开始存放数据的时间

    private final long dayStartTime = System.currentTimeMillis(); // 1天map容器开始存放数据的时间

    private final DataHandleOut dataHandleOut = new DataHandleOut();

    @Around("execution(* com.sohu.dao..*.*(..))")
    public Object slow(ProceedingJoinPoint joinPoint) {
        Long beginTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            Long expend = (System.currentTimeMillis() - beginTime);
            String targetPackage = joinPoint.getTarget().getClass().getPackage().getName();
            String targetClass = joinPoint.getTarget().getClass().getName();
            String targetMethod = joinPoint.getSignature().getName();
            String name = targetPackage + targetClass + targetMethod;
            User user = new User(name, beginTime, expend);
            try {
                queue.put(user); // 队里这里需要研究一下
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void entrance() {//线程A将User，分别放入三个不同的容器
        try {
            User InvokeUser = queue.take();
            dataHandleOut.DataHandle(InvokeUser, minuteInvokeCostTime, InvokeUser.getBeginTime(), 0);
            dataHandleOut.DataHandle(InvokeUser, hourInvokeCostTime, InvokeUser.getBeginTime(), 1);
            dataHandleOut.DataHandle(InvokeUser, dayInvokeCostTime, InvokeUser.getBeginTime(), 2);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    public Map<String, Map<Long, Long>> getMinuteInvokeCostTime() {
        return minuteInvokeCostTime;
    }

    public void setMinuteInvokeCostTime(Map<String, Map<Long, Long>> minuteInvokeCostTime) {
        this.minuteInvokeCostTime = minuteInvokeCostTime;
    }

    public Map<String, Map<Long, Long>> getHourInvokeCostTime() {
        return hourInvokeCostTime;
    }

    public void setHourInvokeCostTime(Map<String, Map<Long, Long>> hourInvokeCostTime) {
        this.hourInvokeCostTime = hourInvokeCostTime;
    }

    public Map<String, Map<Long, Long>> getDayInvokeCostTime() {
        return dayInvokeCostTime;
    }

    public void setDayInvokeCostTime(Map<String, Map<Long, Long>> dayInvokeCostTime) {
        this.dayInvokeCostTime = dayInvokeCostTime;
    }
}
