/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package madsattendancetm.gui.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import madsattendancetm.be.User;
import madsattendancetm.gui.model.Model;


public class StudentViewController implements Initializable {

    private Model model = new Model();
    private String email;
    private FileReader fr = null;
    private File f = new File("C:\\Users\\alex\\Downloads\\2018-SCO1-Examples-from-class-master\\CodingBatProjects\\MadsAttendanceTM\\src\\madsattendancetm\\currentUser.txt");
    
    @FXML private PieChart pie;
    @FXML private Button btnBack;
    @FXML private Text txtWelcome;
    @FXML private ListView<String> listView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine())!=null) email = line;
            fr.close();
        } catch (IOException ex) {
            Logger.getLogger(StudentViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtWelcome.setText("Welcome, you are now attendant");
        set();
        List<String> days = FXCollections.observableArrayList(absenceData(email));
        Collections.reverse(days);
        listView.setItems(FXCollections.observableArrayList(days));
    }    

    private List attendanceData(String email) {
        List<String> days = model.attendanceData(email);

        return days;
    }
    
    private List absenceData(String email){
        List<String> days = model.absenceData(email);

        return days;
    }
    
    private void set() {
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("attendance", attendanceData(email).size()),
            new PieChart.Data("absence", absenceData(email).size()));
            pie.setData(pieChartData);
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
    

}
