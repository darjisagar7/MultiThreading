package practice.ride;

class UberSystem {

    public static void main(String[] args) {

        RideQueue rideQueue = new RideQueue(5);

        //Create driver
        Driver driver1 = new Driver("Driver 1", rideQueue);
        Driver driver2 = new Driver("Driver 2", rideQueue);

        Thread driverThread1 = new Thread(driver1);
        Thread driverThread2 = new Thread(driver2);
        driverThread1.start();
        driverThread2.start();

        //Create Customers
        Customer customer1 = new Customer("Customer 1", rideQueue);
        Customer customer2 = new Customer("Customer 2", rideQueue);
        Customer customer3 = new Customer("Customer 3", rideQueue);
        Customer customer4 = new Customer("Customer 4", rideQueue);
        Customer customer5 = new Customer("Customer 5", rideQueue);

        new Thread(customer1).start();
        new Thread(customer2).start();
        new Thread(customer3).start();
        new Thread(customer4).start();
        new Thread(customer5).start();
    }
}
