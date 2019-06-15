package executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019-06-15 12:12
 */
public class ParallelStreamApi {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 1000000; i++){
            nums.add(1000000 + r.nextInt(1000000));
        }

        long start =  System.currentTimeMillis();
        nums.forEach(v -> isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println(end-start + "ms");

        // 使用ParallelStream
        start = System.currentTimeMillis();
        nums.parallelStream().forEach(ParallelStreamApi::isPrime);
        end = System.currentTimeMillis();
        System.out.println(end-start + "ms");
    }

    static boolean isPrime(int num){
        for (int i = 2; i < num /2; i++){
            if (num % i == 0){
                return false;
            }
        }
        return true;
    }
}
