package practice.ride;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class Driver implements Runnable {

    private final String driverName;
    private final RideQueue rideQueue;

    @Override
    public void run() {
        try {
            while (true) {
                RideRequest rideRequest = rideQueue.getNextRideRequest();
                if (rideRequest != null) {
                    System.out.println(driverName + " is accepting a ride for " + rideRequest.getCustomerName());
                    completeRide();
                    System.out.println(driverName + " has completed the ride for " + rideRequest.getCustomerName());

                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void completeRide() throws InterruptedException {
        Thread.sleep(5000);
    }
}
