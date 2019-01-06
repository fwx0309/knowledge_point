package demo1_singleton;

import java.util.concurrent.*;

public class Lazy_1_test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        Callable<Lazy_1> c = new Callable<Lazy_1>() {
            @Override
            public Lazy_1 call() throws Exception {
                return Lazy_1.getInstance();
            }
        };

        Future<Lazy_1> f1 = threadPool.submit(c);
        Future<Lazy_1> f2 = threadPool.submit(c);

        Lazy_1 s1 = f1.get();
        Lazy_1 s2 = f2.get();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);
    }
}
