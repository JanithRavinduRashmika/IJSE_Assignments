package lk.royalInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.royalInstitute.business.BOFactory;
import lk.royalInstitute.business.BOType;
import lk.royalInstitute.business.SuperBO;
import lk.royalInstitute.business.custom.CourseBO;
import lk.royalInstitute.business.custom.RegistrationBO;
import lk.royalInstitute.business.custom.StudentBO;
import lk.royalInstitute.dto.CourseDTO;
import lk.royalInstitute.dto.RegistrationDTO;
import lk.royalInstitute.dto.StudentDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RegistrationFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private Button btnHome;

    @FXML
    private JFXButton btnNewRegistration;

    @FXML
    private Label lblRegistration;

    @FXML
    private JFXTextField txtStudentID;

    @FXML
    private JFXTextField txtCourseID;

    @FXML
    private JFXTextField txtRegistrationID;

    @FXML
    private JFXTextField txtRegistrationFee;

    @FXML
    private Label lblStudentName;

    @FXML
    private Label lblCourseName;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXComboBox<String> cmbCourseID;


    @FXML
    private JFXButton btnCheck;
    StudentBO studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);
    CourseBO courseBO = BOFactory.getInstance().getBO(BOType.COURSE);
    RegistrationBO registrationBO = BOFactory.getInstance().getBO(BOType.REGISTRATION);

    StudentDTO student;
    CourseDTO course;
    public void initialize(){
        loadCourses();
    }

    private void loadCourses() {
        ObservableList<String> courses = FXCollections.observableArrayList();
        try {
            List<CourseDTO> allCourses = courseBO.getAllCourses();
            for (CourseDTO allCourse : allCourses) {
                courses.add(allCourse.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cmbCourseID.setItems(courses);
    }

    @FXML
    void btnCheckOnAction(ActionEvent event) {
        try {
            student = studentBO.getStudent(txtStudentID.getText().trim());
            if (student == null){
                new Alert(Alert.AlertType.ERROR,"Student Doesn't exit.").showAndWait();
            }else{
                course = courseBO.getCourse(txtCourseID.getText().trim());
                if (course == null){
                    new Alert(Alert.AlertType.ERROR,"Course Doesn't exit.").showAndWait();
                }else{
                    Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Is this information matching?\n Student Name:   " +
                            student.getStudentName() + "\n Course     :" + course.getCourseName(), ButtonType.YES, ButtonType.NO).showAndWait();
                    if (result.get() == ButtonType.YES){
                        lblStudentName.setText("Student Name :  "+student.getStudentName());
                        lblCourseName.setText("Course :  "+course.getCourseName());

                        lblStudentName.setOpacity(1.0);
                        lblCourseName.setOpacity(1.0);

                        txtStudentID.setEditable(false);
                        cmbCourseID.setEditable(false);
                        setFieldState(false,false,false,false);
                        txtRegistrationID.setEditable(true);
                        txtRegistrationFee.setEditable(true);
                        setButtonState(false,false,true);
                        loadNextRegistrationID();
                    }
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Student Doesnt exits.").showAndWait();
        }

    }

    private void loadNextRegistrationID() {
        try {
            txtRegistrationID.setText(registrationBO.getNextID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnNewRegistrationOnAction(ActionEvent event) {
        clear();
        student = null;
        course = null;
        setButtonState(true,true,false);
        setFieldState(false,false,true,true);
        txtStudentID.setEditable(true);
        cmbCourseID.setDisable(false);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        RegistrationDTO registrationDTO = new RegistrationDTO(Integer.parseInt(txtRegistrationID.getText().trim()),
                LocalDate.now().toString(),
                Double.parseDouble(txtRegistrationFee.getText().trim()),
                txtStudentID.getText().trim(),
                txtCourseID.getText().trim());

        try {
            boolean b = registrationBO.addRegistration(registrationDTO);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Registration Successful").showAndWait();
                clear();
                setFieldState(true,true,true,true);
                setButtonState(false,true,true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clear(){
        txtStudentID.clear();
        txtCourseID.clear();
        txtRegistrationID.clear();
        txtRegistrationFee.clear();
        lblCourseName.setText(null);
        lblStudentName.setText(null);
        cmbCourseID.setValue(null);
        cmbCourseID.setDisable(true);
    }

    private void setButtonState(boolean newRegistration, boolean save, boolean check){
        btnNewRegistration.setDisable(newRegistration);
        btnSave.setDisable(save);
        btnCheck.setDisable(check);
    }

    private void setFieldState(boolean studentId, boolean courseId, boolean regId, boolean regFee){
        txtStudentID.setDisable(studentId);
        txtCourseID.setDisable(courseId);
        txtRegistrationID.setDisable(regId);
        txtRegistrationFee.setDisable(regFee);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) this.root.getScene().getWindow();

        Parent root = FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    @FXML
    void cmbCourseIDOnAction(ActionEvent event) {
        txtCourseID.setText(cmbCourseID.getValue().toString());
        cmbCourseID.setValue(null);
    }
}
