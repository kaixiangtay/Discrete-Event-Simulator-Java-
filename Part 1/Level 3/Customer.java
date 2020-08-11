public class Customer {
	public static final int ARRIVES = 1;
	public static final int SERVED = 2;
	public static final int LEAVES = 3;
	private static final double serviceTimeInterval = 1.0;
	private final double arrivalTime;
	private final int ID;
	private int customerState; 

	public Customer(double arrivalTime, int ID, int customerState) {
		this.arrivalTime = arrivalTime;
		this.ID = ID;
		this.customerState = customerState;
	}

	public double getArrivalTime() {
		return this.arrivalTime;
	}

	public int getID() {
		return this.ID;
	}

	public int getCustomerState() {
		return this.customerState;
	}

	public Customer setCustomerState(int newState) { 
		return new Customer(this.getArrivalTime(), this.getID(), newState);
	}

	public double getServiceCompletionTime() { 
		double updatedTime = serviceTimeInterval + this.getArrivalTime(); 
		return updatedTime;
	}

	@Override
		public String toString() {
			String state = " ";
			int customerState = this.getCustomerState();

			switch(customerState) {
				case ARRIVES:
					state = "arrives";
					break;
				case SERVED:
					state = "served";
					break;
				case LEAVES:
					state = "leaves";
					break;
				default: // do nothing
					break;
			}
			return String.format("%.3f ", this.getArrivalTime()) + this.getID() +  " " + state;
		}
}
