/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package madsattendancetm.gui.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import madsattendancetm.be.User;
import madsattendancetm.dal.UserDAO;
import madsattendancetm.gui.model.Model;

/**
 * FXML Controller class
 *
 * @author alex
 */
public class TeacherViewController implements Initializable {
    
    private UserDAO userdao = new UserDAO();
    private Model model = new Model();
    
    @FXML private JFXComboBox<User> cbxClassList;
    @FXML private Label lblTeacherName;
    @FXML private JFXListView<User> lstStudents;
    @FXML private Button btnBack;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        for (User u : userdao.getAllUsers()) {
            if(u.getIsTeacher() == 0)
                lstStudents.getItems().add(u);
        }
        
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
