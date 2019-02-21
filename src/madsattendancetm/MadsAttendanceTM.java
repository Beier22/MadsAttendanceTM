/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package madsattendancetm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author mads_
 */
public class MadsAttendanceTM extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/madsattendancetm/gui/view/LoginWindow.fxml"));
        
        Scene scene = new Scene(root);
        
        //stage.initStyle(StageStyle.UNDECORATED);
        
        Image logo = new Image("/madsattendancetm/gui/images/Icon.png");
        stage.getIcons().add(logo);
        stage.setHeight(600);
        stage.setWidth(800);
        stage.setMinHeight(600);
        stage.setMinWidth(800);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
