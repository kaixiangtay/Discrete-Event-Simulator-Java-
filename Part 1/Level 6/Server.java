]/**
*Server class which contains the details of the server.
*/

public class Server {
	private final Customer currentCustomer;
	private final Customer waitingCustomer;
	private final double nextServiceTime;
	private int serverID;
	private static int count = 1;
	/**
    *service interval is the time required for Server to finish serving one customer
    */
	private static final double serviceInterval = 1.0;

	/**
	*The default constructor to initialise the only server available
	*where no customer to be served at the start.
    */
	public Server() {
		this.currentCustomer = null;
		this.waitingCustomer = null;
		this.nextServiceTime = 0;
		this.serverID = count;
		count++;
	}

	/**
	*Server with overloaded constructor which generates a server to serve a customer accompanied by information on the next available time the Server is free to serve again.
    *@param currentCustomer The customer that the server is serving at the moment.
    *@param nextServiceTime The next service time which the server is availabe to serve a new customer.
    */
    public Server(Customer currentCustomer, double nextServiceTime) {
		this.currentCustomer = currentCustomer;
		this.nextServiceTime = nextServiceTime;
    }

	/**
    *Getter method to retrieve the next service time of the server.
    *@return The next service time of the server.
    */
	public double getServiceTime() {
		return this.nextServiceTime;
	}

	/**
    *Method to check whether the server is free to serve the incoming customer(can be waiting or just arrived).
    *@param incomingCustomer The incoming customer to serve.
    *@return True only if the customer can be served, else it will be false when customer cannot be served
    */
    public boolean canServe (Customer incomingCustomer) {
		if(this.currentCustomer == null) {
			return true;
		} else if(this.currentCustomer != null && (incomingCustomer.getUpdatedTime() >= this.getServiceTime())) {
			return true;
		} else {
			return false;
		}
	}

	/**
    *Server being created to serve the new customer.
    *@param newCustomer The new Customer to be served by the server.
    *@return the only server to serve the customer with updated service time.
    */
    public Server serve(Customer newCustomer) {
		double newServiceTime = newCustomer.getUpdatedTime() + serviceInterval;
		return new Server(newCustomer, newServiceTime);
	}
}