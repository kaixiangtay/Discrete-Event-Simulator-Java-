public class Server {
	private final Customer currentCustomer;
	private final double nextServiceTime;
	private static final double serviceInterval = 1.0;

	public Server() {
		this.currentCustomer = null;
		this.nextServiceTime = 0;
	}

	public Server(Customer currentCustomer, double nextServiceTime) {
		this.currentCustomer = currentCustomer;
		this.nextServiceTime = nextServiceTime;
	}

	public double getServiceTime() {
		return this.nextServiceTime;
	}

	public boolean canServe (Customer newCustomer) {
		if(this.currentCustomer == null) {
			return true;
		} else if(this.currentCustomer != null && (newCustomer.getArrivalTime() >= this.getServiceTime())) {
			return true;
		} else {
			return false;
		}
	}

	public Server serve(Customer newCustomer) {
		double newServiceTime = newCustomer.getArrivalTime() + serviceInterval;
		return new Server(newCustomer, newServiceTime);
	}
}
