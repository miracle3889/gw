package oracle_arena;

import bean.Apple;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xudabiao on 2017/3/1.
 */
@ComponentScan
public class Debug {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spr.xml");
        Apple apple = ac.getBean(bean.Apple.class);
        System.out.println(apple);
    }
}
