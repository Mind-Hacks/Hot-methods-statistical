/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sohu.model.Server;
import com.sohu.model.User;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年1月11日下午3:47:28
 */
@Component
public class DataHandleOut {

    public void DataHandle(User user,Map<String,Map<Long, Long>> Bigmap,long startTime,int flag){
        Map<Long, Long> smallMap=Bigmap.get(user.getDaoName());
        if (smallMap == null) {
            smallMap = new HashMap<Long, Long>();
            Bigmap.put(user.getDaoName(), smallMap);
        }

        Long times= smallMap.get(user.getExpendTime());
        if(times==null){
                  times=0l;
        }
        smallMap.put(user.getExpendTime(),++times);
    }

    public List<Server> DataOut(Map<String, Map<Long, Long>> bigMap){

        List<Server> result=new ArrayList<>();
        Iterator<Map.Entry<String, Map<Long, Long>>> bigEntries = bigMap.entrySet().iterator();
        while (bigEntries.hasNext()) {
            Server server=new Server();         //结果对象

            long totalTimes = 0l;    //总调用次数
            long totalCost = 0l;    //总花费时间
            List<Long> list = new ArrayList<Long>();            //每个name  创建一个 时间list

            Map.Entry<String, Map<Long, Long>> bigEntry = bigEntries.next();
            String methadName=bigEntry.getKey();
            server.setName(methadName);                         //得到名字
            Map<Long, Long> smallMap=bigEntry.getValue();

            Iterator<Map.Entry<Long, Long>> smallEntries = bigEntry.getValue().entrySet().iterator();
            while(smallEntries.hasNext()){
                Map.Entry<Long, Long> entry = smallEntries.next();
                long cost =entry.getKey();
                long times =entry.getValue();
                totalTimes += times;
                totalCost += times * cost;
                list.add(cost);
            }//内存map的数据遍历结束
                server.setTotalTimes(totalTimes);               //得到总共调用次数
                float averageCost = totalCost / (float) totalTimes;
                server.setAverageCost(averageCost);         //得到平均调用时间
                Collections.sort(list);
                long maxCostTimes = (long) (totalTimes * 0.99);
                long total = 0l;
                long maxCost = 0l;
                for (int i = 0; i < list.size(); i++) {
                    total += smallMap.get(list.get(i));
                    if (total >= maxCostTimes) {
                        maxCost = list.get(i);              //(3)调用次数大于99%时候，花费的次数value，对应的key
                        server.setMaxCostTime(maxCost);     //得到最大调用时间
                        break;
                    }
            }
                result.add(server);
        }//for循环结束

        return result;
    } //outPut结束

    public void HandAndPut(Map<String, Map<Long, Long>> map){
        List<Server> list=DataOut(map);
        for (Server server : list) {
            System.out.println(server.toString());
        }
    }
}
