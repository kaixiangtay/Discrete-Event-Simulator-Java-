import java.util.PriorityQueue;
import java.util.ArrayList;

/**
* Event class which manages processes(arrives, served, leaves, done, wait) 
* and transition between processes.
*/

public class Event {
    private PriorityQueue<Customer> queueCustomer;
    Statistics eventStatistics = new Statistics();
    private Server onlyServer;
    /**
    * waiting customer used to keep track if there is any customer required to wait, 
    * initialised to null because no customer is required to wait at the start.
    */
    private Customer waitingCustomer = null;
    private final State ARRIVES = State.ARRIVES;
    private final State SERVED = State.SERVED;
    private final State LEAVES = State.LEAVES;
    private final State DONE = State.DONE;
    private final State WAIT = State.WAIT;
    
    /**
    * Event constructor to set up information relating to statistics and customers in the queue.
    * @param customerInfo Customers in the queue.
    * @param onlyServer Initialise the only server available to serve.
    */
    public Event(PriorityQueue<Customer> customerInfo, Server onlyServer) {
        this.queueCustomer = customerInfo;
        this.onlyServer = onlyServer;
    }
    
    /**
    * Method which handles whenever there is an incoming customer that arrives. 
    * As soon as the incoming customer arrives, if the server is free(no customer), 
    * the server will serve the incoming customer.
    * Ifthe server is already serving a customer, the incoming customer has to wait to be served.
    * If there is another customer waiting to be served, the incoming customer will leave right away.
    * @param currentCustomer The customer that just arrived.
    */
    public void arrives(Customer currentCustomer) {
        if (onlyServer.canServe(currentCustomer) && waitingCustomer == null) {
            currentCustomer.setCustomerState(SERVED);
            onlyServer = onlyServer.serve(currentCustomer);
        } else {
            if (waitingCustomer == null) {
                currentCustomer.setCustomerState(WAIT);
                waitingCustomer = currentCustomer;
            } else {
                currentCustomer.setCustomerState(LEAVES);
                eventStatistics.increaseLeft();
            }
        } 
        queueCustomer.add(currentCustomer);
    }

    /**
    *The customer that has waited, will be served shortly. 
    *@param currentCustomer The customer that has waited.
    *overall waiting time updated here.
    */
    public void wait(Customer currentCustomer) {
        currentCustomer.setUpdatedTime(onlyServer.getServiceTime());
        onlyServer = onlyServer.serve(currentCustomer);
        currentCustomer.setCustomerState(SERVED);
    }

    /**
    *The customer can be served, and will be added back to the queue.
    *@param currentCustomer The customer that can be served.
    *overall waiting time and number of customers served are updated here.
    */
    public void served(Customer currentCustomer) {
        eventStatistics.increaseWaitingTime(currentCustomer.getWaitingTime());
        currentCustomer.setCustomerState(DONE);
        currentCustomer.setUpdatedTime(onlyServer.getServiceTime());
        queueCustomer.add(currentCustomer); 
        eventStatistics.increaseServed();
    }
    
    /**
    *The customer has been served and if there is a waiting customer.
    *the waiting customer can be added back into the queue, next to be served.
    *At the same time, since the customer who is waiting previously, no longer waits now,
    *there will be a new customer that can wait to be served now.
    */
    public void done() {
        if (waitingCustomer != null) {
            queueCustomer.add(waitingCustomer);
            waitingCustomer = null;
        }
    }
    
    /**
    *Manages all the events that happen
    */
    public void allTransitions() {
        while (!queueCustomer.isEmpty()) {
            Customer currentCustomer = queueCustomer.poll();
            State customerState = currentCustomer.getCustomerState();
            System.out.println(currentCustomer);
            switch (customerState) {
                case ARRIVES:
                    arrives(currentCustomer);
                    break;
                case WAIT:
                    wait(currentCustomer);
                    break;
                case SERVED:
                    served(currentCustomer);
                    break;
                case DONE:
                    done();
                    break;    
                default:
                    break;
            }
        }
        System.out.println(eventStatistics);
    }
}