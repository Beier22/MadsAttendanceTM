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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    
    
    @FXML private JFXComboBox<User> cbxClassList;
    @FXML private ListView<String> lstStudents;
    @FXML private Button btnBack;
    @FXML private Button btnMoreInfo;
    @FXML
    private TextField dateField;
    @FXML
    private Button searchDate;

    @Override
    public void initialize(URL url, ResourceBundle rb){   

        
        /*
        for (User user : obsList) {
            lstStudents.getItems().add(user.toString() + "             ATTENDANT = "+model.attendanceDay(user.toString(), date()));
        }
        */
        Date date1 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateField.setText(date());
        
        setValues(date());
        


        
        /*
        studentNameCol.setCellValueFactory(cell->cell.getValue().nameProperty());
        
        emailCol.setCellValueFactory(cell->cell.getValue().emailProperty());
*/
        
        //attendanceCol.setCellValueFactory(cell->cell.getValue().attendanceProperty(email(),dateFormat.format(date1)));

        
        /*
       	LocalDate localDate = LocalDate.now();
        datePicker.setValue(localDate);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(dtf.format(datePicker.getValue()));
        //setAttendance("2019-02-15");
        //lstStudents.getSelectionModel().select(0);
        System.out.println(model.attendanceDay("alex@uldahl.dk", "2019-02-15"));*/
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
        Date date1 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date1);
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
    
    /*
    
    private String email() {
        TablePosition pos = lstStudents.getSelectionModel().getSelectedCells().get(0);
        emailCol = pos.getTableColumn();
        String data = (String)emailCol.getCellObservableValue(3).toString();
        String ret = data.substring(data.indexOf("[value: ")+8,data.indexOf("]"));
        return ret;
    }

    @FXML
    private void handleyada(ActionEvent event) {
                TablePosition pos = lstStudents.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        emailCol = pos.getTableColumn();
        String data = (String) emailCol.getCellObservableValue(row).toString();

        int k = data.indexOf(": ");
        //String ret = data.substring(k+2,data.length()-1);
        System.out.println(data);
        
        String ret = data.substring(data.indexOf("[value: ")+8,data.indexOf("]"));
        Date date1 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(ret);
        System.out.println(model.attendanceDay(ret, dateFormat.format(date1)));
    }
    
    */

    @FXML
    private void handleyada(ActionEvent event) {
    }

    @FXML
    private void handleSearchDate(ActionEvent event) {
        setValues(dateField.getText());
    }




}
