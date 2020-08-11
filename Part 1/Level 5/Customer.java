/**
* Customer class which comprises the details of the customer.
*/

public class Customer {
    private final double arrivalTime;
    /**
    * updated time acts like a counter, used as memory to update each customer's live time when required
    */
    private double updatedTime; 
    private final int ID;
    private State customerState; 

    /**
    * The customer overload constructor that generates customer's information
    * regarding their ID, arrival time and state.
    * @param ID identification number of the customer.
    * @param arrivalTime time which the customer arrives.
    * @param customerState status of the customer(arrives, waits, done, leaves).
    */
    public Customer(double arrivalTime, int ID, State customerState) {
        this.arrivalTime = arrivalTime;
        this.updatedTime = arrivalTime;
        this.ID = ID;
        this.customerState = customerState;
    }

    /**
    * Getter method to retrieve the arrival time of the customer.
    */
    public double getArrivalTime() {
        return this.arrivalTime;
    }

    /**
    * Getter method to retrieve the identification number of the customer.
    */
    public int getID() {
        return this.ID;
    }

    /**
    * Getter method to retrieve the status of the customer.
    */
    public State getCustomerState() {
        return this.customerState;
    }

    /**
    * Setter method to update the state of the customer.
    * @param newState The new state to be updated for the customer.
    */
    public void setCustomerState(State newState) { 
        this.customerState = newState;
    }

    /**
    * Getter method to get time information of the customer after state change. 
    * @return Time information of the customer.
    */
    public double getUpdatedTime() {
        return this.updatedTime;
    }
    
    /**
    * Setter method to update customer time details in the event of state change.
    * @param latestTiming new timing of customer to be updated accordingly after change of state.
    */
    public void setUpdatedTime(double latestTiming) {
        this.updatedTime = latestTiming;
    }
    
    /**
    * Getter method to calculate and retrieve the waiting time of customer.
    * @return waiting time of customer will be returned as required.
    */
    public double getWaitingTime() {
        double waitingTime = this.getUpdatedTime() - this.getArrivalTime();
        return waitingTime;
    } 
   
    /**
    * Overrides the toString() method in String class.
    * @return Customer details of identification number, time and state will be returned in format of string.
    */
    @Override
    public String toString() {
        String state = " ";
        State customerState = this.getCustomerState();
        switch(customerState) {
            case ARRIVES:
                state = "arrives";
                break;
            case SERVED:
                state = "served";
                break;
            case WAIT:
                state = "waits";
                break;
            case LEAVES:
                state = "leaves";
                break;
            case DONE:
                state = "done";
                break;
            default: 
                break;
        }
        return String.format("%.3f ",this.getUpdatedTime()) + this.getID() + " " + state;
    }
}