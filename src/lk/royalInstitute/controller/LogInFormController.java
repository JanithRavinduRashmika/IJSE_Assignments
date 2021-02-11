package lk.royalInstitute.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
