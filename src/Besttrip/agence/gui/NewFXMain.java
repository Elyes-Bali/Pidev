/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author amadd
 */
public class NewFXMain extends Application {
    
 

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Reservation backend
     
        //Reservation Transport
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/gui/Transport.fxml"));
        //Reservation Transport + reservation backend
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();

        
        Scene scene = new Scene(root);

        
        primaryStage.setScene(scene);

       
        primaryStage.setTitle("Transport Application");

        
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
