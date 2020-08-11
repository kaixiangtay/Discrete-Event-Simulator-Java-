package cs2030.simulator;

/**
 * ArriveEvent class which handles the event where the customer first arrives.
 */
public class ArriveEvent extends Event {
    /** 
     * The constructor which creates a arrive Event.
     * @param currentCustomer Customer who is part of the event.
     * @param eventTime Time of event.
     * @param state State of the event.
     */
    public ArriveEvent(Customer currentCustomer, double eventTime, State state) {
        super(currentCustomer, eventTime, state);
    }

    /**
     * Overrides the toString() method in String class.
     * @return Arrive event details of current time and
     *         current customer who arrives will be returned in format of string.
     */
    @Override
    public String toString() {
        String customerType = "";
        if (this.getCustomer().getCustomerType() == ("Greedy")) {
            customerType = "(greedy)";
        }
        return String.format("%.3f %d%s arrives", this.getEventTime(),
                this.getCustomerID(), customerType);
    }
}
