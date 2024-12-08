package lab_08;

public class World {

    int alicePublic;
    int bobPublic;
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Alice alice = new Alice(5000,6000);
        Bob bob = new Bob(6000,5000);
        
        //generate Alices public Key
        int alicePublic = alice.generatePublicKey();
        int bobPublic = bob.generatePublicKey();
        //send public keys to each other
        // Create threads for each send/receive operation
        Thread aliceThread = new Thread(() -> {
            alice.sendMessage(alicePublic + "");
            String receivedMessageAlice = alice.getMessage();
            System.out.println("Alice received: " + receivedMessageAlice);
        });
        Thread bobThread = new Thread(() -> {
            String receivedMessageBob = bob.getMessage();
            System.out.println("Bob received: " + receivedMessageBob);
            bob.sendMessage(bobPublic + "");
        });
        bobThread.start();
        aliceThread.start();
        try {
            aliceThread.join();
            bobThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e);
        }

        
    }
    
}
