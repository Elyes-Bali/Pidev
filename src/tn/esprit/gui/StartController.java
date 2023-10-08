/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import workshop_jdbc_4se1.HomeController;

/**
 * FXML Controller class
 *
 * @author balia
 */
public class StartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void navCircuit(ActionEvent event) {
          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/gui/Adder.fxml"));
        Parent root = loader.load();
        
        // Access the StartController if needed
        AdderController adderController = loader.getController();
        // Initialize data or perform other operations here
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println("Error loading Adder.fxml: " + ex.getMessage());
    }
    }

    @FXML
    private void navList(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/gui/Liste.fxml"));
        Parent root = loader.load();
        
        // Access the StartController if needed
        ListeController listeController = loader.getController();
        // Initialize data or perform other operations here
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println("Error loading Liste.fxml: " + ex.getMessage());
    }
    }
    
}
