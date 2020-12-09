public class Semaphore {
    public static int count;

    protected Semaphore() {
        count = 0;}
    protected Semaphore(int count) {
        Semaphore.count = count;
    }

    /** Decrement the semaphore value. If negative, suspend the process */
    public synchronized void P(Device device) {
        count--;
        if (count < 0)
        {
            try {
                System.out.println("(" + device.getName() + ") ("+
                        device.getType() + ")" + " arrived and waiting");
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        else
            System.out.println("(" + device.getName() + ") ("+
                    device.getType() + ")" + " arrived");
    }

    /** Increment the semaphore value, allow the first process
     * in the waiting queue to continue.
     */
    public synchronized void V()
    {
        count++;
        if (count <= 0)
            notify();
    }
}
