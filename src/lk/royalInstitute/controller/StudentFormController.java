package lk.royalInstitute.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudentFormController {

    @FXML
    private JFXTextField txtStudentName;

    @FXML
    private JFXTextField txtStudentAddress;

    @FXML
    private JFXTextField txtStudentContact;

    @FXML
    private JFXDatePicker dpkDOB;

    @FXML
    private JFXTextField txtGender;

    @FXML
    private Label lblStudentID;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
