/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package madsattendancetm.gui.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import madsattendancetm.be.User;
import madsattendancetm.gui.model.Model;

/**
 * FXML Controller class
 *
 * @author alex
 */
public class TeacherViewController implements Initializable {
    
    private Model model = new Model();
    
    @FXML private JFXComboBox<User> cbxClassList;
    @FXML private Label lblTeacherName;
    @FXML private TableView<User> lstStudents;
    @FXML private Button btnBack;
    @FXML private JFXDatePicker datePicker;
    @FXML
    private TableColumn<User, String> colName;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        colName.setCellValueFactory(cell->cell.getValue().nameProperty());
                
        try {
            ArrayList<User> allStudents = null;
            for (User u : model.getAllUsers()) {
                if(u.getIsTeacher() == 0)
                    lstStudents.getItems().add(u);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
       	LocalDate localDate = LocalDate.now();
        datePicker.setValue(localDate);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(dtf.format(datePicker.getValue()));
        //setAttendance("2019-02-15");
        //lstStudents.getSelectionModel().select(0);
        System.out.println(model.attendanceDay("alex@uldahl.dk", "2019-02-15"));*/
    }    

    @FXML
    private void handleBtnBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/madsattendancetm/gui/view/LoginWindow.fxml"));
            Scene currentScene = btnBack.getScene();
            Stage currentStage = (Stage) btnBack.getScene().getWindow();
            currentStage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(StudentViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setAttendance(String date)
    {
        for (int i = 0; i < lstStudents.getItems().size() ; i++) {
            /*lstStudents.getSelectionModel().select(i);
            User temp = lstStudents.getSelectionModel().getSelectedItem();
            lstStudents.setStyle(model.attendanceDay(temp.getEmail(), date) == true ? "-fx-background-color: green;" : "-fx-background-color: black;");
            if(model.attendanceDay(temp.getEmail(), date) == true){
                lstStudents.getSelectionModel().getSelectedIndex();
            }*/
        }
    }
    
    
    
}
