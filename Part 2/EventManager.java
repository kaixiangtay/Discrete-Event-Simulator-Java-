import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * EventManager class that handles the sequence of Event executions.
 */

public class EventManager {
    private static Scanner sc = new Scanner(System.in);
    private Server[] servers;
    private PriorityQueue<Event> queueEvents =  new PriorityQueue<>(new EventComparator());
    private Statistics eventStats = new Statistics();
    static int guest_ID = 0; 
    public static final int ARRIVES = 1;
    public static final int WAIT = 2;
    public static final int SERVED = 3;
    public static final int LEAVES = 4;
    public static final int DONE = 5;
    private static final double SERVICE_FREQUENCY = 1.0;

    /**
     * Method to set up the servers available for the entire event, 
     * and handle arrival of customers by adding them into the queue.
     */
    void initialiseQueue() {
        int numServers = Integer.valueOf(sc.next());
        this.servers = new Server[numServers];
        for (int i = 0; i < numServers; i++) {
            this.servers[i] = new Server();
        }

        while (sc.hasNext()) {
            guest_ID++; 
            double arriveTime =  sc.nextDouble();
            Customer newCustomer = new Customer(arriveTime, guest_ID);
            Event arriveEvent = new ArriveEvent(newCustomer, arriveTime, ARRIVES);
            this.queueEvents.add(arriveEvent);
        }
        sc.close();
    }

    /** 
     * The earliest available server will be returned
     * when there is a server available to serve
     * or a server that is currently serving a customer but has a
     * free waiting slot that allows the current customer to wait.
     * @param servers All of the servers will be checked in order.
     * @return Server or null will be returned if no servers are available.
     */
    Server checkServer(Server[] servers) {
        int numServers = servers.length;
        boolean hasWaitingSlot = false;
        Server waitServer = null;

        for (int i = 0; i < numServers; i++) {
            Server freeServer = servers[i];
            if (freeServer.canServe()) {
                return freeServer;
            }  
            if (freeServer.canWait() && !hasWaitingSlot) {
                hasWaitingSlot = true;    
                waitServer = freeServer;
            }
        }

        if (!hasWaitingSlot) {
            return null;
        } else {
            return waitServer;
        }
    }


    /**
     * The next event will be created depending on the
     * availability of the servers in the whole event.
     * Event, which could be either 
     * ServedEvent where there is a free server ready to serve the customer,
     * WaitEvent where there is a server occupied with customer but 
     * no customer is waiting for the same server yet,
     * or LeaveEvent where all the servers are not free and 
     * all of them have customers waiting to be served already.
     * All servers available in the event to be checked.
     * The server that is free without any customer 
     * will have an upcoming event of customer served binded to it. 
     * The server that is busy with a current customer but 
     * without a waiting customer will have an upcoming event 
     * of customer waiting binded to it.
     * In the event, no servers are free and all of them have a 
     * waiting customer, the next event will be the customer
     * leaving without being served by any servers.
     * Number of customers who have left will be updated here.
     * @param newEvent Current event that consist of customer who just arrives.
     */ 
    public void arrives(Event newEvent) {
        int numServers = this.servers.length;
        int serverIndex = 0;
        Customer currentCustomer = newEvent.getCustomer();
        double currentTime = newEvent.getEventTime();
        Server freeServer = checkServer(this.servers);
        
        if (freeServer != null) {
            if (freeServer.canServe()) {
                Event nextEvent = new ServedEvent(currentCustomer, currentTime, SERVED, freeServer);
                double serviceTime = currentTime + SERVICE_FREQUENCY;
                freeServer.setCustomerServed(currentCustomer);
                currentCustomer.setUpdatedTime(serviceTime);
                queueEvents.add(nextEvent);
            } else if (freeServer.canWait()) {
                Event nextEvent = new WaitEvent(currentCustomer, currentTime, WAIT, freeServer);
                freeServer.setCustomerWaited(currentCustomer);
                queueEvents.add(nextEvent);
            } 
        } else {
            Event nextEvent = new LeaveEvent(currentCustomer, currentTime, LEAVES);
            queueEvents.add(nextEvent);
            eventStats.increaseLeft();
        }
    }

    /**
     * ServedEvent will be created at the end and added to the queue which  
     * indicates the customer who has waited for the server can be served later on.
     * Time taken for the server to finish serving the customer on hand is calculated here.
     * State of server is also updated where the current 
     * customer is waiting to be served at the moment.
     * Customer new timing will be updated here accordingly.
     */
    public void wait(Event newEvent) {
        Server currentServer = newEvent.getServer();
        if (!currentServer.canWait()) {
            Customer currentCustomer = newEvent.getCustomer();
            double serviceTime = currentServer.getCustomerServed().getUpdatedTime();
            Event nextEvent = new ServedEvent(currentCustomer, serviceTime, SERVED, currentServer);
            double updatedTime = serviceTime + SERVICE_FREQUENCY;
            currentServer.setCustomerWaited(currentCustomer);
            currentCustomer.setUpdatedTime(updatedTime);
            queueEvents.add(nextEvent);
        }
    }


    /**
     * DoneEvent will be created and added to the queue at the end which 
     * indicates the server has finished serving the customer.
     * Time taken for the server to finish serving the customer is calculated here.
     * State of server is also updated to be serving the customer at the moment.
     * Overall waiting time(if any) of customer and 
     * number of customers served are updated here.
     */
    public void served(Event newEvent) {
        Customer currentCustomer = newEvent.getCustomer();
        Server currentServer = newEvent.getServer();
        double currentTime = newEvent.getEventTime();
        double serviceCompleteTime = currentTime + SERVICE_FREQUENCY;
        Event nextEvent = new DoneEvent(currentCustomer, serviceCompleteTime, DONE, currentServer);
        currentServer.setCustomerServed(currentCustomer);
        double updatedTime = currentTime - currentCustomer.getArrivalTime();
        eventStats.increaseWaitingTime(updatedTime);
        eventStats.increaseServed();
        queueEvents.add(nextEvent);
    }

    /**  
     * The state of the server will be updated here since there is
     * no more customer to be served at the moment
     * and the customer who has waited(if any) can be served now.
     */
    public void done(Event newEvent) {
        newEvent.getServer().updateServer();
    }


    /**  
     * Every time, the current event will be retrieved from the Priority Queue.
     * Before proceeding to the next event, the current event will be printed.
     * When there is an upcoming event, it will be added to the Priority Queue.
     * After all the events have completed, the overall statistics will be printed.
     */
    public void simulation() {
        initialiseQueue();

        while (!queueEvents.isEmpty()) {
            Event currentEvent = queueEvents.poll();
            int currentEventState = currentEvent.getState();
            System.out.println(currentEvent);
            switch (currentEventState) {
                case ARRIVES:
                    arrives(currentEvent);
                    break;
                case WAIT:
                    wait(currentEvent);
                    break;
                case SERVED:
                    served(currentEvent);
                    break;
                case DONE:
                    done(currentEvent);
                    break;    
                default:
                    break;
            }
        }
        System.out.println(eventStats);
    }
}
