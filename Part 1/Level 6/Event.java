public abstract class Event {
    /**
     * Customer that the event is involving.
     */
    private final Customer customer;
    /**
     * Time at which the event is created, 
     * which may differ from customer arrival time if 
     * it is only created when it is caused to wait by another preceeding event.
     */
    private final double time;

    /** 
     * Creates an Event.
     * @param customer customer that the event is involving
     * @param time time at which event is created
     */
    public Event(Customer customer, double time) {
        this.customer = customer;
        this.time = time;
    }


    /** 
     * Creates the next event of parent type based on its original type.
     */
    public abstract Event getNextEvent(Server [] servers,RandomGenerator gen);

    /** Modifies information in statistics if required.
     */
    public abstract void updateStatistics(Statistics statistics);

    public Customer getCustomer() {
        return this.customer;
    }

    public int getCustomerID() {
        return this.customer.getCustomerID();
    }

    public double getTime() {
        return this.time;
    }
}