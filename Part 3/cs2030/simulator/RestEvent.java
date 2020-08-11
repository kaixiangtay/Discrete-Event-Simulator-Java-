package cs2030.simulator;

/**
 * RestEvent class which handles the event
 * where the server goes to break.
 */
public class RestEvent extends Event {
    /** 
     * The constructor which creates a rest Event.
     * @param currentCustomer Customer of event.
     * @param eventTime Time of event.
     * @param state State of the event.
     * @param server Server who is part of the event.
     */
    public RestEvent(Customer currentCustomer, double eventTime, State state, Server server) {
        super(currentCustomer, eventTime, state, server);
    }
}