import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LogInFormController {

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private Label lblLoginError;


    public static String userName;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    private void connectToServerAsUnknown() throws IOException {
        this.socket = new Socket("localhost",5052);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream(), true);

    }

    @FXML
    void btnLogInOnAction(ActionEvent event) throws IOException {
        userName = txtUserName.getText().trim().toUpperCase();
        String password = txtPassword.getText();
        checkLogin(userName,password);

    }

    private void checkLogin(String userName, String password) throws IOException {
        /*if ((userName.equals("janith") && password.equals("janith")) || (userName.equals("ravindu") && password.equals("ravindu")) ){

            //userName and password correct.Load next form.
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("ClientChatForm.fxml"))));
            stage.show();


            Stage stage1= (Stage) txtUserName.getScene().getWindow();
            stage1.close();


        }else{
            lblLoginError.setOpacity(1.0);
        }*/

        connectToServerAsUnknown();
        if (isThisUserExit(userName,password)){
            //userName and password correct.Load next form.
            Stage stage = new Stage();
            //Parent parent = FXMLLoader.load(getClass().getResource("ClientChatForm.fxml"));
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("ClientChatForm.fxml"))));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.UTILITY);


            stage.show();


            Stage stage1= (Stage) txtUserName.getScene().getWindow();
            stage1.close();

        }else{
            lblLoginError.setOpacity(1.0);
        }
    }

    private boolean isThisUserExit(String userName, String password) throws IOException {
        String msg = "logIn "+userName+" "+password;
        writer.println(msg);
        String reply = reader.readLine();
        if (reply.equals("True")){
            socket.close();
            return true;
        }else{
            socket.close();
            return false;
        }
    }


    @FXML
    void txtPasswordClickOnAction(MouseEvent event) {
        lblLoginError.setOpacity(0.0);
    }

    @FXML
    void txtUserNameClickOnAction(MouseEvent event) {
        lblLoginError.setOpacity(0.0);
    }


}
