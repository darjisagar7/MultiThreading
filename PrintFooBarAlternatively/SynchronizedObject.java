// https://leetcode.com/problems/print-foobar-alternately/

package practice;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Synchronized sp = new Synchronized(10);
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

public class Synchronized {
    private int n;
    // 0 = Foo
    // 1 = Bar
    private int flag = 0;

    public Synchronized(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (flag == 1) {
                    wait();
                }
                printFoo.run();
                Thread.sleep(1000);
                flag = 1;
                notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (flag == 0) {
                    wait();
                }
                printBar.run();
                Thread.sleep(1000);
                flag = 0;
                notify();
            }
        }
    }
}
