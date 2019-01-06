package demo1_singleton;

/**
 * 懒汉式线程不安全
 */
public class Lazy_0 {
    private static Lazy_0 instance;

    private Lazy_0(){}

    public static Lazy_0 getInstance(){

        if (instance==null) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance= new Lazy_0();
        }
        return instance;
    }
}
