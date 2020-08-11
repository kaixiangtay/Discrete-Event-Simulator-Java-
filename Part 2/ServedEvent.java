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
    public ServedEvent(Customer currentCustomer, double eventTime, int state, Server server) {
        super(currentCustomer, eventTime, state, server);
    }

    @Override
    public String toString() {
        return String.format("%.3f ", this.getEventTime()) + this.getCustomerID() 
            + " served by " + this.getServer().getServerID();
    }
}
