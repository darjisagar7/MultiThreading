package practice.ride;

import java.util.Queue;
import java.util.LinkedList;

class RideQueue {

    private final Queue<RideRequest> rideRequests;
    private final int maxCapacity;

    public RideQueue(int maxCapacity) {
        this.rideRequests = new LinkedList<>();
        this.maxCapacity = maxCapacity;
    }

    public synchronized void requestRide(final RideRequest rideRequest) throws InterruptedException {
        while (rideRequests.size() == maxCapacity) {
            wait();
        }

        rideRequests.add(rideRequest);
        System.out.println(rideRequest.getCustomerName() + " has requested a ride.");
        notifyAll();
    }

    public synchronized RideRequest getNextRideRequest() throws InterruptedException {

        while(rideRequests.isEmpty()) {
            wait();
        }

        RideRequest rideRequest = rideRequests.poll();
        notifyAll();
        return rideRequest;
    }
}
