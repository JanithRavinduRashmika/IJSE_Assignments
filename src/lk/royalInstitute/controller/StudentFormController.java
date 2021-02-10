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
import javafx.scene.input.KeyCode;
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
import java.util.regex.Pattern;

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

    private int activeFieldValue = 1;
    private int[] validation = new int[5];
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
        validation();
    }

    private void validation() {
        txtStudentID.setOnKeyReleased(e->{
            KeyCode keyCode = e.getCode();
            if(isEnteredKeyArrowKey(keyCode)){
                traverse(keyCode);
            }

        });

        txtStudentName.setOnKeyReleased(e->{
            KeyCode keyCode = e.getCode();
            if(isEnteredKeyArrowKey(keyCode)){
                traverse(keyCode);
            }else if(!Pattern.compile("^[A-Z ]{1,50}$",Pattern.CASE_INSENSITIVE).matcher(txtStudentName.getText().trim()).matches()){
                txtStudentName.setStyle("-fx-background-color: red");
                validation[0]=0;
            }else{
                txtStudentName.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #002266 0%,#0078D4 50%, #002266 100%)");
                validation[0]=1;
            }

        });

        txtAddress.setOnKeyReleased(e->{
            KeyCode keyCode = e.getCode();
            if(isEnteredKeyArrowKey(keyCode)){
                traverse(keyCode);
            }else if(!Pattern.compile("^[A-Z0-9:/, ]{1,200}$",Pattern.CASE_INSENSITIVE).matcher(txtAddress.getText()).matches()){
                txtStudentName.setStyle("-fx-background-color: red");
                validation[1]=0;
            }else{
                txtStudentName.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #002266 0%,#0078D4 50%, #002266 100%)");
                validation[1]=1;
            }

        });

        txtContactNumber.setOnKeyReleased(e->{
            KeyCode keyCode = e.getCode();
            if(isEnteredKeyArrowKey(keyCode)){
                traverse(keyCode);
            }else if(!Pattern.compile("^[0-9]{10}$").matcher(txtContactNumber.getText().trim()).matches()){
                txtStudentName.setStyle("-fx-background-color: red");
                validation[2]=0;
            }else{
                txtStudentName.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #002266 0%,#0078D4 50%, #002266 100%)");
                validation[2]=1;
            }

        });

        txtDOB.setOnKeyReleased(e->{
            KeyCode keyCode = e.getCode();
            if(isEnteredKeyArrowKey(keyCode)){
                traverse(keyCode);
            }

        });

        txtGender.setOnKeyReleased(e->{
            KeyCode keyCode = e.getCode();
            if(isEnteredKeyArrowKey(keyCode)){
                traverse(keyCode);
            }

        });

    }

    private boolean isEnteredKeyArrowKey(KeyCode keyCode) {
        if (keyCode == KeyCode.UP || keyCode == KeyCode.DOWN || keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT || keyCode == KeyCode.ENTER){
            return true;
        }
        return false;
    }

    private void traverse(KeyCode keyCode) {
        switch (keyCode){
            case UP: activeFieldValue-=2;break;
            case DOWN: activeFieldValue+=2;break;
            case LEFT: activeFieldValue--;break;
            case RIGHT:
            case ENTER:
                activeFieldValue++;break;
            default: break;
        }

        if (activeFieldValue<1){
            activeFieldValue+=6;
        }else if(activeFieldValue>6){
            activeFieldValue-=6;
        }

        switch (activeFieldValue){
            case 1:txtStudentID.requestFocus();break;
            case 2:txtStudentName.requestFocus();break;
            case 3:txtAddress.requestFocus();break;
            case 4:txtContactNumber.requestFocus();break;
            case 5:txtDOB.requestFocus();break;
            case 6:txtGender.requestFocus();break;
            default:break;
        }
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
        activeFieldValue = 2;
        loadNextID();
        enableFields();
        setButtonState(true,false,true,true);
        txtStudentName.requestFocus();

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
