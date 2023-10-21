/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.agence.gui;


import besttrip.agence.entity.ReponseReclamation;
import besttrip.agence.services.ServiceRepRec;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zouar
 */
public class ListeRepRecController implements Initializable {

    @FXML
    private AnchorPane button;
    @FXML
    private ListView<ReponseReclamation> ListeReponseAdmin;
      private ObservableList<ReponseReclamation> recList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadInitialDataFromDatabase();
        ListeReponseAdmin.setItems(recList);
    }    
      private void loadInitialDataFromDatabase() {
    ServiceRepRec ps = new ServiceRepRec();
    List<ReponseReclamation> initialReponseReclamation = ps.afficher();
    
    // Populate circuitList with the initial data from the database
    recList.clear();
    recList.addAll(initialReponseReclamation);
}

    @FXML
    private void buttonRetourListeRep(ActionEvent event) {
                try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/besttrip/agence/gui/ListeReclamationAdmin.fxml"));
        Parent root = loader.load();
       
        // Access the StartController if needed
        ListeReclamationAdminController startController = loader.getController();
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