/**
 * LeaveEvent class which handles the event of the customer who leave
 * right away without being served by any server.
 */
public class LeaveEvent extends Event {

    /** 
     * The constructor which creates a leave Event.
     * @param currentCustomer Customer who is part of the event.
     * @param eventTime Time of event.
     * @param state State of the event.
     */
    public LeaveEvent(Customer currentCustomer, double eventTime, int state) {
        super(currentCustomer, eventTime, state);
    }

    @Override
    public String toString() {
        return String.format("%.3f ", this.getEventTime()) + this.getCustomerID() + " leaves";
    }

}
