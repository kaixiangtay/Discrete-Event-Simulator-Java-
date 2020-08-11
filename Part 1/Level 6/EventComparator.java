import java.util.Comparator;

/**
* customerComparator class implements the Comparator class with a generic Customer type.
* This class will arrange the events of the customers according to the earliest occurence based on time.
* If there are events of the customers that happens at the same timing, the customer with a smaller ID number will be given priority.
*/
public class EventComparator implements Comparator<Event> {

	 /**
    * Taking two customers in comparison to decide the order.
    * @param firstCustomer First customer being used in comparison.
    * @param secondCustomer Second customer being used in comparison.
	* @return negative integer will be returned if first customer's details less than second customer's details,
	* zero will be returned if first customer's details same as second customer's details
	* postive integer will be returned if first customer's details greater than second customer's details

    */
	public int compare(Event firstEvent, Event secondEvent) {
		if (firstEvent.getUpdatedTime() != secondEvent.getUpdatedTime()) {
			if (firstEvent.getUpdatedTime() > secondEvent.getUpdatedTime()) {
				return 1;
			} else if (firstEvent.getUpdatedTime() < secondEvent.getUpdatedTime()) {
				return -1;
			} else {
				return 0;
			}
		} else {
			if(firstEvent.getID() < secondEvent.getID()) {
				return -1;
			} else if(firstEvent.getID() > secondEvent.getID()) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}