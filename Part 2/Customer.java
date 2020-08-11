/**
 * Customer class which comprises the details of the customer.
 */

public class Customer {
    private final int customerID;
    private final double arrivalTime;
    private double updatedTime;

    /**
     * The customer overload constructor that generates customer's information
     * regarding their ID, arrival time.
     * @param arrivalTime Time which the customer arrives.
     * @param customerID Identification number of the customer.
     */
    public Customer(double arrivalTime, int customerID) {
        this.arrivalTime = arrivalTime; 
        this.customerID = customerID;
    }

    /**
     * Getter method to retrieve the identification number of the customer.
     */
    public int getCustomerID() {
        return this.customerID;
    }

    /**
     * Getter method to retrieve the arrival time of the customer.
     */
    public double getArrivalTime() {
        return this.arrivalTime;
    }

    /**
     * Getter method to get time information of the customer after change of event. 
     * @return Time information of the customer.
     */
    public double getUpdatedTime() {
        return this.updatedTime;
    }

    /**
     * Setter method to update customer time details in the event of state change.
     * @param latestTime new timing of customer to be updated accordingly after change of event.
     */
    public void setUpdatedTime(double latestTime) {
        this.updatedTime = latestTime;
    }
}
