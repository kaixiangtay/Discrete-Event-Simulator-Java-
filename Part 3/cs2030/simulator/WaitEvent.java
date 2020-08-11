package cs2030.simulator;

/**
 * WaitEvent class which handles the event where there is 
 * a customer waiting to be served by the server.
 */
public class WaitEvent extends Event {
    /** 
     * The constructor which creates a wait Event.
     * @param currentCustomer Customer who is part of the event.
     * @param eventTime Time of event.
     * @param state State of event.
     * @param server Server that takes care of the wait Event.
     */
    public WaitEvent(Customer currentCustomer, double eventTime, State state, Server server) {
        super(currentCustomer, eventTime, state, server);
    }

    /**
     * Overrides the toString() method in String class.
     * @return waiting event details of current time, current customer served,
     *         and current server to wait for will be returned in format of string.
     */
    @Override
    public String toString() {
        String serverType = "";
        String customerType = "";
        if (this.getServer().getServerType() == ("Machine")) {
            serverType = "self-check";
        } else {
            serverType = "server";
        }

        if (this.getCustomer().getCustomerType() == ("Greedy")) {
            customerType = "(greedy)";
        }

        return String.format("%.3f %d%s waits to be served by %s %d", this.getEventTime(),
                this.getCustomerID(),customerType, serverType, this.getServer().getServerID());
    }
}
