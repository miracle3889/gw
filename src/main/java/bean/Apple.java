package bean;

import org.springframework.beans.factory.DisposableBean;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by xudabiao on 2017/3/1.
 */
public class Apple implements DisposableBean{
    public synchronized void tasteWhat(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Apple() {
//        System.out.println("apple");
    }
    private static String stringCompare(String a,String b){
        int i = 0;
        while (i<a.length()&&i<b.length()){
            if (a.charAt(i)>b.charAt(i))
                return a;
            else if (a.charAt(i)<b.charAt(i))
                return b;
            i++;
        }
        return a.length()>b.length()?a:b;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> list = new LinkedList<>();
        Thread t = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.getClass());
        t.start();
        list.add(t);
        for (int i = 0; i < 9; i++) {
            Thread g = new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            list.add(g);
            g.start();
        }
        for (Thread thread : list) {
            thread.join();
            System.out.println(thread.getClass()+"=>die");
        }

    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Yes,indeed");
    }
}
class wcb{
    public static void main(String[] args) {
        Thread g = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }}
        );
        g.start();
        synchronized (g){
            try {
                g.join();
                System.out.println(g.isAlive());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}
