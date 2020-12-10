
public class Routeur {
    private final int nConnections;   // Max number of allowed connections for the router
    public boolean[] connections;   // Assign each connection as free(false) or busy(true)
    public static Semaphore connection;
    public int nBusy;   // Occupied Connections


    Routeur(int nConnections) {
        this.nConnections = nConnections;
        connection = new Semaphore(nConnections);
        connections = new boolean[nConnections];
    }

    /**
     * Use a connection if available
     * @param device in use
     * @return the number of the taken connection
     * @throws InterruptedException ex
     */
    public synchronized int occupy(Device device) throws InterruptedException {
        for (int i=0; i<nConnections; i++) {
            if (!connections[i]) {  // If the value is false which means it is available
                nBusy++;    // Increment the number of connections in use
                device.givenConnection = i+1;
                System.out.println("Connection " + device.givenConnection +
                        ": " + device.getType() + " occupied");
                connections[i] = true;
                Thread.sleep(3000);
                break;
            }
        }
        return device.givenConnection;
    }

    /**
     * Let the device performs an online activity
     * @return String
     * @throws InterruptedException ex
     */
    public String surf() throws InterruptedException {
        Thread.sleep(2000);
        return " performs online activity";
    }

    /**
     * Release the connection, return the value inside the array to false and
     * decrement the number of connections in use
     * @param device device
     * @return String
     */
    public synchronized String release(Device device) throws InterruptedException {
        nBusy--;
        connections[device.givenConnection-1] = false;
        Thread.sleep(5000);
        return " logged out";
    }
}
