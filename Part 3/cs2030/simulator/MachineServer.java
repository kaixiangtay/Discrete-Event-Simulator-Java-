package cs2030.simulator;

import java.util.ArrayList;

/**
 * MachineServer class which contains the details of the machine server
 * manages the combined waiting queue of all machine servers.
 * The customer being served by the server is updated
 * here from time to time.
 */
public class MachineServer extends Server {
    private static ArrayList<Customer> machineCustomerQueue = new ArrayList<>();

    /**
     * The constructor to creates a new Machine Server object
     * where no customer to be served at the start.
     * and assign identification number to the server when created.
     * @param serverID identification number of server.
     * @param serverType "Machine" type of server.
     */
    MachineServer(int serverID, String serverType) {
        super(serverID, serverType);
    }

    /**
     * Getter method to obtain current waiting queue capacity.
     * @return the current waiting queue capacity.
     */
    @Override
    public int getQueueSize() {
        return this.machineCustomerQueue.size();
    }

    /**
     * Method to add customer to shared Machine Server waiting queue.
     * @param customer current customer to add to waiting queue.
     */
    @Override
    public void addCustomer(Customer customer) {
        this.machineCustomerQueue.add(customer);
    }

    /**
     * Method to remove customer from shared Machine Server waiting queue.
     * @param customer current customer to remove from waiting queue.
     */
    @Override
    public void removeCustomer(Customer customer) {
        this.machineCustomerQueue.remove(customer);
    }

    /**
     * Method to check whether customer is in queue.
     * @param customer current customer to be checked.
     * @return true if customer is in queue, false otherwise.
     */
    @Override
    public boolean customerWaiting(Customer customer) {
        return this.machineCustomerQueue.contains(customer);
    }

    /**
     * Getter method to retrieve the next customer from queue.
     * @return the first customer from the waiting queue.
     */
    @Override
    public Customer getNextCustomer() {
        Customer nextCustomer = this.machineCustomerQueue.get(0);
        return nextCustomer;
    }

    /**
     * Determines whether the customer can be served by the server.
     * @return true only if the server is currently not serving customer.
     *
     */
    @Override
    public boolean canServe() {
        return super.customerServed == null;
    }
}
