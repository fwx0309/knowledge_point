package demo1_singleton;

import java.io.IOException;
import java.util.Properties;

/**
 *  饿汉式：在类初始化的时候直接创建对象，不管你是否需要这个对象
 */
public class Hungry_2 {
    public static final Hungry_2 INSTANSE;
    private String info;

    static {
        Properties prop = new Properties();
        try {
            prop.load(Hungry_2.class.getClassLoader().getResourceAsStream("info.properties"));
            INSTANSE = new Hungry_2((String) prop.get("info"));
        } catch (IOException e) {
            throw new RuntimeException("初始化失败！");
        }
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private Hungry_2(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Hungry_2{" +
                "info='" + info + '\'' +
                '}';
    }

}
