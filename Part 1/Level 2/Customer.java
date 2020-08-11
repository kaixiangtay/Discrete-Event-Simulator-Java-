public class Customer {
	private final double arrivalTime;
	private final int ID;
	private static final double timeInterval = 1.0;

	public Customer(double arrivalTime, int ID) {
		this.arrivalTime = arrivalTime;
		this.ID = ID;
	}

	public double getArrivalTime() {
		return this.arrivalTime;
	}

	public int getID() {
		return this.ID;
	}

	public double getServiceCompletionTime() { // for level 2
		return timeInterval + this.getArrivalTime();
	}

	@Override
		public String toString() {
			return this.getID() + String.format(" arrives at %.3f", this.getArrivalTime());
		}

}
