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
 * @Date 2015年12月28日下午2:53:35
 */
public class Server {
    private String name;
    private long   totalTimes;  //总共调用次数
    private float   averageCost;  //平均调用时间
    private long   MaxCostTime;     //99%的调用时间

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getTotalTimes() {
        return totalTimes;
    }
    public void setTotalTimes(long totalTimes) {
        this.totalTimes = totalTimes;
    }
    public float getAverageCost() {
        return averageCost;
    }
    public void setAverageCost(float averageCost) {
        this.averageCost = averageCost;
    }
    public long getMaxCostTime() {
        return MaxCostTime;
    }
    public void setMaxCostTime(long maxCostTime) {
        MaxCostTime = maxCostTime;
    }
    @Override
    public String toString() {
        return "Server [name=" + name + ", totalTimes=" + totalTimes + ", averageCost=" + averageCost
                + ", MaxCostTime=" + MaxCostTime + "]";
    }


}
