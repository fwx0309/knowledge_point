package demo1_singleton;

/**
 *  饿汉式：在类初始化的时候直接创建对象，不管你是否需要这个对象
 */
public class Hungry_1 {
    public static final Hungry_1 INSTANSE = new Hungry_1();

    //构造器私有化
    private Hungry_1() {
        System.out.println("Singleton");
    }
}
