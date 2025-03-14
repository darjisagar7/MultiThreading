// https://leetcode.com/problems/building-h2o

class H2O {
    private Semaphore hSemaphore;
    private Semaphore oSemaphore;

    public H2O() {
        hSemaphore = new Semaphore(2);
        oSemaphore = new Semaphore(1);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
		
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        hSemaphore.acquire();
        releaseHydrogen.run();
        oSemaphore.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
		oSemaphore.acquire(2);
        releaseOxygen.run();
        hSemaphore.release(2);
    }
}
