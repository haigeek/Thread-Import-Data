package com.haigeek.Thread;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaohj
 * @date 2019-07-13 23:00
 */
public class Import {

    public void threadImport(){
        List<Object> data=new ArrayList<Object>();
        //使用多线程进行存储
        Integer totalTimes = null;
        if (data.size()<10){
            totalTimes=1;
        }else {
            totalTimes=10;
        }
        // 每一次多少条数据
        int eachTimeNumber = data.size() / totalTimes;
        //余下的数据
        int restValues = data.size() % totalTimes;
        int times = 0;
    }



}
