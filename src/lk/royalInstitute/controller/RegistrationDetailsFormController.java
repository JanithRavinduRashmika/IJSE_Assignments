package lk.royalInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.royalInstitute.business.BOFactory;
import lk.royalInstitute.business.BOType;
import lk.royalInstitute.business.SuperBO;
import lk.royalInstitute.business.custom.CourseBO;
import lk.royalInstitute.dao.DAOFactory;
import lk.royalInstitute.dao.DAOType;
import lk.royalInstitute.dao.SuperDAO;
import lk.royalInstitute.dao.custom.QueryDAO;
import lk.royalInstitute.dto.CourseDTO;
import lk.royalInstitute.entity.CustomEntity;
import lk.royalInstitute.util.FactoryConfiguration;
import lk.royalInstitute.view.tm.CustomEntityTM;

import java.io.IOException;
import java.util.List;

public class RegistrationDetailsFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblRegistrationsDetails;

    @FXML
    private Button btnHome;

    @FXML
    private JFXButton btnSearchCW;

    @FXML
    private JFXButton btnSARegistration;

    @FXML
    private TableView<CustomEntityTM> tblRegistration;

    @FXML
    private TableView<?> tblStudentInAll;

    @FXML
    private JFXComboBox<String> cmbCourse;

    @FXML
    private TableColumn<?, ?> colRegNo;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    CourseBO courseBO = BOFactory.getInstance().getBO(BOType.COURSE);


    public void initialize(){
        loadCourses();

        colRegNo.setCellValueFactory(new PropertyValueFactory<>("regNO"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));


    }

    private void loadCourses() {
        
        ObservableList<String> courses = FXCollections.observableArrayList();
        try {
            List<CourseDTO> allCourses = courseBO.getAllCourses();
            for (CourseDTO courseDTO : allCourses) {
                courses.add(courseDTO.getCourseName());
            }
            cmbCourse.setItems(courses);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
    void btnSARegistrationOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchCWOnAction(ActionEvent event) {
        Object value = cmbCourse.getValue();
        if (value != null){
            String course = value.toString();
            try {
                String courseID = courseBO.getCourseID(course);
                loadStudents(courseID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadStudents(String courseID) throws Exception {
        ObservableList<CustomEntityTM> customEntityTMs = FXCollections.observableArrayList();
        QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);
        List<CustomEntity> studentDetails = queryDAO.getStudentDetails(courseID);
        for (CustomEntity customEntity : studentDetails) {
            customEntityTMs.add(new CustomEntityTM(customEntity.getStudentId(),
                    customEntity.getStudentName(),
                    customEntity.getRegNo()));
        }
        tblRegistration.setItems(customEntityTMs);
    }

}
