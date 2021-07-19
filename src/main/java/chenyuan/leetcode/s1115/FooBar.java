package chenyuan.leetcode.s1115;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *  <a href="https://leetcode-cn.com/problems/print-foobar-alternately/">LeetCode题目连接</a>
 * @author chenyuan
 */
public class FooBar {

    private volatile boolean fooPrinted;
    private static final Object lock = new Object();

    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        long c = System.currentTimeMillis();

        int count = 0;
        while (count < n) {
            synchronized (lock) {
                printFoo.run();
                fooPrinted = true;
                lock.wait();
                count ++;
            }
        }

        System.out.println("foo cost: " + (System.currentTimeMillis() - c));
    }

    public void bar(Runnable printBar) {
        long c = System.currentTimeMillis();

        int count = 0;
        while (count < n) {
            if (!fooPrinted) {
                continue;
            }
            synchronized (lock) {
                printBar.run();
                lock.notify();
                count ++;
                fooPrinted = false;
            }
        }

        System.out.println("bar cost: " + (System.currentTimeMillis() - c));
    }

    public static void main(String[] args) throws Exception {
        FooBar fooBar = new FooBar(200);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(() ->  {
            try {
                fooBar.foo(() -> System.out.println("foo"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> fooBar.bar(() -> System.out.println("bar")));

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }
}
