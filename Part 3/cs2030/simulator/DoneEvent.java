package cs2030.simulator;

/**
 * DoneEvent class which handles the event where 
 * the server has finished serving the customer on hand.
 */
public class DoneEvent extends Event {
    /** 
     * The constructor which creates a done Event.
     * @param currentCustomer Customer who is part of the event.
     * @param eventTime Time of event.
     * @param state State of the event.
     * @param server Server that takes care of the done event.
     */
    public DoneEvent(Customer currentCustomer, double eventTime, State state, Server server) {
        super(currentCustomer, eventTime, state, server);
    }

    /**
     * Overrides the toString() method in String class.
     * @return Done event details of current time, current customer
     *         and current server will be returned in format of string.
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

        return String.format("%.3f %d%s done serving by %s %d", this.getEventTime(),
                this.getCustomerID(), customerType, serverType, this.getServer().getServerID());
    }
}
