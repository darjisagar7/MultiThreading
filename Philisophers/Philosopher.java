package practice.dinningphilosopher;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class Philosopher extends Thread {

    private final int id;
    private final Chopstick leftChopstick;
    private final Chopstick rightChopstick;

    @Override
    public void run() {
        try {
            while (true) {
                think();
                if (id == 4) {
                    if (rightChopstick.pickUp()) {
                        if (leftChopstick.pickUp()) {
                            eat();
                            leftChopstick.putDown();
                        }
                        rightChopstick.putDown();
                    }
                } else {
                    if (leftChopstick.pickUp()) {
                        if (rightChopstick.pickUp()) {
                            eat();
                            rightChopstick.putDown();
                        }
                        leftChopstick.putDown();
                    }
                }
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking...");
        Thread.sleep(10000);
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating.");
        Thread.sleep(10000);
    }
}
