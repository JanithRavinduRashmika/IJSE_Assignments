package lk.royalInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.royalInstitute.business.BOFactory;
import lk.royalInstitute.business.BOType;
import lk.royalInstitute.business.SuperBO;
import lk.royalInstitute.business.custom.StudentBO;
import lk.royalInstitute.dto.StudentDTO;
import lk.royalInstitute.view.tm.StudentTM;

import java.time.LocalDate;
import java.util.List;

public class StudentFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblManageStudents;

    @FXML
    private Button btnHome;

    @FXML
    private JFXButton btnNewStudent;

    @FXML
    private TableView<StudentTM> tblStudent;

    @FXML
    private TableColumn<?, ?> clmID;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmAddress;

    @FXML
    private TableColumn<?, ?> clmContactNumber;

    @FXML
    private TableColumn<?, ?> clmDOB;

    @FXML
    private TableColumn<?, ?> clmGender;

    @FXML
    private JFXTextField txtStudentID;

    @FXML
    private JFXTextField txtStudentName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContactNumber;

    @FXML
    private JFXTextField txtGender;

    @FXML
    private JFXTextField txtDOB;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXDatePicker dpkDOB;

    @FXML
    private JFXComboBox<String> cmbGender;

    @FXML
    private TextField txtSearch;

    private StudentBO studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);
    private ObservableList<String> genderList = FXCollections.observableArrayList("Male","Female");

    public void initialize(){
        cmbGender.setItems(genderList);
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentName"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));

        loadAllCustomers();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewStudentOnAction(ActionEvent event) {
        loadNextID();
        enableFields();
        btnSave.setDisable(false);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        StudentDTO data = getData();
        try {
            boolean b = studentBO.addStudent(data);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Student Added Successful").showAndWait();
                clear();
                btnSave.setDisable(true);
            }else{
                new Alert(Alert.AlertType.ERROR,"Student Added Unsuccessful").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void dpkDOBOnAction(ActionEvent event) {
        setDOB();
    }

    @FXML
    void cmbGenderOnAction(ActionEvent event) {
        setGender();
    }

    private void loadNextID() {
        try {
            String nextID = studentBO.getNextID();
            txtStudentID.setText(nextID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enableFields(){
        txtStudentID.setDisable(false);
        txtStudentName.setDisable(false);
        txtAddress.setDisable(false);
        txtContactNumber.setDisable(false);
        txtDOB.setDisable(false);
        txtGender.setDisable(false);
        dpkDOB.setDisable(false);
        cmbGender.setDisable(false);
    }

    private void disableFields(){
        txtStudentID.setDisable(true);
        txtStudentName.setDisable(true);
        txtAddress.setDisable(true);
        txtContactNumber.setDisable(true);
        txtDOB.setDisable(true);
        txtGender.setDisable(true);
        dpkDOB.setDisable(true);
        cmbGender.setDisable(true);
    }

    private StudentDTO getData(){
        return new StudentDTO(txtStudentID.getText().trim(),
                txtStudentName.getText().trim(),
                txtAddress.getText().trim(),
                txtContactNumber.getText().trim(),
                txtDOB.getText().trim(),
                txtGender.getText().trim());
    }

    private void clear(){
        txtStudentID.clear();
        txtStudentName.clear();;
        txtAddress.clear();;
        txtContactNumber.clear();
        txtDOB.clear();
        txtGender.clear();
        disableFields();
    }

    private void setDOB(){
        txtDOB.setText(String.valueOf(dpkDOB.getValue()));
    }

    private void setGender(){
        txtGender.setText(cmbGender.getValue());
    }

    private void loadAllCustomers(){
        ObservableList<StudentTM> studentTMS  = FXCollections.observableArrayList();
        List<StudentDTO> allStudents = null;
        try {
            allStudents = studentBO.getAllStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (StudentDTO studentDTO : allStudents) {
            studentTMS.add(new StudentTM(studentDTO.getId(),
                    studentDTO.getStudentName(),
                    studentDTO.getAddress(),
                    studentDTO.getContact(),
                    studentDTO.getDate(),
                    studentDTO.getGender()));
        }
        tblStudent.setItems(studentTMS);
    }
}
