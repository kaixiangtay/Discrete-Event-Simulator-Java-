package cs2030.simulator;

/**
 * Abstract Event class which enables subclasses 
 * ArriveEvent, LeaveEvent, DoneEvent, WaitEvent, ServedEvent
 * as well as BackEvent and RestEvent when Human server
 * go for break and resume from break to generate the
 * next event and update statistics as required.
 */
public abstract class Event {
    protected final Customer currentCustomer;
    protected final double eventTime;
    protected final State state;
    protected Server server;

    /**
     * The event constructor that generates a new event
     * with information of customer details and 
     * time of event taking place available. 
     * It is used for ArriveEvent and LeaveEvent.
     * @param currentCustomer Customer that is part of the event.
     * @param eventTime Time of the event.
     * @param state State of event.
     */
    public Event(Customer currentCustomer, double eventTime, State state) {
        this.currentCustomer = currentCustomer;
        this.eventTime = eventTime;
        this.state = state;
    }

    /**
     * The event constructor that generates a new event
     * with information of customer details, current
     * server and time of event taking place available.
     * It is used for WaitEvent, ServedEvent,
     * DoneEvent, RestEvent and also BackEvent.
     * @param currentCustomer Customer that is part of the event.
     * @param eventTime Time of the event.
     * @param state State of event.
     * @param server Server of the event.
     */ 
    public Event(Customer currentCustomer, double eventTime, State state, Server server) {
        this.currentCustomer = currentCustomer;
        this.eventTime = eventTime;
        this.state = state;
        this.server = server;
    }

    /**
     * Getter method to retrieve current Server.
     * @return Server of the current event.
     */
    public Server getServer() {
        return this.server;
    }

    /**
     * Getter method to retrieve current time.
     * @return Time of the current event.
     */
    public double getEventTime() {
        return this.eventTime;
    }

    /**
     * Getter method to retrieve current customer.
     * @return Customer of the current event.
     */
    public Customer getCustomer() {
        return this.currentCustomer;
    }

    /**
     * Getter method to retrieve current identification of customer.
     * @return Customer's identification number of the current event.
     */
    public int getCustomerID() {
        return this.currentCustomer.getCustomerID();
    }

    /**
     * Getter method to retrieve current state.
     * @return State of the current event.
     */
    public State getState() {
        return this.state;
    }

}
