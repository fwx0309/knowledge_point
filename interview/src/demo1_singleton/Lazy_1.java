package demo1_singleton;

/**
 * 懒汉式线程不安全
 */
public class Lazy_1 {
    private static Lazy_1 instance;

    private Lazy_1(){}

    public static Lazy_1 getInstance(){

        if(instance==null){
            synchronized (Lazy_1.class){
                if (instance==null) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance= new Lazy_1();
                }
            }
        }

        return instance;
    }
}
