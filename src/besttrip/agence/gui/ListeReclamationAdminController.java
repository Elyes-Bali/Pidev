/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.agence.gui;

import besttrip.agence.entity.Reclamation;
import besttrip.agence.entity.ReponseReclamation;
import besttrip.agence.services.ServiceRepRec;
import besttrip.agence.services.Services;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zouar
 */
public class ListeReclamationAdminController implements Initializable {

    @FXML
    private ListView<Reclamation> ListeRecAdmin;
    @FXML
    private Label idUserEmail;
    @FXML
    private TextArea ReponceRecAdmin;
    @FXML
    private AnchorPane button;
    
      private ObservableList<Reclamation> recList = FXCollections.observableArrayList();
    @FXML
    private TextArea MessageListeRec;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadInitialDataFromDatabase();
        ListeRecAdmin.setItems(recList);
        
        ListeRecAdmin.setOnMouseClicked(event ->{
        if (event.getClickCount()==2){
            Reclamation selectedReclamation = ListeRecAdmin.getSelectionModel().getSelectedItem();
            if (selectedReclamation != null){
            idUserEmail.setText(selectedReclamation.getEmailU());            
            MessageListeRec.setText(selectedReclamation.getTextRec());

            }
        }
        });
               //  listv.setItems(circuitList);
        // TODO
    }    
    
       private void loadInitialDataFromDatabase() {
    Services ps = new Services();
    List<Reclamation> initialReclamations = ps.afficher();
    
    // Populate circuitList with the initial data from the database
    recList.clear();
    recList.addAll(initialReclamations);
}

    @FXML
    private void buttonAcceuilListeRec(ActionEvent event) {
    }

    @FXML
    private void buttonEnvoyerRepRec(ActionEvent event) {      
        String textRepRec  = ReponceRecAdmin.getText();

        ServiceRepRec ps = new ServiceRepRec();
        ReponseReclamation p = new ReponseReclamation(textRepRec,"dsxf", 1102, 7);       
        ps.save(p);

clearTextFields();
    // Clear the TextFields after saving
    //Alerte
    try {
    showAlert(Alert.AlertType.INFORMATION, "Succès", "Réclamation ajoutée avec succès", "");
    }    catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de l'ajout de Réclamation", e.getMessage());
                e.printStackTrace();
            }
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);

    alert.showAndWait();
}
    
    private void clearTextFields() {
    
    ReponceRecAdmin.clear();}

    @FXML
    private void buttonSupprimerRepRec(ActionEvent event) {
         Reclamation selectedReclamation = ListeRecAdmin.getSelectionModel().getSelectedItem();

    // Check if an item is selected
    if (selectedReclamation == null) {
        System.out.println("No item selected for deletion.");
        return;
    }

    // Remove the selected item from the database
    Services ps = new Services();
    ps.supprimer(selectedReclamation);

    // Remove the selected item from the ListView
    recList.remove(selectedReclamation);

    }

    @FXML
    private void buttonRetourListeRec(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/besttrip/agence/gui/ListeRepRec.fxml"));
        Parent root = loader.load();
       
        // Access the StartController if needed
        ListeRepRecController startController = loader.getController();
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
    

