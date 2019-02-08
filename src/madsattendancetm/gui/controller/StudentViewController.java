/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package madsattendancetm.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import madsattendancetm.gui.model.Model;

/**
 * FXML Controller class
 *
 * @author alex
 */
public class StudentViewController implements Initializable {

    LoginWindowController loginpage = new LoginWindowController();
    Model model = new Model();
    
    String email = loginpage.email;
    String pass = loginpage.pass;
    
    @FXML
    private PieChart pie;

    

            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        set();
    }    
    
    public int attendanceData(String email) {
        return model.attendanceData(email);
    }
    
    public int absenseData(String email){
        return model.absenseData(email);
    }
    
    public void set() {
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("attendance", attendanceData(this.email)),
            new PieChart.Data("absense", absenseData(this.email)));
    pie.setData(pieChartData);
    }
    
}
