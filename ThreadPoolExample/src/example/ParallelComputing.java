package example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019-06-15 10:30
 *
 * 并行计算
 * 利用Future的Callable接口来进行并行计算，计算1~200000中的质数的计算时间
 */
public class ParallelComputing {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Integer> results = getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");

        System.out.println(results);
        final int cpuCoreNum = 4;

        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);


        /**
         * 不均分是因为越到后面，质数的计算需要的时间越长，所以不能进行均分
         * */
        MyTask t1 = new MyTask(1, 80000);
        MyTask t2 = new MyTask(80001, 130000);
        MyTask t3 = new MyTask(130001, 170000);
        MyTask t4 = new MyTask(170001, 200000);

        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);
        results.clear();

        start = System.currentTimeMillis();
        results.addAll(f1.get());
        results.addAll(f2.get());
        results.addAll(f3.get());
        results.addAll(f4.get());

        end = System.currentTimeMillis();
        System.out.println((end-start)+ "ms");

        System.out.println(results);
        service.shutdown();
    }

    static class MyTask implements Callable<List<Integer>>{
        int start;
        int end;

        public MyTask(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public List<Integer> call() throws Exception {
            return getPrime(start, end);
        }
    }

    private static List<Integer> getPrime(int start, int end) {
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++){
            if (isPrime(i)){
                list.add(i);
            }
        }
        return list;
    }

    private static boolean isPrime(int num){
        for (int i = 2; i < num/2; i++){
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
