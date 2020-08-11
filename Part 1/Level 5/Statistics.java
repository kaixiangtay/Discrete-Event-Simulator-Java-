/**
* Statistics class to keep track of the total waiting time of all customers being served eventually after waiting,
* the number of customers who have left themselves without being served by the server.
* the number of customers who are served by the server.
* keep track the details of waiting Customer details
*/

public class Statistics {
    private double totalWaitingTime;
    private int customerLeft;
    private int customerServed;

    /**
    * object statistics created using default constructor with 
    * inital conditions of total waiting time set to zero as well as
    * number of customers who left and number of customers served both set to zero
    */
    public Statistics() {
        totalWaitingTime = 0;
        customerLeft = 0;
        customerServed = 0;
    }

    /**
    * Method to increase the number of customers being served by the server.
    */
    public void increaseServed() {
        customerServed++;
    }

    /**
    * Method to increase the overall waiting time of customers.
    */
    public void increaseWaitingTime(double latestTime) {
        totalWaitingTime = totalWaitingTime + latestTime;
    }

    /**
    * Method to increase the number of customers who have left without being served by the server.
    */
    public void increaseLeft() {
        customerLeft++;
    }

    /**
    * Overrides the toString() method in String class.
    * @return event details of average waiting time, number of customers served
    * and number of customers left(in order) will be returned in format of string.
    */
    @Override
    public String toString() {
        double averageWaitingTime = totalWaitingTime / customerServed;
        return String.format("[%.3f %d %d]", averageWaitingTime, customerServed, customerLeft);
    }
}