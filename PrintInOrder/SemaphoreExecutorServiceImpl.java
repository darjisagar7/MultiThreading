package practice;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        SemaphorePractice sp = new SemaphorePractice();
        ExecutorService executorService = newFixedThreadPool(3);

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
        executorService.submit(() -> {
            try {
                sp.first(() -> System.out.println("first"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.shutdown();
    }
}


package practice;

import lombok.AllArgsConstructor;

import java.util.concurrent.Semaphore;

class SemaphorePractice {

    private final Semaphore s2;
    private final Semaphore s3;

    public SemaphorePractice() {
        s2 = new Semaphore(0);
        s3 = new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        System.out.println("first method - Semaphore 2 value - " + s2.availablePermits());
        Thread.sleep(10000);
        s2.release();
        System.out.println("first method - Semaphore 2 value - " + s2.availablePermits());
    }

    public void second(Runnable printSecond) throws InterruptedException {
        System.out.println("second method - Semaphore 2 value - " + s2.availablePermits());
        s2.acquire();
        System.out.println("second method - Semaphore 2 value - " + s2.availablePermits());
        printSecond.run();
        System.out.println("second method - Semaphore 3 value - " + s3.availablePermits());
        s3.release();
        System.out.println("second method - Semaphore 3 value - " + s3.availablePermits());
    }

    public void third(Runnable printThird) throws InterruptedException {
        System.out.println("third method - Semaphore 3 value - " + s3.availablePermits());
        s3.acquire();
        System.out.println("third method - Semaphore 3 value - " + s3.availablePermits());
        printThird.run();
    }
}
