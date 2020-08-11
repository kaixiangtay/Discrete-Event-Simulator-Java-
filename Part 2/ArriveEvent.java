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
    public ArriveEvent(Customer currentCustomer, double eventTime, int state) {
        super(currentCustomer, eventTime, state);
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getEventTime()) + ' ' + this.getCustomerID() + " arrives";
    }
}

