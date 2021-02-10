package lk.royalInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.royalInstitute.business.BOFactory;
import lk.royalInstitute.business.BOType;
import lk.royalInstitute.business.SuperBO;
import lk.royalInstitute.business.custom.StudentBO;
import lk.royalInstitute.dto.StudentDTO;
import lk.royalInstitute.view.tm.StudentTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        clmID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmContactNumber.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clmDOB.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));


        tblStudent.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        if (newValue != null){
                            setData(newValue);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });


        loadAllCustomers();
    }



    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Optional<ButtonType> result = new Alert(Alert.AlertType.WARNING, "Are you want to delete this recode",
                ButtonType.YES, ButtonType.NO).showAndWait();
        if (result.get() == ButtonType.YES){
            try {
                boolean b = studentBO.deleteStudent(txtStudentID.getText().trim());
                if (b){
                    new Alert(Alert.AlertType.CONFIRMATION,"Delete Successful").showAndWait();
                    clear();
                    disableFields();
                    loadAllCustomers();
                    setButtonState(false,true,true,true);
                }else{
                    new Alert(Alert.AlertType.ERROR,"Delete Unsuccessful").showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnNewStudentOnAction(ActionEvent event) {
        clear();
        loadNextID();
        enableFields();
        setButtonState(true,false,true,true);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        StudentDTO data = getData();
        try {
            boolean b = studentBO.addStudent(data);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Student Added Successful").showAndWait();
                clear();
                disableFields();
                loadAllCustomers();
                setButtonState(false,true,true,true);
            }else{
                new Alert(Alert.AlertType.ERROR,"Student Added Unsuccessful").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        StudentDTO data = getData();
        try {
            boolean b = studentBO.updateStudent(data);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Update Successful").showAndWait();
                clear();
                loadAllCustomers();
                disableFields();
                setButtonState(false,true,true,true);
            }else{
                new Alert(Alert.AlertType.ERROR,"Update Unsuccessful").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void setData(StudentTM newValue) {
        txtStudentID.setText(newValue.getId());
        txtStudentName.setText(newValue.getStudentName());
        txtAddress.setText(newValue.getAddress());
        txtContactNumber.setText(newValue.getContact());
        txtDOB.setText(newValue.getDate());
        txtGender.setText(newValue.getGender());
        enableFields();
        setButtonState(false,true,false,false);
    }

    private void setButtonState(boolean newStudent, boolean save, boolean update, boolean delete){
        btnNewStudent.setDisable(newStudent);
        btnSave.setDisable(save);
        btnUpdate.setDisable(update);
        btnDelete.setDisable(delete);
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) this.root.getScene().getWindow();

        Parent root = FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }
}
