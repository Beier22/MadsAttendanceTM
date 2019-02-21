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
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
        
        txtEmail.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER)
                    txtPassword.requestFocus();
            }
            
        });
        
        txtPassword.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER)
                    btnLogin.fire();
            }
            
        });

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
                
                /*Parent root = FXMLLoader.load(getClass().getResource("/madsattendancetm/gui/view/StudentView.fxml"));
                Scene currentScene = btnLogin.getScene();
                Stage currentStage = (Stage) btnLogin.getScene().getWindow();
                currentStage.setScene(new Scene(root, currentScene.getWidth(), currentScene.getHeight()));*/
                
                //Stage st = (Stage) btnLogin.getScene().getWindow();
                //st.close();
                Stage stage = (Stage) btnLogin.getScene().getWindow();//new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/madsattendancetm/gui/view/StudentView.fxml"));
                stage.setHeight(stage.getHeight());
                stage.setWidth(stage.getWidth());
                stage.setMinHeight(600);
                stage.setMinWidth(800);
                stage.setScene(new Scene(loader.load()));
                //stage.show();
            }
            else if ((model.teacherLogon(txtEmail.getText(), txtPassword.getText()))==1)
            {
                File file = new File("..\\madsattendancetm\\currentuser.txt");
                byte[] strToBytes = txtEmail.getText().getBytes();
                Files.write(file.toPath(), strToBytes);
                
                //Stage st = (Stage) btnLogin.getScene().getWindow();
                //st.close();
                Stage stage = (Stage) btnLogin.getScene().getWindow(); //new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/madsattendancetm/gui/view/TeacherView.fxml"));
                stage.setScene(new Scene(loader.load()));
                stage.setHeight(stage.getHeight());
                stage.setWidth(stage.getWidth());
                stage.setMinHeight(600);
                stage.setMinWidth(800);
                //stage.show();
            }
            else
            {
                txt.setText("Invalid username or password");
            }
    }
}
