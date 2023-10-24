/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Maroua SANDI
 */
public class NewFXMain extends Application {
    
    
      private double x = 0 ;
    private double y = 0;
    @Override
     public void start(Stage primaryStage) {
       Parent root ;
       try{
       root = FXMLLoader.load(getClass().
               getResource("Login.fxml"));
       Scene scene = new Scene(root);
       primaryStage.setTitle("User");
       primaryStage.setScene(scene);
       primaryStage.show();
      
       
       }catch(IOException ex){
       System.out.println(ex.getMessage());
       }
    

        

    }

    public static void main(String[] args) {
        launch();
    }
    
}
