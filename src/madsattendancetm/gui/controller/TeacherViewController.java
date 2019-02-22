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
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
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

    }
    
    private void studentSummary()
    {
        lstStudents.getItems().clear();
        btnMoreInfo.setText("More");
        try {
            ObservableList<String> obsList = FXCollections.observableArrayList();
            List<User> userList;
            userList = model.getAllUsers();
            for (User user : userList) {
                if (user.getIsTeacher()==0)
                {
                    obsList.add(user.getEmail());
                }
            }
            lstStudents.getItems().addAll(obsList);
        } catch (SQLException ex) {
            Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void summarizedAttendance()
    {
        lstStudents.getItems().clear();
        btnMoreInfo.setText("More");
        HashMap<String, Integer> map = model.summarizedAttendance();
        ObservableList<String> showList = FXCollections.observableArrayList();
 
        List<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(map.entrySet()); 
  
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 

        HashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            sortedMap.put(aa.getKey(), aa.getValue()); 
        } 

        List<String> students = new ArrayList<>(sortedMap.keySet());
        List<Integer> attendance = new ArrayList<>(sortedMap.values());
        
        Collections.reverse(students);
        Collections.reverse(attendance);
        
        for (int i = 0; i<students.size(); i++) {
            showList.add(students.get(i)+"       "+attendance.get(i));
        }
        lstStudents.getItems().add("Summarized attendance ranked from highest absence");
        lstStudents.getItems().addAll(showList);

    }
    

    private void setValues(String date) {
        lstStudents.getItems().clear();
        btnMoreInfo.setText("More");
        HashMap<String, Integer> yada = model.att(date);
        
        List<String> students = new ArrayList<>(yada.keySet());
        List<Integer> attend = new ArrayList<>(yada.values());
        
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
    
    private void studentRequests()
    {
        lstStudents.getItems().clear();
        btnMoreInfo.setText("Accomodate request");
        ObservableList<String> obsList = FXCollections.observableArrayList();
        obsList.addAll(model.studentRequests());
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
        if (menu.getSelectionModel().isSelected(2))
        {
            studentSummary();
        }
        else
        {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/madsattendancetm/gui/view/LoginWindow.fxml"));
                Scene currentScene = btnBack.getScene();
                Stage currentStage = (Stage) btnBack.getScene().getWindow();
                currentStage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
            } catch (IOException ex) {
                Logger.getLogger(StudentViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void handleMoreInfo(MouseEvent event) {
        if (menu.getSelectionModel().isSelected(2))
        {
            String weekday = null;
            String student = lstStudents.getSelectionModel().getSelectedItem();
            lstStudents.getItems().clear();
            ObservableList<String> obsList = FXCollections.observableArrayList();
            List<Integer> list = model.studentSummary(student);

            for (int i = 0; i<6; i++)
            {
                if (i == 0) weekday = "Sunday";
                if (i == 1) weekday = "Monday";
                if (i == 2) weekday = "Tuesday";
                if (i == 3) weekday = "Wednesday";
                if (i == 4) weekday = "Thursday";
                if (i == 5) weekday = "Friday";
                if (i == 6) weekday = "Saturday";


                obsList.add(weekday+"       "+list.get(i));
            }
            lstStudents.getItems().add("Total absence for each week day");
            lstStudents.getItems().addAll(obsList);
        }
        
        
        
    }

    @FXML
    private void pickDate(ActionEvent event) {
        if(menu.getSelectionModel().isSelected(0))
        {
            setValues(datePicker.getValue().toString());
        }
    }

    @FXML
    private void handleMenu(ActionEvent event) {
        if(menu.getSelectionModel().isSelected(0))
        {
            setValues(date());
        }
        else if(menu.getSelectionModel().isSelected(1))
        {
            summarizedAttendance();
        }
        else if(menu.getSelectionModel().isSelected(2))
        {
            studentSummary();
        }
        else if(menu.getSelectionModel().isSelected(3))
        {
            studentRequests();
        }
        else
        {
            lstStudents.getItems().clear();
        }
    }

    @FXML
    private void btnMore(ActionEvent event) {
        if(menu.getSelectionModel().isSelected(3))
        {
            lstStudents.getItems().remove(lstStudents.getSelectionModel().getSelectedItem());
        }
    }


    
}
