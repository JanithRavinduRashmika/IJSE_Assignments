package lk.royalInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.royalInstitute.dao.DAOFactory;
import lk.royalInstitute.dao.DAOType;
import lk.royalInstitute.dao.SuperDAO;
import lk.royalInstitute.dao.custom.QueryDAO;
import lk.royalInstitute.entity.LogIn;
import lk.royalInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LogInFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblLogIn;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private Button btnShowPassword;

    @FXML
    private JFXTextField txtPasswordText;

    @FXML
    private JFXButton btnLogIn;
    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);

    public void initialize(){
        /*Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        LogIn admin = new LogIn(1, "admin", "1234");
        session.save(admin);

        transaction.commit();*/
    }
    @FXML
    void btnLogInOnAction(ActionEvent event) {

        String userName = txtUserName.getText().trim();
        String password = txtPassword.getText().trim();
        queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);
        try {
            if (queryDAO.isThisLogInValid(userName,password)){
                Stage primaryStage = (Stage) this.root.getScene().getWindow();

                Parent root = FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"));
                Scene scene = new Scene(root);

                primaryStage.setScene(scene);
                primaryStage.centerOnScreen();
            }else{
                new Alert(Alert.AlertType.ERROR,"User Name or Password incorrect").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void btnShowPasswordOnMousePressed(MouseEvent event) {
        String password = txtPassword.getText().trim();
        txtPassword.setOpacity(0);
        txtPasswordText.setOpacity(1);
        txtPasswordText.setText(password);
    }

    @FXML
    void btnShowPasswordOnMouseReleased(MouseEvent event) {
        txtPasswordText.setOpacity(0);
        txtPassword.setOpacity(1);
    }
}
