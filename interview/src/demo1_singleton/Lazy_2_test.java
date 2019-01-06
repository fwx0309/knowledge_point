package demo1_singleton;

import java.util.concurrent.*;

public class Lazy_2_test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        Callable<Lazy_2> c = new Callable<Lazy_2>() {
            @Override
            public Lazy_2 call() throws Exception {
                return Lazy_2.getInstance();
            }
        };

        Future<Lazy_2> f1 = threadPool.submit(c);
        Future<Lazy_2> f2 = threadPool.submit(c);

        Lazy_2 s1 = f1.get();
        Lazy_2 s2 = f2.get();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);
    }
}
