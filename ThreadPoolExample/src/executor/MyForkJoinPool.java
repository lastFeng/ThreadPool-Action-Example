package executor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019-06-15 11:38
 */
public class MyForkJoinPool {
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++){
            nums[i] = r.nextInt(100);
        }
        System.out.println(Arrays.stream(nums).sum());
    }

    /**
     * 进行递归操作：没有返回值
     * */
    static class AddTask extends RecursiveAction{
        int start, end;
        AddTask(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        protected void compute() {
            // 不超过范围，直接计算
            if (end - start <= MAX_NUM){
                long sum = 0L;
                for (int i = start; i < end; i++){
                    sum += nums[i];
                }
                System.out.println("from: " + start + " to: " + end + " = " + sum);
            }
            // 超过范围，拆分
            else {
                int middle = start + (end - start) / 2;
                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                subTask1.fork();
                subTask2.fork();
            }
        }
    }

    /**
     * RecursiveTask有返回值
     * */
    static class Task extends RecursiveTask<Long>{
        int start, end;

        Task(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0L;

            if (end - start <= MAX_NUM){
                for (int i = start; i < end; i++){
                    sum += nums[i];
                }
            }
            else {
                int middle = start + (end -start) / 2;
                Task task1 = new Task(start, middle);
                Task task2 = new Task(middle, end);
                task1.fork();
                task2.fork();
                sum += task1.join() + task2.join();
            }

            return sum;
        }
    }

    public static void main(String[] args) throws IOException {
        ForkJoinPool pool = new ForkJoinPool();
        AddTask task = new AddTask(0, nums.length);
        pool.execute(task);
        // 进行阻塞
//        System.in.read();


        Task task1 = new Task(0, nums.length);
        pool.execute(task1);
        long sum = task1.join();
        System.out.println("sum: " + sum);

        pool.shutdown();
    }

}
