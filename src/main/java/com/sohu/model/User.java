/*
* Copyright (c) 2015 Sohu TV. All rights reserved.
*/
package com.sohu.model;
/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2015年12月28日下午2:33:12
 */
public class User {
    private  String daoName;
    private  Long beginTime;
    private  Long expendTime;


    /**
    * <p>Title: </p>
    * <p>Description: </p>
    * @param daoName
    * @param beginTime
    * @param expendTime
    */
    public User(String daoName, Long beginTime, Long expendTime) {
        super();
        this.daoName = daoName;
        this.beginTime = beginTime;
        this.expendTime = expendTime;
    }

    public String getDaoName() {
        return daoName;
    }
    public void setDaoName(String daoName) {
        this.daoName = daoName;
    }
    public Long getBeginTime() {
        return beginTime;
    }
    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }
    public Long getExpendTime() {
        return expendTime;
    }
    public void setExpendTime(Long expendTime) {
        this.expendTime = expendTime;
    }
}
