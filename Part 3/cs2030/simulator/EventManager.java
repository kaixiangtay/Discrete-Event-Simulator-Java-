package cs2030.simulator;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * EventManager class that handles the sequence of Event executions.
 */
public class EventManager { 
    private PriorityQueue<Event> queueEvents = new PriorityQueue<>(new EventComparator());
    private Server[] servers; 
    private Statistics statistics = new Statistics();
    private final int seed;
    private final int numServers;
    private final int maxQueueLength;
    private final int numCustomers;
    private final int numCounters;
    private final double arrivalRate;
    private final double serviceRate;
    private final double restingRate;
    private final double restingChance;
    private final double greedyCustomerChance;
    private final RandomGenerator randomGen;
    private int totalServers;

    /**
     * default Event Manager constructor which initialises all the
     * inputs of the whole event, consisting of the seed value,
     * number of Servers, number of Counters(Machine server),
     * maximum Queue Length, number of customers,
     * arrival rate, service rate, resting rate,
     * resting chance and the probability customer
     * is a greedy customer.
     */
    public EventManager() {
        Scanner sc = new Scanner(System.in);
        seed = sc.nextInt();
        numServers = sc.nextInt();
        numCounters = sc.nextInt();
        maxQueueLength = sc.nextInt();
        numCustomers = sc.nextInt();
        arrivalRate = sc.nextDouble();
        serviceRate = sc.nextDouble();
        restingRate = sc.nextDouble();
        restingChance = sc.nextDouble();
        greedyCustomerChance = sc.nextDouble();
        randomGen = new RandomGenerator(seed, arrivalRate, serviceRate, restingRate);
    }

    /**
     * Method to set up the servers available for the entire event, 
     * and handle arrival of customers by adding them into the queue.
     */
    public void initialiseQueue() {
        double arriveTime = 0;
        totalServers = numServers + numCounters;
        servers = new Server[totalServers];
        Server server;
        Customer customer;
        for (int i = 0; i < totalServers; i++) {
            if (i < numServers) {
                server = new Server(i + 1, "Human");
            } else {
                server = new MachineServer(i + 1, "Machine");
            }
            servers[i] = server;
        }

        for (int i = 0; i < numCustomers; i++) {
            double customerType = randomGen.genCustomerType();
            if (customerType < greedyCustomerChance) {
                customer = new GreedyCustomer(arriveTime, i + 1, "Greedy");
            } else {
                customer = new Customer(arriveTime, i + 1, "Typical");
            }
            Event arriveEvent = new ArriveEvent(customer, arriveTime, State.ARRIVES);
            arriveTime += randomGen.genInterArrivalTime();
            queueEvents.add(arriveEvent);
        }
    }

    /**
     * The earliest available server will be returned
     * when there is a server available to serve.
     * All of the servers will be checked in order.
     * @return Server or null will be returned if no servers are available.
     */
    public Server freeServer() {
        Server freeServer = Arrays.stream(servers)
                .filter(server -> server.canServe())
                .findFirst()
                .orElse(null);
        return freeServer;
    }

    /**
     * The servers with unfilled waiting queue will
     * be checked in order as well as the shortest
     * waiting queue will be checked in order.
     * @param customer current customer(can be greedy or typical)
     * @return earliest server with unfilled waiting queue
     *         if customer is typical customer, otherwise
     *         return shortest waiting queue for greedy customer.
     */
    public Server waitServer(Customer customer) {
        Server waitingServer = null;
        if (customer.getCustomerType() == ("Typical")) {
            waitingServer = Arrays.stream(servers)
                    .filter(server -> server.getQueueSize() != maxQueueLength)
                    .findFirst()
                    .orElse(null);
        }   else { // customer Type is Greedy
            int serverIndex = 0;
            int min = servers[serverIndex].getQueueSize();
            for (int i = 0; i < totalServers; i++) {
                int currentQueueSize = servers[i].getQueueSize();
                if (currentQueueSize != maxQueueLength) {
                    if (currentQueueSize < min) {
                        if (min == currentQueueSize) {
                            continue;
                        } else {
                            min = currentQueueSize;
                            serverIndex = i;
                        }
                    }
                    waitingServer = servers[serverIndex];
                }
            }
        }
        return waitingServer;
    }

