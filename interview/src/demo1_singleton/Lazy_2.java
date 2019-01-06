package demo1_singleton;

/**
 * 懒汉式线程不安全
 */
public class Lazy_2 {
    private Lazy_2(){}

    private static final class Inner{
        public static final Lazy_2 INSTANCE = new Lazy_2();
    }

    public static Lazy_2 getInstance(){
        return Inner.INSTANCE;
    }
}
