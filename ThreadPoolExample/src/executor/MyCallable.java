package executor;

import java.util.concurrent.Callable;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019-06-15 09:42
 */
public class MyCallable implements Callable {

    /**
     * Callable任务真正运行时，运行call，call()方法有返回值，可以进行计算返回
     * @throws Exception
     * */
    @Override
    public Object call() throws Exception {
        return null;
    }
}
