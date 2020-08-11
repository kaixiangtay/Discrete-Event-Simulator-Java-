package cs2030.simulator;

/**
 * GreedyCustomer class that comprises
 * the details of the Greedy customer.
 */
public class GreedyCustomer extends Customer {
    /**
     * The greedy customer constructor that generates greedy customer
     * object type with their ID and arrival time information.
     * @param arrivalTime Time which the customer arrives.
     * @param customerID Identification number of the customer.
     * @param customerType "Greedy" type of customer.
     */
    GreedyCustomer(double arrivalTime, int customerID, String customerType) {
        super(arrivalTime, customerID, customerType);
    }
}
