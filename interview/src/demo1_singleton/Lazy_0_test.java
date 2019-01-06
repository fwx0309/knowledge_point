package demo1_singleton;

import java.util.concurrent.*;

public class Lazy_0_test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        Callable<Lazy_0> c = new Callable<Lazy_0>() {
            @Override
            public Lazy_0 call() throws Exception {
                return Lazy_0.getInstance();
            }
        };

        Future<Lazy_0> f1 = threadPool.submit(c);
        Future<Lazy_0> f2 = threadPool.submit(c);

        Lazy_0 s1 = f1.get();
        Lazy_0 s2 = f2.get();


        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);
    }
}
