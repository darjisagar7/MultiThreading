// https://leetcode.com/problems/print-in-order

package practice;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ConditionVariable sp = new ConditionVariable();
        ExecutorService executorService = newFixedThreadPool(3);

        executorService.submit(() -> {
            try {
                sp.first(() -> System.out.println("first"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                sp.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                sp.second(() -> System.out.println("second"));
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
    private boolean oneDone;
    private boolean twoDone;
    private final Condition one;
    private final Condition two;

    public ConditionVariable() {
        lock = new ReentrantLock();
        oneDone = false;
        twoDone = false;
        one = lock.newCondition();
        two = lock.newCondition();
    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        try {
            printFirst.run();
            oneDone = true;
            Thread.sleep(10000);
            one.signal();
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        try {
            // The while loop (also known as a "spin lock" pattern) is necessary for handling spurious wakeups - 
            // a situation where a thread might wake up from waiting even though no thread signaled it to wake up. 
            // This is why the condition check is needed in a loop rather than using a simple wait/await.
            while (!oneDone) {
                System.out.println("second method - oneDone - " + oneDone);
                one.await();
            }
            printSecond.run();
            twoDone = true;
            two.signal();
        } finally {
            lock.unlock();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        try {
            while (!twoDone) {
                two.await();
            }
            printThird.run();
        } finally {
            lock.unlock();
        }
    }
}
