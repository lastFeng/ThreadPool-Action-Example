package executor;

import java.util.concurrent.Executor;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019-06-15 09:34
 *
 * 实现Executor接口，重写execute方法，来执行
 */
public class MyExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
