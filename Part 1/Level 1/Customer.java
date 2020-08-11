public class Customer {
	private final double arrivalTime;
	private final int ID;

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

	@Override
		public String toString() {
			return this.getID() + String.format(" arrives at %.3f", this.getArrivalTime());
		}

}
