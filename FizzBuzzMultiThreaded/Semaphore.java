// https://leetcode.com/problems/fizz-buzz-multithreaded

class FizzBuzz {
    private int currNum;
    private Semaphore fizzSem;
    private Semaphore buzzSem;
    private Semaphore fizzBuzzSem;
    private Semaphore numberSem;
    private int n;

    public FizzBuzz(int n) {
        this.n = n;
        currNum = 1;
        fizzSem = new Semaphore(0);
        buzzSem = new Semaphore(0);
        fizzBuzzSem = new Semaphore(0);
        numberSem = new Semaphore(1);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (currNum <= n) {
            fizzSem.acquire();
            if (currNum <= n) {
                printFizz.run();
            }
            release();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (currNum <= n) {
            buzzSem.acquire();
            if (currNum <= n) {
                printBuzz.run();
            }
            release();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (currNum <= n) {
            fizzBuzzSem.acquire();
            if (currNum <= n) {
                printFizzBuzz.run();
            }
            release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (currNum <= n) {
            numberSem.acquire();
            if (currNum <= n) {
                printNumber.accept(currNum);
            }
            release();
        }
    }

        private void release() {
        currNum++;
        if (currNum <= n) {
            if (currNum % 3 == 0 && currNum % 5 == 0) {
                fizzBuzzSem.release();
            } else if (currNum % 3 == 0) {
                fizzSem.release();
            } else if (currNum % 5 == 0) {
                buzzSem.release();
            } else {
                numberSem.release();
            }
        } else {
            fizzSem.release();
            buzzSem.release();
            fizzBuzzSem.release();
            numberSem.release();
        }
    }
}
