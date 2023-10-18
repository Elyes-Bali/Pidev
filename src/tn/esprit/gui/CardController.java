/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    

    // Debugging: Print the values
    System.out.println("Depart: " + circuit.getDepart());
    System.out.println("Arrive: " + circuit.getArrive());
    }
    
}
