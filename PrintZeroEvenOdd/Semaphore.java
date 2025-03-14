// https://leetcode.com/problems/print-zero-even-odd/

class ZeroEvenOdd {
    private int n;
    private Semaphore s0;
    private Semaphore seven;
    private Semaphore sodd;
    
    public ZeroEvenOdd(int n) {
        this.n = n;
        s0 = new Semaphore(1);
        seven = new Semaphore(0);
        sodd = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i=1; i<=n; i++) {
            s0.acquire();
            printNumber.accept(0);

            if (i % 2 == 0) {
                seven.release();
            } else {
                sodd.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i=2; i<=n; i+=2) {
            seven.acquire();
            printNumber.accept(i);
            s0.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i=1; i<=n; i+=2) {
            sodd.acquire();
            printNumber.accept(i);
            s0.release();
        }
    }
}
