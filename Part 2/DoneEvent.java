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
    public DoneEvent(Customer currentCustomer, double eventTime, int state, Server server) {
        super(currentCustomer, eventTime, state, server);
    }

    @Override
    public String toString() {
        return String.format("%.3f ",this.getEventTime()) + this.getCustomerID() + 
            " done serving by " + this.getServer().getServerID();
    }

}
