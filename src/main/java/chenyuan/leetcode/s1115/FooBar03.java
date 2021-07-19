package chenyuan.leetcode.s1115;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  <a href="https://leetcode-cn.com/problems/print-foobar-alternately/">LeetCode题目连接</a>
 * @author chenyuan
 */
public class FooBar03 {

    private static volatile boolean printedFoo;
    private final static Semaphore S = new Semaphore(1);
    private final int n;

    public FooBar03(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        long c = System.currentTimeMillis();

        int count = 0;
        while (count < n) {
            if (printedFoo) { continue; }
            S.acquire();
            try {
                printFoo.run();
                printedFoo = true;
                count ++;
            } finally {
                S.release();
            }
        }

        System.out.println("foo cost: " + (System.currentTimeMillis() - c));
    }

    public void bar(Runnable printBar) throws InterruptedException {
        long c = System.currentTimeMillis();
        int count = 0;
        while (count < n) {
            if (!printedFoo) {continue;}
            S.acquire();
            try {
                printBar.run();
                printedFoo = false;
                count ++;
            } finally {
                S.release();
            }
        }
        System.out.println("bar cost: " + (System.currentTimeMillis() - c));
    }


    public static void main(String[] args) throws Exception {
        FooBar03 fooBar = new FooBar03(200);

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