    /**
     * Check current server identification number.
     * @param server current Server.
     * @return the identification number of the server.
     */
    public int getServerIndex(Server server) {
        int defaultValue = 0;
        int serverIndex = IntStream.range(0, totalServers)
                .filter(index -> servers[index].getServerID() == server.getServerID())
                .findFirst()
                .orElse(defaultValue);
        return serverIndex;
    }

    /**
     * The next event will be created depending on the
     * availability of the servers in the whole event.
     * Event, which could be either 
     * ServedEvent where there is a free server ready to serve the customer,
     * WaitEvent where there is a server occupied with customer but 
     * waiting queue allows current customer to wait for the server,
     * or LeaveEvent where all the servers are not free and 
     * all waiting queues of the servers are filled up already.
     * All servers available in the event to be checked.
     * The server that is free without any customer 
     * will have an upcoming event of customer served bind to it.
     * The server that is busy with a current customer but 
     * with a unfilled waiting queue will have an upcoming event
     * of customer waiting bind to it.
     * In the event, no servers are free and all the waiting
     * queues are occupied, the next event will be the customer
     * leaving without being served by any servers.
     * Number of customers who have left will be updated here.
     * @param newEvent Current event that consist of customer who has just
     *                 arrives and the current time of event.
     */
    public void arrives(Event newEvent) {
        Customer currentCustomer = newEvent.getCustomer();
        double currentTime = newEvent.getEventTime();
        Server server;
        int serverIndex;

        if (freeServer() != null) {
            serverIndex = getServerIndex(freeServer());
            server = servers[serverIndex];
            Event nextEvent = new ServedEvent(currentCustomer, currentTime, State.SERVED, server);
            queueEvents.add(nextEvent);
        } else {

            if (waitServer(currentCustomer) != null) {
                serverIndex = getServerIndex(waitServer(currentCustomer));
                server = servers[serverIndex];
                Event nextEvent = new WaitEvent(currentCustomer, currentTime, State.WAIT, server);
                queueEvents.add(nextEvent);
            } else {
                Event nextEvent = new LeaveEvent(currentCustomer, currentTime, State.LEAVES);
                queueEvents.add(nextEvent);
                statistics.increaseLeft();
            }
        }
    }

    /**
     * Waiting customer is added to the waiting queue of the server.
     * @param newEvent Current event that consist of customer who can be served
     *                 and the current server to serve the customer.
     */
    public void wait(Event newEvent) {
        Customer currentCustomer = newEvent.getCustomer();
        Server currentServer = newEvent.getServer();
        currentServer.addCustomer(currentCustomer);
    }

    /**
     * DoneEvent will be created and added to the queue at the end which 
     * indicates the server has finished serving the customer.
     * Time taken for the server to finish serving the customer is calculated here.
     * State and time of server is also updated to be serving the customer currently.
     * @param newEvent Current event that consist of customer who can be served,
     *                 current time and the current server to serve the customer.
     */
    public void served(Event newEvent) {
        Customer currentCustomer = newEvent.getCustomer();
        double currentTime = newEvent.getEventTime();
        Server currentServer = newEvent.getServer();
        double serviceTime = currentTime + randomGen.genServiceTime();
        currentServer.setTime(serviceTime);
        if (currentServer.customerWaiting(currentCustomer)) {
            currentServer.removeCustomer(currentCustomer);
        }
        currentServer.setCustomerServed(currentCustomer);
        Event doneEvent = new DoneEvent(currentCustomer, serviceTime, State.DONE, currentServer);
        queueEvents.add(doneEvent);
    }

