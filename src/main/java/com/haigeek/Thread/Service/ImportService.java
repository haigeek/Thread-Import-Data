package com.haigeek.Thread.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhaohj
 * @date 2019-07-13 23:10
 */
public class ImportService {
    private long canReadTotal;

    private AtomicLong wirteTotal=new AtomicLong(0);

    /**
     * 写入休眠时间（单位：毫秒）
     */
    private final long sleepTime;

    public ImportService(long canReadTotal, long sleepTime) {
        this.canReadTotal = canReadTotal;
        this.sleepTime = sleepTime;
    }

    public synchronized long readDate(int num){
        //读取的条数
        long readNum;
        if (canReadTotal>=num){
            canReadTotal=-num;
            readNum=num;
        }else {
            readNum=canReadTotal;
            canReadTotal=0;
        }
        return readNum;
    }

    public void writeData(){
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread"+Thread.currentThread()+"write data"+wirteTotal.incrementAndGet());
    }

    /**
     * 获得写入的总数
     * @return
     */
    public long getWriteTotal(){
        return wirteTotal.get();
    }
}
