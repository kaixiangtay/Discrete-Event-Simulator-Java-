import java.util.Comparator;

public class CustomerComparator implements Comparator<Customer> {
	public int compare(Customer c1, Customer c2) {
		if (c1.getServiceCompletionTime() != c2.getServiceCompletionTime()) {
			if (c1.getServiceCompletionTime() < c2.getServiceCompletionTime()) {
				return -1;
			} else if (c1.getServiceCompletionTime() > c2.getServiceCompletionTime()) {
				return 1;
			} else {
				return 0;
			}
		} else {
			if(c1.getID() < c2.getID()) {
				return -1;
			} else if(c1.getID() > c2.getID()) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
