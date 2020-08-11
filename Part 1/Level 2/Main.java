import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // scanner to take in user input
		ArrayList<Customer> customerInfo = new ArrayList<>(); // list to store customer details (ID and time)
		int guest_ID = 0; // customer ID tag number(intialised to 0)

		while(sc.hasNext()) {
			guest_ID = guest_ID + 1; 
			double arriveTime = sc.nextDouble();
			customerInfo.add(new Customer(arriveTime, guest_ID)); // storing arrival time and ID of customers to a list
		}
		sc.close(); // close scanner when not in use
		outputCustomerInfo(customerInfo); // print out details of all customers 
	}

	public static void outputCustomerInfo(ArrayList<Customer> customerInfo) {
		Customer currentCustomer = null; // used as memory to store details of customer being served

		for(int i = 0; i < customerInfo.size(); i++) {
			Customer newCustomer = customerInfo.get(i);
			System.out.println(newCustomer); 

			Server serverStatus = new Server(currentCustomer).serve(newCustomer); // check whether server is free to serve customer
			if(serverStatus != null) {
				currentCustomer = newCustomer;
				System.out.println(serverStatus);
			} else {
				System.out.println("Customer leaves");
			}
		}
		System.out.println("Number of customers: " + customerInfo.size());
	}
}