    /**
     * ServedEvent will be created and added to the queue at the end
     * if the server is ready to serve the next customer.
     * If not, RestEvent will be created and added to the queue which
     * indicates the server wants to rest before serving next customer.
     * The state of the server will be updated here since there is
     * no more customer to be served at the moment.
     * Overall waiting time(if any) and the number of customers
     * served will be updated here.
     * @param newEvent Current event that consist of customer who can be served,
     *                 current time and the current server to serve the customer.
     */
    public void done(Event newEvent) {
        Server currentServer = newEvent.getServer();
        double currentTime = newEvent.getEventTime();
        currentServer.updateServer();
        statistics.increaseServed();
        if (currentServer.getServerType() == ("Machine"))  {
            if (currentServer.getQueueSize() > 0) {
                Customer nextCustomer = currentServer.getNextCustomer();
                double waitingTime = currentTime - nextCustomer.getArrivalTime();
                Event nextEvent = new ServedEvent(nextCustomer, currentTime,
                        State.SERVED, currentServer);
                statistics.increaseWaitingTime(waitingTime);
                queueEvents.add(nextEvent);
            }
        } else {
            double randomNumber = randomGen.genRandomRest();
            if (randomNumber < restingChance) {
                Customer nextCustomer  = null;
                Event restEvent = new RestEvent(nextCustomer, currentTime,
                        State.REST, currentServer);
                queueEvents.add(restEvent);
            } else {
                if (currentServer.getQueueSize() > 0) {
                    Customer nextCustomer = currentServer.getNextCustomer();
                    double waitingTime = currentTime - nextCustomer.getArrivalTime();
                    Event nextEvent = new ServedEvent(nextCustomer, currentTime,
                            State.SERVED, currentServer);
                    statistics.increaseWaitingTime(waitingTime);
                    queueEvents.add(nextEvent);
                }
            }
        }
    }

    /**
     * BackEvent will be created and added to the queue at the end
     * which indicates the human server will be back from break,
     * able to serve the next customer. The state and time of the
     * server will be updated here since there is
     * no more customer to be served at the moment.
     * Overall waiting time(if any) will be updated here.
     * @param newEvent Current event that consist of customer who can be served,
     *                 current time and the current server to serve the customer.
     */
    public void rest(Event newEvent) {
        double restTime = randomGen.genRestPeriod();
        double currentTime = newEvent.getEventTime();
        double updatedTime = restTime + currentTime;
        Server currentServer = newEvent.getServer();
        currentServer.setRest(true);
        currentServer.setTime(currentTime);
        Customer nextCustomer = null;
        Event backEvent = new BackEvent(nextCustomer, updatedTime, State.BACK, currentServer);
        queueEvents.add(backEvent);
    }

    /**
     * ServedEvent will be created and added to the queue at the end
     * which indicates the server is ready to serve the next customer.
     * The state of the server will be updated here since there is
     * no more customer to be served at the moment.
     * Overall waiting time(if any) will be updated here.
     * @param newEvent Current event that consist of customer who can be served,
     *                 current time and the current server to serve the customer.
     */
    public void back(Event newEvent) {
        Server currentServer = newEvent.getServer();
        currentServer.setRest(false);
        if (currentServer.getQueueSize() > 0) {
            Customer nextCustomer = currentServer.getNextCustomer();
            double currentTime = newEvent.getEventTime();
            double waitingTime = currentTime - nextCustomer.getArrivalTime();
            Event nextEvent = new ServedEvent(nextCustomer, currentTime,
                    State.SERVED, currentServer);
            statistics.increaseWaitingTime(waitingTime);
            queueEvents.add(nextEvent);
        }
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
            State currentEventState = currentEvent.getState();
            switch (currentEventState) {
                case ARRIVES:
                    System.out.println(currentEvent);
                    arrives(currentEvent);
                    break;
                case SERVED:
                    System.out.println(currentEvent);
                    served(currentEvent);
                    break;
                case WAIT:
                    System.out.println(currentEvent);
                    wait(currentEvent);
                    break;
                case LEAVES:
                    System.out.println(currentEvent);
                    break;
                case DONE:
                    System.out.println(currentEvent);
                    done(currentEvent);
                    break;
                case REST:
                    rest(currentEvent);
                    break;
                case BACK:
                    back(currentEvent);
                    break;
                default:
                    break;
            }
        }
        System.out.println(statistics);
    }
}