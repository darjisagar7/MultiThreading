package practice.dinningphilosopher;

public class DinningPhilosophers {
    public static void main(String[] args) {
        final int NUMBER_OF_PHILOSOPHERS = 5;

        Chopstick[] chopsticks = new Chopstick[NUMBER_OF_PHILOSOPHERS];
        Philosopher[] philosophers = new Philosopher[NUMBER_OF_PHILOSOPHERS];

        for (int i=0; i<NUMBER_OF_PHILOSOPHERS; i++) {
            chopsticks[i] = new Chopstick();
        }

        for (int i=0; i<NUMBER_OF_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i+1)%NUMBER_OF_PHILOSOPHERS]);
        }

        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }

        for (Philosopher philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
