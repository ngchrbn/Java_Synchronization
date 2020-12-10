import java.util.Scanner;

public class Network {

    public static void main(String[] args) {
        int numberOfConnections;
        Scanner input = new Scanner(System.in);
        System.out.println("What is the number of WI-FI connections?");
        numberOfConnections = input.nextInt();
        Routeur routeur = new Routeur(numberOfConnections);

        System.out.println("What is number of devices Clients want to connect?");
        int numberOfDevices = input.nextInt();
        Device[] devices = new Device[numberOfDevices];

        System.out.println("Enter the devices' Type:\n");
        input = new Scanner(System.in);
        for (int i=0; i<numberOfDevices; i++)
        {
            System.out.print("Device " + (i+1) + "'s Type: ");
            String type = input.nextLine();
            devices[i] = new Device(("C" + (i+1)), type, routeur);
        }

        for (Device device: devices)
            device.start();
    }
}
