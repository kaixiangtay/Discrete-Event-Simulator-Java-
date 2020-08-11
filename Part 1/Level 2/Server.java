public class Server {
	private final Customer currentCustomer;

	public Server(Customer currentCustomer) {
		this.currentCustomer = currentCustomer;
	}

	public boolean canServe (Customer newCustomer) {
		if(this.currentCustomer == null) {
			return true;
		} else if(this.currentCustomer != null && (newCustomer.getArrivalTime() >= this.currentCustomer.getServiceCompletionTime())) {
			return true;
		} else {
			return false;
		}
	}

	public Server serve(Customer newCustomer) {
		if(canServe(newCustomer)) {
			return new Server(newCustomer);
		} else {
			return null;
		}
	}

	@Override
		public String toString() {
			return String.format("Customer served; next service @ %.3f", this.currentCustomer.getServiceCompletionTime());
		}
}
