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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.esprit.entity.Circuit;

/**
 * FXML Controller class
 *
 * @author balia
 */
public class CardController implements Initializable {

    @FXML
    private Label Cdpart;
    @FXML
    private Label Carrive;
    @FXML
    private Label Ctemps;
    @FXML
    private Label Cprix;
    @FXML
    private Label Ccateg;
    @FXML
    private ImageView img;
    @FXML
    private Label Cdescription;

  
private Circuit circuit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void initializeCard(Circuit circuit) {
         Cdpart.setText(circuit.getDepart());
    Carrive.setText(circuit.getArrive());
    Ctemps.setText(circuit.getTemps());
    Cprix.setText(String.valueOf(circuit.getPrix()));
    Ccateg.setText(circuit.getCategorie());
    Cdescription.setText(circuit.getDescription());
    this.circuit = circuit;

    // Debugging: Print the values
    System.out.println("Depart: " + circuit.getDepart());
    System.out.println("Arrive: " + circuit.getArrive());
    }

    @FXML
    private void moreDetails(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/gui/Details.fxml"));
            Parent root = loader.load();
            
            DetailsController detailsController = loader.getController();
            detailsController.initializeDetails(circuit);
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error loading Details.fxml: " + ex.getMessage());
        }
    }
    
    
}
