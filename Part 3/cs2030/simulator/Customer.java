package cs2030.simulator;

/**
 * Customer class which comprises the details of the customer.
 */
public class Customer {
    private final int customerID;
    private final double arrivalTime;
    private final String customerType;

    /**
     * The customer constructor that generates customer
     * with their ID and arrival time information.
     * @param arrivalTime Time which the customer arrives.
     * @param customerID Identification number of the customer.
     * @param customerType Type of the customer.
     */
    public Customer(double arrivalTime, int customerID, String customerType) {
        this.arrivalTime = arrivalTime;
        this.customerID = customerID;
        this.customerType = customerType;
    }

    /**
     * Getter method to retrieve the identification number of the customer.
     * @return identification number of the customer.
     */
    public int getCustomerID() {
        return this.customerID;
    }

    /**
     * Getter method to retrieve the arrival time of the customer.
     * @return arrival time of the customer.
     */
    public double getArrivalTime() {
        return this.arrivalTime;
    }

    /**
     * Getter method to retrieve the current type of the customer.
     * @return Type of the customer.
     */
    public String getCustomerType() {
        return this.customerType;
    }
}
