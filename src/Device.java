public class Device extends Thread {
    public static Routeur routeur;
    public int givenConnection;
    public String type;

    public Device(String name, String type, Routeur routeur)
    {
        super(name);
        this.type = type;
        Device.routeur = routeur;
    }

    /**
     * @return the type of the device
     */
    public String getType() {
        return type;
    }

    public void run() {
        Routeur.connection.P(this);
        try {
            givenConnection=routeur.occupy(this);
            System.out.println("Connection " + givenConnection +
                    ": " + this.getName() + routeur.surf());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        try {
            System.out.println("Connection " + givenConnection + ": " +
                    this.getName() + routeur.release(this));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Routeur.connection.V();
    }
}
