package lk.royalInstitute.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MainFormController {

    @FXML
    private AnchorPane root;

    public void initialize(){

    }
    @FXML
    void btnCourseManagementOnAction(ActionEvent event) throws IOException {
        loadNextForm("CourseForm.fxml");
    }

    @FXML
    void btnRegistrationOnAction(ActionEvent event) throws IOException {
        loadNextForm("RegistrationForm.fxml");
    }

    @FXML
    void btnStudentManagementOnAction(ActionEvent event) throws IOException {
        loadNextForm("StudentForm.fxml");
    }

    private void loadNextForm(String nextForm) throws IOException {
        Stage primaryStage = (Stage) this.root.getScene().getWindow();

        Parent root = FXMLLoader.load(this.getClass().getResource("../view/"+nextForm));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public void btnRegistrationDetailsOnAction(ActionEvent actionEvent) throws IOException {
        loadNextForm("RegistrationDetailsForm.fxml");
    }
}
