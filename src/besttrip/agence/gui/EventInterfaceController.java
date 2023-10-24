/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import Besttrip.agence.entity.Evenement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import besttrip.agence.services.ServiceEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author Dhouha
 */
public class EventInterfaceController implements Initializable {
    @FXML
    private Button btn1;

@FXML
    private Button btn2;
@FXML
    private Button btn3;
@FXML
    private Button btn4;
@FXML
    private Button btn5;
 
    @FXML
    private TableColumn<Evenement, String> desccolumn;

 

    @FXML
    private TableColumn<Evenement, String> datecolumn;
    @FXML
    private TableColumn<Evenement, String> catecolumn;
    @FXML
    private TableColumn<Evenement, String> nomcolumn;

    @FXML
    private TableColumn<Evenement, String> lieucolumn;

    

    @FXML
    private TableView<Evenement> table;
    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    show();
      }

   ObservableList<Evenement> listeB = FXCollections.observableArrayList();

    public void show(){
    ServiceEvent bs=new ServiceEvent();
         listeB=bs.obtenirTousLesEvenements();
        nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
         lieucolumn.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        desccolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        datecolumn.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            catecolumn.setCellValueFactory(new PropertyValueFactory<>("categorieNom"));
 
      
       
 
        table.setItems(listeB);
    }

    @FXML
    void Supprimer(ActionEvent event) {
          Evenement selectedLN =  table.getSelectionModel().getSelectedItem();
         if (selectedLN == null) {
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible de supprimer l'événement ");
        alert.setContentText("Veuillez sélectionner une événement à  supprimer !");
        alert.showAndWait();
    }
         else{
             Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation de suppression");
alert.setHeaderText("Voulez-vous vraiment supprimer cette evenement ?");
alert.setContentText("Cliquez sur OK pour confirmer la suppression.");

Optional<ButtonType> result = alert.showAndWait();

if (result.isPresent() && result.get() == ButtonType.OK) {
    // L'utilisateur a confirmé la suppression, vous pouvez maintenant supprimer la boutique.
    ServiceEvent bs = new ServiceEvent();
        bs.supprimer(selectedLN);
        show();
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("information");
        alert1.setHeaderText(null);
        alert1.setContentText("l''evenement est supprimé!");
        alert1.showAndWait();}
} 

       //System.out.println(selectedLN.getId_b());
       
    }
    @FXML
    void Ajouter(ActionEvent event) {
            try {
              Parent page1 = FXMLLoader.load(getClass().getResource("../gui/AjouterEvents.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterEventsController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    @FXML
    void Modifier(ActionEvent event) {
        Evenement selectedLN = table.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier");
            alert.setContentText("Veuillez selectionner pour modifier !");
            alert.showAndWait();
        } else {
            // Show input dialogs to get the new event details.
            TextInputDialog dialogNom = new TextInputDialog();
            dialogNom.setTitle("Modifier un evenement");
            dialogNom.setHeaderText("Modifier les champs de l'evenement");
            dialogNom.setContentText("Nom de l'evenement:");

            TextInputDialog dialogLieu = new TextInputDialog();
            dialogLieu.setTitle("Modifier un evenement");
            dialogLieu.setHeaderText("Modifier les champs de l'evenement");
            dialogLieu.setContentText("Lieu de l'evenement:");

        

            TextInputDialog dialogDesc = new TextInputDialog();
            dialogDesc.setTitle("Modifier un evenement");
            dialogDesc.setHeaderText("Modifier les champs de l'evenement");
            dialogDesc.setContentText("Description de l'evenement:");

            // Set the default values of the input fields to the current event details.
            dialogNom.getEditor().setText(selectedLN.getNom());
            dialogLieu.getEditor().setText(selectedLN.getLieu());
             
            dialogDesc.getEditor().setText(selectedLN.getDescription());

            Optional<String> resultNom = dialogNom.showAndWait();
            Optional<String> resultLieu = dialogLieu.showAndWait();
             Optional<String> resultDesc = dialogDesc.showAndWait();

            if (resultNom.isPresent() && resultLieu.isPresent()  && resultDesc.isPresent()) {
                // Update the event details with the new values.
                selectedLN.setNom(resultNom.get());
                selectedLN.setLieu(resultLieu.get());
                 selectedLN.setDescription(resultDesc.get());

                ServiceEvent bs = new ServiceEvent();

                bs.modifier(selectedLN);

                // Show a confirmation alert.
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setHeaderText("L'evenement a ete modifie avec succes");
                alert.setContentText("Les modifications ont ete enregistrees.");
                alert.showAndWait();
            }
        }
        table.refresh();
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    @FXML
    void CosnulterRes(ActionEvent event) {
        try {
              Parent page1 = FXMLLoader.load(getClass().getResource("../gui/ConsulterReservation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ConsulterReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    @FXML
    void ConsulterPart(ActionEvent event) {
         try {
              Parent page1 = FXMLLoader.load(getClass().getResource("../gui/ListeParticipant.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ListeParticipantController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    
    
    ///////////////bouton retour transp
    @FXML
    private void goToHome(ActionEvent event) {
        try {
              Parent page1 = FXMLLoader.load(getClass().getResource("../gui/Acceuil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ListeParticipantController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
     
}
