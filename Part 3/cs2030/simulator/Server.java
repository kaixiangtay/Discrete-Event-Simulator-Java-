package cs2030.simulator;

import java.util.ArrayList;

/**
 * Server class which contains the details of the server and 
 * manages the waiting queue of the server.
 * The customer being served by the server is updated here
 * from time to time.
 */
public class Server {
    protected final int serverID;
    protected final String serverType;
    protected Customer customerServed;
    private ArrayList<Customer> waitingCustomerQueue;
    protected double time;
    protected boolean canRest;

    /**
     * The default constructor to creates a new Server object
     * where no customer to be served at the start.
     * and assign identification number to the server when created.
     * @param serverID identification number of server.
     * @param serverType server type in String form.
     */
    public Server(int serverID, String serverType) {
        this.serverID = serverID;
        this.customerServed = null;
        this.waitingCustomerQueue = new ArrayList<Customer>();
        this.time = 0;
        this.canRest = false;
        this.serverType = serverType;
    }

    /**
     * Getter method to retrieve the identification number of the server.
     * @return the identification number of the server.
     */
    public int getServerID() {
        return this.serverID;
    }

    /**
     * Getter method to retrieve the type of the server.
     * @return type of server in String type.
     */
    public String getServerType() {
        return this.serverType;
    }

    /**
     * Getter method to retrieve current time.
     * @return current time of the server.
     */
    public double getTime() {
        return this.time;
    }

    /**
     * Setter method to update Server time.
     * @param time current time to update for Server.
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * Updates the service state of the Server when there is a new Customer to be served.
     * @param newCustomer an incoming customer that will be served by the server.
     */
    public void setCustomerServed(Customer newCustomer) {
        this.customerServed = newCustomer;
    }

    /**
     * Setter method to assign the status of the server
     * whether the server is going to rest.
     * @param canRest boolean of whether the server can rest.
     */
    public void setRest(boolean canRest) {
        this.canRest = canRest;
    }

    /**
     * Getter method to obtain current waiting queue capacity.
     * @return the current waiting queue capacity.
     */
    public int getQueueSize() {
        return this.waitingCustomerQueue.size();
    }

    /**
     * Method to add customer to Server waiting queue.
     * @param customer current customer to add to waiting queue.
     */
    public void addCustomer(Customer customer) {
        this.waitingCustomerQueue.add(customer);
    }

    /**
     * Method to remove customer from Server waiting queue.
     * @param customer current customer to remove from queue.
     */
    public void removeCustomer(Customer customer) {
        this.waitingCustomerQueue.remove(customer);
    }

    /**
     * Method to check whether customer is in queue.
     * @param customer current customer to be checked.
     * @return true if customer is in queue, false otherwise.
     */
    public boolean customerWaiting(Customer customer) {
        return this.waitingCustomerQueue.contains(customer);
    }

    /**
     * Getter method to retrieve the next customer from queue.
     * @return the first customer from the waiting queue.
     */
    public Customer getNextCustomer() {
        Customer nextCustomer = this.waitingCustomerQueue.get(0);
        return nextCustomer;
    }

    /**
     * Determines whether the customer can be served by the server.
     * @return true only if the server is currently not serving customer, not resting
     *         and the waiting queue is empty.
     */
    public boolean canServe() {
        return !this.canRest && this.customerServed == null && this.waitingCustomerQueue.isEmpty();
    }

    /**
     * Updates the service state of the Server after server just finished serving a customer.
     * This is done by setting the server to be without any customer on hand to be served.
     * This will allow another customer(if there is) to be able to wait for the server again.
     */
    public void updateServer() {
        this.customerServed = null;
    }
}
