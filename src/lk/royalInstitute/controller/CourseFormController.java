package lk.royalInstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import lk.royalInstitute.business.custom.CourseBO;
import lk.royalInstitute.dao.DAOFactory;
import lk.royalInstitute.dao.DAOType;
import lk.royalInstitute.dao.SuperDAO;
import lk.royalInstitute.dao.custom.CourseDAO;
import lk.royalInstitute.dto.CourseDTO;
import lk.royalInstitute.view.tm.CourseTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CourseFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private Button btnHome;

    @FXML
    private JFXButton btnNewCourse;

    @FXML
    private Label lblManageCourses;

    @FXML
    private JFXTextField txtCourseID;

    @FXML
    private JFXTextField txtCourseName;

    @FXML
    private JFXTextField txtCourseDuration;

    @FXML
    private JFXTextField txtCourseFee;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<CourseTM> tblCourse;

    @FXML
    private TableColumn<?, ?> clmID;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmDuration;

    @FXML
    private TableColumn<?, ?> clmFee;

    private CourseBO courseBO = BOFactory.getInstance().getBO(BOType.COURSE);
    private static int nextID;

    public void initialize(){
        clmID.setCellValueFactory(new PropertyValueFactory<>("code"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        clmDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        clmFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));

        tblCourse.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        if (newValue != null){
                            setData(newValue);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        loadAllCourses();
    }



    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Optional<ButtonType> result = new Alert(Alert.AlertType.WARNING, "Are you sure?",ButtonType.YES,
                ButtonType.NO).showAndWait();
        if (result.get() == ButtonType.YES){
            try {
                boolean b = courseBO.deleteCourse(txtCourseID.getText().trim());
                if (b){
                    new Alert(Alert.AlertType.CONFIRMATION,"Delete Successful").showAndWait();
                    clear();
                    disableFields();
                    setButtonState(false,true,true,true);
                    loadAllCourses();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @FXML
    void btnNewCourseOnAction(ActionEvent event) {
        clear();
        enableFields();
        setButtonState(true,false,true,true);
        loadNextId();

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        CourseDTO data = getData();
        try {
            boolean b = courseBO.addCourse(data);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Course added successful").showAndWait();
                clear();
                disableFields();
                setButtonState(false,true,true,true);
                loadAllCourses();
            }else{
                new Alert(Alert.AlertType.ERROR,"Course added unsuccessful").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        CourseDTO data = getData();
        try {
            boolean b = courseBO.updateCourse(data);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Update Successful").showAndWait();
                clear();
                disableFields();
                setButtonState(false,true,true,true);
                loadAllCourses();
            }else{
                new Alert(Alert.AlertType.ERROR,"Update Unsuccessful").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clear(){
        txtCourseID.clear();
        txtCourseName.clear();
        txtCourseDuration.clear();
        txtCourseFee.clear();
    }

    private void enableFields(){
        txtCourseID.setDisable(false);
        txtCourseName.setDisable(false);
        txtCourseDuration.setDisable(false);
        txtCourseFee.setDisable(false);
    }

    private void disableFields(){
        txtCourseID.setDisable(true);
        txtCourseName.setDisable(true);
        txtCourseDuration.setDisable(true);
        txtCourseFee.setDisable(true);
    }

    private void setButtonState(boolean newCourse, boolean save, boolean update, boolean delete){
        btnNewCourse.setDisable(newCourse);
        btnSave.setDisable(save);
        btnUpdate.setDisable(update);
        btnDelete.setDisable(delete);
    }

    private CourseDTO getData(){
        return new CourseDTO(txtCourseID.getText().trim(),
                txtCourseName.getText().trim(),
                Double.parseDouble(txtCourseFee.getText().trim()),
                txtCourseDuration.getText().trim());
    }

    private void loadAllCourses(){
        ObservableList<CourseTM> courseTMS = FXCollections.observableArrayList();
        try {
            List<CourseDTO> allCourses = courseBO.getAllCourses();
            for (CourseDTO courseDTO : allCourses) {
                courseTMS.add(new CourseTM(courseDTO.getCode(),
                        courseDTO.getCourseName(),
                        courseDTO.getCourseFee(),
                        courseDTO.getDuration()));
            }
            tblCourse.setItems(courseTMS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setData(CourseTM newValue) {
        txtCourseID.setText(newValue.getCode());
        txtCourseName.setText(newValue.getCourseName());
        txtCourseDuration.setText(newValue.getDuration());
        txtCourseFee.setText(String.valueOf(newValue.getCourseFee()));
        enableFields();
        setButtonState(false,true,false,false);
    }

    private void loadNextId() {
        if (nextID == 0){
            try {
                String id = courseBO.getNextId();
                txtCourseID.setText(id);
                int i = Integer.parseInt(id.substring(2, 6));
                nextID=i;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            nextID++;
            txtCourseID.setText("CT"+nextID);
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

}
