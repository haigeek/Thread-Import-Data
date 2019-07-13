import com.haigeek.Thread.Handler.BatchHandler;
import com.haigeek.Thread.Service.ImportService;

/**
 * @author zhaohj
 * @date 2019-07-13 23:26
 */
public class BatchImportTest {
    public static void main(String[] args) {
        // 总数
        long total=100000;
        // 休眠时间
        long sleepTime=100;
        // 每次拉取的数量
        int batch=100;
        // 线程个数
        int threadNum=16;
        ImportService importService=new ImportService(total,sleepTime);
        BatchHandler handler=new BatchHandler(importService,batch,threadNum);
        handler.startHandle();
    }
}
