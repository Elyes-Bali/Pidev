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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import tn.esprit.entity.Circuit;
import tn.esprit.services.ServiceCircuit;

/**
 * FXML Controller class
 *
 * @author balia
 */
public class ListeController implements Initializable {

    @FXML
    private ListView<Circuit> listev;
    private ObservableList<Circuit> circuitList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadInitialDataFromDatabase();
        listev.setItems(circuitList);
        // TODO
    }    
        private void loadInitialDataFromDatabase() {
    ServiceCircuit ps = new ServiceCircuit();
    List<Circuit> initialCircuits = ps.afficher();
    
    // Populate circuitList with the initial data from the database
    circuitList.clear();
    circuitList.addAll(initialCircuits);
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
