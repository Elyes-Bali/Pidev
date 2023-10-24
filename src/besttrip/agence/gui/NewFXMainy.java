/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.agence.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author zouar
 */
public class NewFXMainy extends Application {
    
    @Override
    public void start(Stage primaryStage) {
           Parent root;
        try {
root = FXMLLoader.load(getClass().
//                     getResource("ListeRepRec.fxml"));
//                   getResource("ListeReclamationAdmin.fxml"));
                    getResource("ReclamationClient.fxml"));                    
                    
            Scene scene = new Scene(root);
          primaryStage.setTitle("Réclamation");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
