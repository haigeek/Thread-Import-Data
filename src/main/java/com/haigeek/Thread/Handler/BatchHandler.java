package com.haigeek.Thread.Handler;

import com.haigeek.Thread.Service.ImportService;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaohj
 * @date 2019-07-13 23:21
 */
public class BatchHandler {
    private ExecutorService executorService;
    private ImportService importService;

    private int threadNum;

    private int batch;

    public BatchHandler(ImportService importService, int threadNum, int batch) {
        this.importService = importService;
        this.executorService= Executors.newFixedThreadPool(threadNum);
        this.batch = batch;
    }

    /**
     * 开始处理
     */
    public void startHandle() {
        // 开始处理的时间
        long startTime = System.currentTimeMillis();
        System.out.println("start handle time:" + startTime);
        long readData;
        while ((readData = importService.readDate(batch)) != 0) {// 批量读取数据，知道读取不到数据才停止
            for (long i = 0; i < readData; i++) {
                executorService.execute(() -> importService.writeData());
            }
        }
        // 关闭线程池
        executorService.shutdown();
        while (!executorService.isTerminated()) {//等待线程池中的线程执行完

        }
        // 结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("end handle time:" + endTime);
        // 总耗时
        System.out.println("total handle time:" + (endTime - startTime) + "ms");
        // 写入总数
        System.out.println("total write num:" + importService.getWriteTotal());
    }


}
