/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package madsattendancetm.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
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
    
    @FXML private JFXButton btnBack;
    @FXML private JFXButton btnMoreInfo;

    @FXML private JFXListView<String> lstStudents;
    
    @FXML private JFXComboBox<String> pickTeacher;
    @FXML private JFXComboBox<String> menu;
    @FXML private JFXDatePicker datePicker;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){   
        
        menu.getItems().addAll("Class attendance", "Summarized attendance", "Student summary", "Student requests");
        menu.setValue("Class attendance");
        
        
        
        if(menu.getSelectionModel().isSelected(0))
        {
            setValues(date());
        }
        
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        System.out.println(dayOfWeek-1);
        
        
    }
    

    private void setValues(String date) {
        HashMap<String, Integer> yada = model.att(date);
        
        List<String> students = new ArrayList<String>(yada.keySet());
        List<Integer> attend = new ArrayList<Integer>(yada.values());
        
        ObservableList<String> obsList = FXCollections.observableArrayList();
        for (int i = 0; i<students.size(); i++) 
        {
            String st = "Absent";
            if (attend.get(i).equals(1))
            {
                st = "Present";
            }
            obsList.add(students.get(i)+"   ATTENDANCE:    "+st);
        }

        lstStudents.getItems().addAll(obsList);
    }
    
    private String date() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        localDate.format(dateFormat);
        datePicker.setValue(localDate);
        return localDate.toString();
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

    @FXML
    private void handleBtnMoreInfo(ActionEvent event) {
    }

    @FXML
    private void pickDate(ActionEvent event) {
        if(menu.getSelectionModel().isSelected(0))
        {
            lstStudents.getItems().clear();
            setValues(datePicker.getValue().toString());
        }
    }

    @FXML
    private void handleMenu(ActionEvent event) {
        if(menu.getSelectionModel().isSelected(0))
        {
            setValues(date());
        }
        else
        {
            lstStudents.getItems().clear();
        }
    }
    
}
