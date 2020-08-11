package cs2030.simulator;

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
    public LeaveEvent(Customer currentCustomer, double eventTime, State state) {
        super(currentCustomer, eventTime, state);
    }

    /**
     * Overrides the toString() method in String class.
     * @return Leave event details of current time and current customer
     *         who leaves will be returned in format of string.
     */
    @Override
    public String toString() {
        String customerType = "";
        if (this.getCustomer().getCustomerType() == ("Greedy")) {
            customerType = "(greedy)";
        }
        return String.format("%.3f %d%s leaves", this.getEventTime(),
                this.getCustomerID(), customerType);
    }
}
