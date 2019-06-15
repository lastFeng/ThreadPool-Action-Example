package executor;

import java.util.concurrent.*;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019-06-15 10:50
 */
public class MyCacheThreadPool {
    public static void main(String[] args) throws InterruptedException {

        /**import commons.lang3*/
        /***
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(5, 20, 2L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
         **/


        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);

        for (int i = 0; i < 2; i++){
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);
        TimeUnit.SECONDS.sleep(80);
        System.out.println(service);

        service.shutdown();
    }
}
