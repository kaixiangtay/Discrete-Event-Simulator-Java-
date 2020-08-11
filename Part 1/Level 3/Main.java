import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static final int ARRIVES = 1;
	public static final int SERVED = 2;
	public static final int LEAVES = 3;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // scanner to take in user input
		ArrayList<Customer> customerInfo = new ArrayList<>(); // list to store customer details (ID and time)
		int guest_ID = 0; // customer ID tag number(intialised to 0)

		while(sc.hasNext()) {
			guest_ID = guest_ID + 1; 
			double arriveTime = sc.nextDouble();
			customerInfo.add(new Customer(arriveTime, guest_ID, ARRIVES)); // storing arrival time and ID of customers to a list, add the current arrives state!
		}
		sc.close(); // close scanner when not in use
		outputCustomerInfo(customerInfo); // print out details of all customers 
		System.out.println("Number of customers: " + customerInfo.size());
	}

	public static void outputCustomerInfo(ArrayList<Customer> customerInfo) {
		Customer currentCustomer = null; // used as memory to store details of current customer being served
		int customerState = 0;

		for(int i = 0; i < customerInfo.size(); i++) {
			Customer newCustomer = customerInfo.get(i); 
			System.out.println(newCustomer); 

			Server serverStatus = new Server(currentCustomer).serve(newCustomer); // check whether server is free to serve customer
			if(serverStatus != null) { // if server is free to serve
				customerState = SERVED;
				currentCustomer = newCustomer.setCustomerState(customerState); // change the state of new customer to SERVED, new customer is the current customer being served
				System.out.println(currentCustomer);
			} else { // server is busy(there is a current customer)
				customerState = LEAVES;
				newCustomer = newCustomer.setCustomerState(customerState); // change the state of new customer to LEAVES
				System.out.println(newCustomer);
			}
		}  
	} 
}
