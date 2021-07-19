package chenyuan.leetcode.s1115;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <a href="https://leetcode-cn.com/problems/print-foobar-alternately/">LeetCode题目连接</a>
 * @author chenyuan
 */
public class FooBar02 {

    private final static AtomicInteger COUNT = new AtomicInteger(0);
    private final int n;

    public FooBar02(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) {
        long c = System.currentTimeMillis();

        int count = 0;
        while (count < n) {
            if (COUNT.get() % 2 == 0) {
                printFoo.run();
                COUNT.incrementAndGet();
                count ++;
            }
        }

        System.out.println("foo cost: " + (System.currentTimeMillis() - c));
    }

    public void bar(Runnable printBar) {
        long c = System.currentTimeMillis();
        int count = 0;
        while (count < n) {
            if (COUNT.get() % 2 != 0) {
                printBar.run();
                COUNT.incrementAndGet();
                count ++;
            }
        }
        System.out.println("bar cost: " + (System.currentTimeMillis() - c));
    }

    public static void main(String[] args) throws Exception {
        FooBar02 fooBar = new FooBar02(200);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(() -> fooBar.foo(() -> System.out.println("foo")));
        executor.execute(() -> fooBar.bar(() -> System.out.println("bar")));

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }
}
