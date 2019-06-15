import executor.MyExecutor;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        new MyExecutor().execute(() -> System.out.println("Hello Executor~"));
    }
}
