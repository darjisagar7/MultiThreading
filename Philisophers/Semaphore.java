
// https://leetcode.com/problems/the-dining-philosophers/

class DiningPhilosophers {
    private Lock leftForkLock;
    private Lock rightForkLock;

    public DiningPhilosophers() {
        leftForkLock = new ReentrantLock();
        rightForkLock = new ReentrantLock();
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        
        while (true) {
            if (leftForkLock.tryLock(100, TimeUnit.MILLISECONDS)) {
                try {
                    pickLeftFork.run();
                    if (rightForkLock.tryLock(100, TimeUnit.MILLISECONDS)) {
                        try {
                            pickRightFork.run();
                            eat.run();
                            putRightFork.run();

                            return;
                        } finally {
                            rightForkLock.unlock();
                        }
                    }
                } finally {
                    putLeftFork.run();
                    leftForkLock.unlock();
                }
            }
        }
    }
}
