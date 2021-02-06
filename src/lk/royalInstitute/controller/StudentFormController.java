package lk.royalInstitute.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.royalInstitute.business.BOFactory;
import lk.royalInstitute.business.BOType;
import lk.royalInstitute.business.SuperBO;
import lk.royalInstitute.business.custom.StudentBO;
import lk.royalInstitute.dto.StudentDTO;

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


    StudentBO studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);

    public void initialize(){
        try {
            lblStudentID.setText(studentBO.getNextID());
            System.out.println(lblStudentID.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        StudentDTO studentData = getStudentData();
        try {
            boolean b = studentBO.addStudent(studentData);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Student added Successful").showAndWait();
                clear();
            }else{
                new Alert(Alert.AlertType.ERROR,"Student added Unsuccessful").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    private StudentDTO getStudentData(){
        String id = lblStudentID.getText().trim();
        String name = txtStudentName.getText().trim();
        String address = txtStudentAddress.getText().trim();
        String contact = txtStudentContact.getText().trim();
        String dob = dpkDOB.getValue().toString().trim();
        String gender = txtGender.getText().trim();

        return new StudentDTO(id,name,address,contact,dob,gender);
    }

    private void clear(){
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtStudentContact.clear();
        txtGender.clear();
        dpkDOB.setValue(null);
        try {
            lblStudentID.setText(studentBO.getNextID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
