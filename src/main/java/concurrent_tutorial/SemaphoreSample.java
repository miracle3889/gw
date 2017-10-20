package concurrent_tutorial;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by xudabiao on 2017/3/8.
 */
public class SemaphoreSample {
    private static final int MAX_PERMIT = 100;

    private static String template = "Thread[%s]:before release we have %s permit,release %s permit,now we have %s";

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(MAX_PERMIT,true);
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            es.execute(()->{
                while (true){
                    try {
                        int permitsNeed = (int)(Math.random()*MAX_PERMIT/2);
                        semaphore.acquire(permitsNeed);
                        System.out.println(String.format("need %s permits and stay in %s seconds",permitsNeed,permitsNeed ));
                        TimeUnit.SECONDS.sleep(permitsNeed/10+1);
                        int before = semaphore.availablePermits();
                        semaphore.release(permitsNeed);
                        int after = semaphore.availablePermits();
                        System.out.println(semaphore.getQueueLength());
                        System.out.println(String.format(template, Thread.currentThread(),before,permitsNeed,after));
                        Thread.yield();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
