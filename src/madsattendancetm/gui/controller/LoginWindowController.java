/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package madsattendancetm.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import madsattendancetm.be.User;
import madsattendancetm.gui.model.Model;


public class LoginWindowController implements Initializable {
    
    private Model model = new Model();
    
    @FXML private JFXTextField txtEmail;
    @FXML private JFXPasswordField txtPassword;
    @FXML private JFXButton btnLogin;
    @FXML private Text txt;
    
    private File file = new File("..\\madsattendancetm\\currentuser.txt");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            model.unattendance(date());
        } catch (SQLException ex) {
            Logger.getLogger(LoginWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
    private String date() {
        Date date1 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date1);
    }
    
    @FXML
    private void clickLogin(ActionEvent event) throws SQLException, IOException {
            if ((model.studentLogon(txtEmail.getText(), txtPassword.getText()))==1)
            {
                byte[] strToBytes = txtEmail.getText().getBytes();
                Files.write(file.toPath(), strToBytes);
                model.login(txtEmail.getText(), date());
                Parent root = FXMLLoader.load(getClass().getResource("/madsattendancetm/gui/view/StudentView.fxml"));
                Scene currentScene = btnLogin.getScene();
                Stage currentStage = (Stage) btnLogin.getScene().getWindow();
                currentStage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
            }
            else if ((model.teacherLogon(txtEmail.getText(), txtPassword.getText()))==1)
            {
                File file = new File("..\\madsattendancetm\\currentuser.txt");
                byte[] strToBytes = txtEmail.getText().getBytes();
                Files.write(file.toPath(), strToBytes);
                Parent root = FXMLLoader.load(getClass().getResource("/madsattendancetm/gui/view/TeacherView.fxml"));
                Scene currentScene = btnLogin.getScene();
                Stage currentStage = (Stage) btnLogin.getScene().getWindow();
                currentStage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));
            }
            else
            {
                txt.setText("Invalid username or password");
            }
    }
}
