package chenyuan.leetcode.s1115;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 使用SynchronousQueue实现
 * @author chenyuan
 */
public class FooBar04 {

    private static final SynchronousQueue<Integer> SQ = new SynchronousQueue<>();
    private static volatile boolean FLAG;

    private int n;

    public FooBar04(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        long c = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            while (FLAG);
            printFoo.run();
            FLAG = true;
            SQ.put(0);
        }

        System.out.println("FOO cost: " + (System.currentTimeMillis() - c));
    }

    public void bar(Runnable printBar) throws InterruptedException {
        long c = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            SQ.take();
            printBar.run();
            FLAG = false;
        }

        System.out.println("BAR cost: " + (System.currentTimeMillis() - c));
    }

    public static void main(String[] args) throws Exception {

        FooBar04 fooBar = new FooBar04(200);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(() ->  {
            try {
                fooBar.foo(() -> System.out.println("foo"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executor.execute(() ->  {
            try {
                fooBar.bar(() -> System.out.println("bar"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }
}
