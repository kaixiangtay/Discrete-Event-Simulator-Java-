import java.util.Scanner;
import java.util.PriorityQueue;

public class Main { 
    /**
    * initialise customer identification number to zero since no customer at the start.
    */
    static int guest_ID = 0; 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        PriorityQueue<Customer> customerInfo = newCustomer(sc); 
        Event queueEvent = new Event(customerInfo, new Server());
        queueEvent.allTransitions();
    }

    /**
    * method to add customer to priority queue one by one 
    * and assign identification number to each customer in order of input
    */
    public static PriorityQueue<Customer> newCustomer(Scanner sc) {
        PriorityQueue<Customer> customerInfo =  new PriorityQueue<>(new CustomerComparator());
        while (sc.hasNext()) {
            double arriveTime =  sc.nextDouble();
            guest_ID++; 
            customerInfo.add(new Customer(arriveTime, guest_ID, State.ARRIVES));
        }
        sc.close();
        return customerInfo;
    } 

}
