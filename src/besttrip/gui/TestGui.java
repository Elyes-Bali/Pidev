/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package besttrip.gui;


 

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class TestGui  extends Application {
    public static Stage stg;
    @Override 
    public void start(Stage primaryStage) 
    {
        try {
            TestGui.stg = primaryStage;
            
          // FXMLLoader loader= new FXMLLoader(getClass().getResource("../gui/ConsulterReservation.fxml"));
                  FXMLLoader loader= new FXMLLoader(getClass().getResource("../gui/Acceuil.fxml"));

            Parent root= loader.load();
            Scene scene= new Scene(root);
            primaryStage.setTitle("Bievennue");
            primaryStage.setScene(scene);
            primaryStage.show();
        } 
        catch (IOException ex)
        {
            Logger.getLogger(TestGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

 
    
   public static void main(String[] args) {
           launch(args);
    
      
    }
       
}
