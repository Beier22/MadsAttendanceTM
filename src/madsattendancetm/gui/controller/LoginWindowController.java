/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package madsattendancetm.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import madsattendancetm.be.User;
import madsattendancetm.gui.model.Model;

/**
 *
 * @author mads_
 */
public class LoginWindowController implements Initializable {
    
    Model model = new Model();
    List<User> userList;
    String email;
    String pass;
    
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    try {
        userList = model.getAllUsers();
            for (User user : userList) 
                if (user.getIsTeacher()==0)
                    model.unattendance(user.getEmail(), date(), user.getEmail(), date());
        } catch (SQLException ex) 
        {
            Logger.getLogger(LoginWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }    
    
    
    
    private String date() {
        Date date1 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        return dateFormat.format(date1);
    }
    
    @FXML
    private void clickLogin(ActionEvent event) throws SQLException {
    try {
        for (User user : userList) 
        {
            if (user.getEmail().equals(txtEmail.getText()) && user.getPassword().equals(txtPassword.getText()) && user.getIsTeacher()==0)
            {
                email = txtEmail.getText();
                pass = txtPassword.getText();
                model.login(txtEmail.getText(), date());
                Parent root = FXMLLoader.load(getClass().getResource("/madsattendancetm/gui/view/StudentView.fxml"));
                Scene currentScene = btnLogin.getScene();
                Stage currentStage = (Stage) btnLogin.getScene().getWindow();
                currentStage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
            }
            else if (user.getEmail().equals(txtEmail.getText()) && user.getPassword().equals(txtPassword.getText()) && user.getIsTeacher()==1)
            {
                email = txtEmail.getText();
                pass = txtPassword.getText();
                Parent root = FXMLLoader.load(getClass().getResource("/madsattendancetm/gui/view/TeacherView.fxml"));
                Scene currentScene = btnLogin.getScene();
                Stage currentStage = (Stage) btnLogin.getScene().getWindow();
                currentStage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
            }    
        }
    }   catch(IOException e) {
        System.out.println("IOException occured"); }
    }
}
