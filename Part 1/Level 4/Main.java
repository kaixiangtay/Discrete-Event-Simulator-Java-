import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Spliterator;
import java.util.Iterator;

public class Main {
	public static final int ARRIVES = 1;
	public static final int SERVED = 2;
	public static final int LEAVES = 3;
	public static final int DONE = 4;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // scanner to take in user input
		PriorityQueue<Customer> customerInfo =  new PriorityQueue<>(new CustomerComparator()); // list to store customer details (ID and time)
		int guest_ID = 0; // customer ID tag number(intialised to 0)

		while (sc.hasNext()) {
			guest_ID = guest_ID + 1;
			double arriveTime = sc.nextDouble();
			customerInfo.add(new Customer(arriveTime, guest_ID, ARRIVES)); // storing arrival time and ID of customers
			// to a list, add the current arrives state!
		}
		sc.close(); // close scanner when not in use
		int numCustomers = customerInfo.size(); // get number of customers
		addCustomerArrivals(customerInfo); // add arrivals of customers
		queueCustomer(customerInfo, new Server()); // organise queue
		System.out.println("Number of customers: " + numCustomers);
	}

	public static void queueCustomer(PriorityQueue<Customer> customerInfo, Server onlyServer) {
		System.out.println();

		while(!customerInfo.isEmpty()) {
			Customer newCustomer = customerInfo.poll(); // retrieve customer details from Priority Queue
			int customerState = newCustomer.getCustomerState(); // get current state of customer 
			String nextEvent = "# Get next event: " + newCustomer.toString(); // print the upcoming event(whether Customer is served, leaves or done serving)
			System.out.println(nextEvent);

			switch(customerState) {
				case ARRIVES:
					if(onlyServer.canServe(newCustomer)) {
						newCustomer.setCustomerState(SERVED); // change the state of customer from Arrives to Served
						onlyServer = onlyServer.serve(newCustomer); // update server status
					} else {
						newCustomer.setCustomerState(LEAVES); // change the state of customer from Arrives to Leaves
					}
					customerInfo.add(newCustomer); // add to queue
					break;
				case SERVED:
					newCustomer.setCustomerState(DONE); // change the state of customer from SERVED to Done
					customerInfo.add(newCustomer); // add to queue
					break;
				default: // do nothing
					break;
			}

			Iterator itr = customerInfo.iterator();
			while (itr.hasNext()) {
				System.out.println(itr.next());
			}
			queueCustomer(customerInfo, onlyServer);	
		}
	}

	public static void addCustomerArrivals(PriorityQueue<Customer> customerInfo) {
		Spliterator<Customer> spt = customerInfo.spliterator(); 
		System.out.println("# Adding arrivals");

		// forEachRemaining method of Spliterator 
		spt.forEachRemaining((n) -> System.out.println(n));  // print result from Spliterator
	}
} 
