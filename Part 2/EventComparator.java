import java.util.Comparator;

/**
 * EventComparator class implements the Comparator class with a generic Event type.
 * This class will arrange the events according to the earliest occurrence based on time.
 * If there are events that happens at the same timing, 
 * the customer with a smaller ID number will be given priority.
 */
public class EventComparator implements Comparator<Event> {

    /**
     * Taking two events in comparison to decide the order.
     * Negative integer will be returned if occurrence of 
     * first event is earlier than second event.
     * zero will be returned if there is no priority between first event 
     * and second event based on time and customer ID.
     * positive integer will be returned if occurrence of first event is later than second event.
     * @param firstEvent First event being used in comparison.
     * @param secondEvent Second event being used in comparison.
     * @return value of integer to decide order in priority queue.
     */
    @Override
    public int compare(Event firstEvent, Event secondEvent) {
        if (firstEvent.getEventTime() != secondEvent.getEventTime()) {
            if (firstEvent.getEventTime() > secondEvent.getEventTime()) {
                return 1;
            } else if (firstEvent.getEventTime() < secondEvent.getEventTime()) {
                return -1;
            } else {
                return 0;
            }
        } else {
            if (firstEvent.getCustomerID() < secondEvent.getCustomerID()) {
                return -1;
            } else if (firstEvent.getCustomerID() > secondEvent.getCustomerID()) {
                return 1;
            } else {
                return 0;
            }
        }

    }
}
