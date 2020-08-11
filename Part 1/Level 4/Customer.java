public class Customer {
	public static final int ARRIVES = 1;
	public static final int SERVED = 2;
	public static final int LEAVES = 3;
	public static final int DONE = 4;
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

	public void setCustomerState(int newState) { 
		this.customerState = newState;
	}

	public double getServiceCompletionTime() { 
		if(this.getCustomerState() == DONE) {
			double updatedTime = serviceTimeInterval + this.getArrivalTime(); 
			return updatedTime;
		}  else {
			return this.getArrivalTime();
		}
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
				case DONE:
					state = "done";
					break;
				default: // do nothing
					break;
			}
			return String.format("%.3f ",this.getServiceCompletionTime()) + this.getID() + " " + state;
		}
}
