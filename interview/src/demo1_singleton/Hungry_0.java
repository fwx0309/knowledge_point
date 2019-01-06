package demo1_singleton;

/**
 *  饿汉式：在类初始化的时候直接创建对象，不管你是否需要这个对象
 */
public class Hungry_0 {
    public static final Hungry_0 INSTANSE = new Hungry_0();

    //构造器私有化
    private Hungry_0() {
        System.out.println("Singleton");
    }
}
