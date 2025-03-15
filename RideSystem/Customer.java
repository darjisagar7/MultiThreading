package practice.ride;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class Customer implements Runnable {
    private final String customerName;
    private final RideQueue rideQueue;

    @Override
    public void run() {
        try {
            System.out.println(customerName + " is requesting a ride.");
            rideQueue.requestRide(new RideRequest(customerName));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
