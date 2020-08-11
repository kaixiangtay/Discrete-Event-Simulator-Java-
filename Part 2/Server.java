/**
 * Server class which contains the details of the server and 
 * managed the state of server whether 
 * The state of customer who are being served or waited to to be served
 * has been represented as event types of customerServed and customerWaited respectively.
 */

public class Server {
    private static int COUNT = 0;
    private int serverID;
    private Customer customerServed;
    private Customer customerWaited;

    /**
     * The default constructor to initialise a server 
     * where no customer to be served at the start.
     * and assign identification number to the server when created.
     */
    public Server() {
        COUNT++;
        this.customerServed = null;
        this.customerWaited = null;
        this.serverID = COUNT;
    }

    /**
     * Getter method to retrieve the identification number of the server.
     */
    public int getServerID() {
        return this.serverID;
    }

    /**
     * Getter method to retrieve the current customer served by the server.
     */
    public Customer getCustomerServed() {
        return this.customerServed;
    }

    /**
     * Updates the service state of the Server when there is a new Customer to be served.
     * @param newCustomer an incoming customer that will be served by thse server.
     */
    public void setCustomerServed(Customer newCustomer) {
        this.customerServed = newCustomer;
    }

    /**
     * Updates the service state of the Server when there is a new Customer waiting to be served.
     * @param newCustomer an incoming customer that can wait to be served by the server.
     */
    public void setCustomerWaited(Customer newCustomer) {
        this.customerWaited = newCustomer;
    }

    /**
     * Determines whether the customer can be served by the server.
     * @return true only if the server is currently not serving customer and no waiting customer.
     */
    public boolean canServe() {
        return customerServed == null && customerWaited == null;
    }

    /**
     * Determines whether the customer can wait to be served by the server.
     * @return true only if the server is currently serving a customer and no waiting customer.
     */
    public boolean canWait() { 
        return customerServed != null && customerWaited == null;
    }

    /**
     * Updates the service state of the Server after server just finished serving a customer.
     * This is done by resetting the event of customer being served by the customer to 
     * be the customer who has waited to be served by the server.
     * and setting the event of the customer waiting for the same server to be free.
     * This will allow another customer(if there is) to be able to wait for the server again.
     */
    public void updateServer() {
        this.customerServed = null;
        this.customerServed = customerWaited;
        this.customerWaited = null;
    }
}
