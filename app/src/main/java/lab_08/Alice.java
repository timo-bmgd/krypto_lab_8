package lab_08;

public class Alice extends Communication {
    private int privatekey = 5;
    private int serverport = 5000;
    private int clientport = 6000;
    private int publickey[];
    
    public Alice(int serverport, int clientport) {
        super(serverport, clientport);
        this.serverport = serverport;
        this.clientport = clientport;
        this.publickey = getPublicKeyNG();
    }
    
    public int mixWithPrivate(int a, int b) {
        return (int) Math.pow(a, b) % publickey[0];
    }

    public int generatePublicKey() {
        return mixWithPrivate(publickey[1], privatekey);
    }
}
