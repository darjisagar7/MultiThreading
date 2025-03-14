//https://leetcode.com/problems/print-foobar-alternately

package practice;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        SemaphorePractice sp = new SemaphorePractice(10);
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

import java.util.concurrent.Semaphore;

class SemaphorePractice {

    private final Semaphore fooSP;
    private final Semaphore barSP;
    private final int n;

    public SemaphorePractice(int n) {
        this.n = n;
        fooSP = new Semaphore(1);
        barSP = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i=0; i<n; i++) {
            fooSP.acquire();
            printFoo.run();
            barSP.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i=0; i<n; i++) {
            barSP.acquire();
            printBar.run();
            fooSP.release();
        }
    }
}

