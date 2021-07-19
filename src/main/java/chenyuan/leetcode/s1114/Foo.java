package chenyuan.leetcode.s1114;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  <a href="https://leetcode-cn.com/problems/print-in-order/">LeetCode题目连接</a>
 */
public class Foo {

    private AtomicInteger count = new AtomicInteger(3);
    private Lock lock = new ReentrantLock(true);

    public Foo() {

    }

    public void first(Runnable printFirst) {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        count.decrementAndGet();
    }

    public void second(Runnable printSecond) {
        for (;;) {
            if (lock.tryLock()) {
                try {
                    if (count.get() == 2) {
                        // printSecond.run() outputs "second". Do not change or remove this line.
                        printSecond.run();
                        count.decrementAndGet();
                        break;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public void third(Runnable printThird) {
        for (;;) {
            if (lock.tryLock()) {
                try {
                    if (count.get() == 1) {
                        // printThird.run() outputs "third". Do not change or remove this line.
                        printThird.run();
                        count.getAndDecrement();
                        break;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }

    }


    public static void main(String[] args) throws Exception  {
        final Foo foo = new Foo();
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(() -> foo.second(() -> System.out.println("second")));
        executor.execute(() -> foo.first(() -> System.out.println("first")));
        executor.execute(() -> foo.third(() -> System.out.println("third")));

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }
}
