/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package madsattendancetm.gui.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import madsattendancetm.be.User;

/**
 * FXML Controller class
 *
 * @author alex
 */
public class TeacherViewController implements Initializable {

    @FXML
    private JFXComboBox<User> cbxClassList;
    @FXML
    private Label lblTeacherName;
    @FXML
    private JFXListView<User> lstStudents;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<User> students = null;
        //for (Object object : col) {
            
        //}
    }    
    
    
    
}
