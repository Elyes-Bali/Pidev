/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.entity.Circuit;
import tn.esprit.services.ServiceCircuit;

/**
 * FXML Controller class
 *
 * @author balia
 */
public class ListeController implements Initializable {

    
    private ObservableList<Circuit> circuitList = FXCollections.observableArrayList();
    @FXML
    private VBox cardContainer;
    @FXML
    private HBox viewing;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadInitialDataFromDatabase();
        
        // TODO
    }    
  private void loadInitialDataFromDatabase() {
        ServiceCircuit ps = new ServiceCircuit();
        List<Circuit> initialCircuits = ps.afficher();

        // Create and display cards for each Circuit
        for (Circuit circuit : initialCircuits) {
            try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/gui/Card.fxml"));
    Parent cardLayout = loader.load();
    CardController cardController = loader.getController();
    cardController.initializeCard(circuit);
    // Add vertical margin to create space between cards
            VBox.setMargin(cardLayout, new Insets(10, 0, 0, 0));
    cardContainer.getChildren().add(cardLayout);
} catch (IOException ex) {
    ex.printStackTrace();
}
        }
    }

           
    @FXML
    private void returnHome(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/gui/Start.fxml"));
        Parent root = loader.load();
        
        // Access the StartController if needed
        StartController startController = loader.getController();
        // Initialize data or perform other operations here
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println("Error loading Start.fxml: " + ex.getMessage());
    }
    }
}
