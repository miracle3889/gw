package bean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Created by xudabiao on 2017/3/1.
 */
public abstract class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("spr.xml");
        System.out.println(cp.getBean(Car.class));
    }
}
