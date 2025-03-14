//https://leetcode.com/problems/print-foobar-alternately

package practice;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ConditionVariable sp = new ConditionVariable(10);
        ExecutorService executorService = newFixedThreadPool(3);

        executorService.submit(() -> {
            try {
                sp.foo(() -> System.out.println("Foo"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.submit(() -> {
            try {
                sp.bar(() -> System.out.println("Bar"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.shutdown();
    }
}


package practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ConditionVariable {

    private final Lock lock;
    // 0 = foo
    // 1 = bar
    private volatile int flag;
    private final Condition fooNotPrinted;
    private final Condition barNotPrinted;
    private int n;

    public ConditionVariable(int n) {
        this.n = n;
        lock = new ReentrantLock();
        flag = 0;
        fooNotPrinted = lock.newCondition();
        barNotPrinted = lock.newCondition();
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            try {
                lock.lock();
                while (flag != 0) {
                    fooNotPrinted.await();
                }
                printFoo.run();
                flag = 1;
                barNotPrinted.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            try {
                lock.lock();
                while (flag != 1) {
                    barNotPrinted.await();
                }
                printBar.run();
                flag = 0;
                fooNotPrinted.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}
