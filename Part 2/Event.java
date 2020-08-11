/**
 * Abstract Event class which enables subclasses 
 * ArriveEvent, LeaveEvent, DoneEvent, WaitEvent, ServedEvent
 * to generate the next event and update statistics as required.
 */
public abstract class Event {
    protected final Customer currentCustomer;
    protected final double eventTime;
    protected final int state;
    protected Server server;
    public static final int ARRIVES = 1;
    public static final int WAIT = 2;
    public static final int SERVED = 3;
    public static final int LEAVES = 4;
    public static final int DONE = 5;

    /**
     * The event constructor that generates a new event
     * with information of customer details and 
     * time of event taking place available. 
     * It is used for ArriveEvent and LeaveEvent.
     * @param currentCustomer Customer that is part of the event.
     * @param eventTime Time of the event.
     * @param state State of event.
     */
    public Event(Customer currentCustomer, double eventTime, int state) {
        this.currentCustomer = currentCustomer;
        this.eventTime = eventTime;
        this.state = state;
    }

    /**
     * The event constructor that generates a new event
     * with information of customer details and 
     * time of event taking place available. 
     * It is used for WaitEvent, ServedEvent and DoneEvent.
     * @param currentCustomer Customer that is part of the event.
     * @param eventTime Time of the event.
     * @param state State of event.
     * @param server Server of the event.
     */
    public Event(Customer currentCustomer, double eventTime, int state, Server server) {
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
     * @return Customer's identifcation number of the current event.
     */
    public int getCustomerID() {
        return this.currentCustomer.getCustomerID();
    }

    /**
     * Getter method to retrieve current state.
     * @return State of the current event.
     */
    public int getState() {
        return this.state;
    }
}
