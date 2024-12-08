package lab_08;

public class Bob extends Communication{
    private int privatekey = 5;
    private int serverport = 6000;
    private int clientport = 5000;
    private int publickey[];

    public Bob(int serverport, int clientport) {
        super(serverport, clientport);
        this.serverport = serverport;
        this.clientport = clientport;
        this.publickey = getPublicKeyNG();
    }

    public static void main(String[] args) {

    }

    public int mixWithPrivate(int a, int b) {
        return (int) Math.pow(a, b) % publickey[0];
    }

    public int generatePublicKey() {
        return mixWithPrivate(publickey[1], privatekey);
    }
}
