public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
        ServerLogin serverLogin = new ServerLogin();
        serverLogin.start();
    }
}
