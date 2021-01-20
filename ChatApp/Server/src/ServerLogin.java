import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerLogin extends Thread{

    @Override
    public void run() {
        ServerSocket serverSocket;
        Socket socket;
        try {
            serverSocket = new ServerSocket(5052);
            while(true) {

                socket = serverSocket.accept();
                LogInHandler clientThread = new LogInHandler(socket);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}