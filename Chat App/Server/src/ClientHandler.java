import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {

    private ArrayList<ClientHandler> clients;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String userName;

    public ClientHandler(Socket socket, ArrayList<ClientHandler> clients) {
        try {
            this.socket = socket;
            this.clients = clients;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            //client send userName first, at every login automatically.
            this.userName = reader.readLine();

            //Send new user's name to other online users
            newClientLogin();

            //handle msg and commands of this client.
            String msg;
            while ((msg = reader.readLine()) != null) {
                String[] tokens = msg.split("\\s+",3);
                String cmd = tokens[0];
                String sender = tokens[1];

                //send client msg for every other clients
                if (cmd.equals("msg")){
                    for (ClientHandler cl : clients) {
                        cl.writer.println(msg);
                    }

                    //send current online users list
                }else if (cmd.equals("onlineUsers")){
                    if (clients.isEmpty()){
                        this.writer.println("no");
                    }else{
                        sendAllOnlineClients();
                    }
                }else if (cmd.equals("exits")){
                    String user = sender;
                    removeUser(user);
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void removeUser(String user) {
        String msg = "offline "+user+" "+"a";
        for (ClientHandler cl:clients) {
            if (cl.userName == user){
                clients.remove(cl);
                System.out.println("remove "+user);
            }else{
                cl.writer.println(msg);
            }

        }
    }

    private void newClientLogin() {
        for (ClientHandler cl:clients) {
            cl.writer.println("# "+userName);
        }
    }

    private void sendAllOnlineClients() {
        for (ClientHandler cl: clients) {
            //String userName = "# "+cl.userName+" "+"a";
            String userName = "# "+cl.userName;
            this.writer.println(userName);
        }
    }


}