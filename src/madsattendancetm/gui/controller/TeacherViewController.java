/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package madsattendancetm.gui.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import madsattendancetm.be.User;
import madsattendancetm.dal.UserDAO;
import madsattendancetm.gui.model.Model;

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
    
    UserDAO userdao = new UserDAO();
    Model model = new Model();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        for (User u : userdao.getAllUsers()) {
            if(u.getIsTeacher() == 0)
                lstStudents.getItems().add(u);
        }
        
    }    
    
    
    
}
