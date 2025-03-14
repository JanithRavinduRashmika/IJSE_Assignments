import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientChatFormController<pressEvent> extends Thread {

    @FXML
    private TextArea txtArea;

    @FXML
    private Label lblUserName;

    @FXML
    private TextField txtMsg;

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String userName;

    public ClientChatFormController() throws IOException {
    }


    public void initialize(){
        userName = LogInFormController.userName;
        lblUserName.setText(userName);
        connectServer();
        sendUserName();
        getOnlineUsers();


    }

    private void sendUserName() {
        sendToServer(userName);
    }

    private void getOnlineUsers() {
        String msg = "onlineUsers " + userName;
        sendToServer(msg);
    }

    private void connectServer() {
        try {
            this.socket = new Socket("localhost", 5051);
            System.out.println("Socket is connected with server!");
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = reader.readLine();
                if (msg.charAt(0) == '#'){
                    showOnlineUsers(msg);
                }else{
                    String[] tokens = msg.split("\\s+",3);
                    String cmd = tokens[0];
                    String sender = tokens[1];
                    String text = tokens[2];

                    if (sender.equals(userName)){
                        continue;
                    }else{
                        if (cmd.equals("offline")) {
                            txtArea.appendText("OFFLINE : "+tokens[1].trim()+"\n");
                        }else if(cmd.equals("msg")){
                            txtArea.appendText(sender.toUpperCase()+ " :  " + text+"\n");
                        }
                    }


                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showOnlineUsers(String msg) {
        String me = "# "+userName;
        if (!msg.equals(me)){
            if (msg.equals("no")){
                txtArea.appendText("There are no other users are online");
            }else{
                String[] s = msg.split("\\s+",2);
                String onlineUser = s[1];
                txtArea.appendText("online : "+onlineUser+"\n");
            }
        }



    }

    @FXML
    void btnSendOnAction(ActionEvent event) {
        String msg = txtMsg.getText().trim();
        String sendMsg = "msg "+userName+" "+msg;
        sendToServer(sendMsg);
        txtArea.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        txtArea.appendText("Me :  " + msg + "\n");
        txtMsg.setText("");
    }

    public void sendToServer(String msg){
        writer.println(msg);
    }

    @FXML
    void lblCloseOnClickedAction(MouseEvent event) throws IOException {
        String msg = "exits "+userName+" "+"a";
        sendToServer(msg);

       /* writer.close();
        reader.close();
        socket.close();*/
        Stage stage = (Stage) txtArea.getScene().getWindow();
        stage.close();
    }

    @FXML
    void lblMinimizeOnClickedAction(MouseEvent event) {
        Stage stage = (Stage) txtArea.getScene().getWindow();
        stage.setIconified(true);
    }







}
