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
    public WaitEvent(Customer currentCustomer, double eventTime, int state, Server server) {
        super(currentCustomer, eventTime, state, server);
    }

    @Override
    public String toString() {
        return String.format("%.3f ",this.getEventTime()) + this.getCustomerID() 
            + " waits to be served by " + this.getServer().getServerID();
    }

}
