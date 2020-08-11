package cs2030.simulator;

/**
 * ServedEvent class which takes care of the event where 
 * the customer is served by the server.
 */
public class ServedEvent extends Event {
    /** 
     * The constructor which creates a served Event.
     * @param currentCustomer Customer who is part of the event.
     * @param eventTime Time of event.
     * @param state State of event.
     * @param server Server that takes care of the served event.
     */
    public ServedEvent(Customer currentCustomer, double eventTime, State state, Server server) {
        super(currentCustomer, eventTime, state, server);
    }

    /**
     * Overrides the toString() method in String class.
     * @return Served event details of current time, current customer served,
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
        return String.format("%.3f %d%s served by %s %d", this.getEventTime(),
                this.getCustomerID(), customerType, serverType, this.getServer().getServerID());
    }
}
