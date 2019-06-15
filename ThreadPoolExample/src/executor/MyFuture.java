package executor;

import java.util.concurrent.*;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019-06-15 10:08
 */
public class MyFuture{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(5000);
            return 1000;
        });

        new Thread(task).start();

        // 阻塞
        System.out.println(task.get());

        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> futureTask = service.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });

//        System.out.println(futureTask.get());
        System.out.println(futureTask.isDone());
        System.out.println(futureTask.get());
        System.out.println(futureTask.isDone());

        service.shutdown();
    }
}
