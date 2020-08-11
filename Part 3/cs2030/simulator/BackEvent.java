package cs2030.simulator;

/**
 * BackEvent class which handles the event where
 * the server returns from the break.
 */
public class BackEvent extends Event {
    /** 
     * The constructor which creates a back Event.
     * @param currentCustomer Customer of event.
     * @param eventTime Time of event.
     * @param state State of the event.
     * @param server Server who is part of the event.
     */
    public BackEvent(Customer currentCustomer, double eventTime, State state, Server server) {
        super(currentCustomer, eventTime, state, server);
    }
}