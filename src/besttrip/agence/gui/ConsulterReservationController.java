/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Besttrip.agence.gui;

import Besttrip.agence.entity.Evenement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

 import Besttrip.agence.entity.Reservation;
import besttrip.agence.services.ReservationService;
import besttrip.agence.services.ReservationServiceImpl;
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
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableRow;
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
public class ConsulterReservationController implements Initializable {
   @FXML
    private Button btn1;

@FXML
    private Button btn2;
    @FXML
    private TableColumn<Reservation, String> part;

 

    @FXML
    private TableColumn<Reservation, String> event;
    @FXML
    private TableColumn<Reservation, String> date;
    @FXML
    private TableColumn<Reservation, String> num;

 
 
    

    @FXML
    private TableView<Reservation> table;
    /**
     * Initializes the controller class.
     */
       ObservableList< Reservation> listeB = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    show();
    }    
 public void show() {
    ReservationServiceImpl bs = new ReservationServiceImpl();
    listeB = bs.obtenirToutesReservations();

    // Use a custom PropertyValueFactory to access nested properties
    part.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getParticipant().getNom()));
    event.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEvenement().getNom()));
    date.setCellValueFactory(new PropertyValueFactory<>("dateHeureReservation"));
    num.setCellValueFactory(new PropertyValueFactory<>("placesReservees"));

    table.setItems(listeB);

    // Set the row factory for row background color
    table.setRowFactory(tv -> {
        TableRow<Reservation> row = new TableRow<>();
 
        row.itemProperty().addListener((obs, previousReservation, currentReservation) -> {
            if (currentReservation != null) {
                if (currentReservation.isValidate()) {
                    row.setStyle("-fx-background-color: #008080;"); // Set the row color to blue for valid reservations
                }  
            }
        });

        return row;
    });
}


              @FXML
    void back(ActionEvent event) {
        try {
              Parent page1 = FXMLLoader.load(getClass().getResource("../gui/EventInterface.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(EventInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
}
          @FXML
    void cancel(ActionEvent event) {
            Reservation selectedLN =  table.getSelectionModel().getSelectedItem();
         if (selectedLN == null) {
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible de valider reservation ");
        alert.setContentText("Veuillez séléctionner une reservation à valider !");
        alert.showAndWait();
    }
         else{
             Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation de valiation");
alert.setHeaderText("Voulez-vous vraiment valider cette reservaion ?");
alert.setContentText("Cliquez sur OK pour confirmer  .");

Optional<ButtonType> result = alert.showAndWait();

if (result.isPresent() && result.get() == ButtonType.OK) {
    selectedLN.setValidate(true);
    // L'utilisateur a confirmé la suppression, vous pouvez maintenant supprimer la boutique.
    ReservationServiceImpl bs = new ReservationServiceImpl();
        bs.modifierReservation(selectedLN);
        show();
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("information");
        alert1.setHeaderText(null);
        alert1.setContentText("l'evenement est Validee !");
        alert1.showAndWait();
}
else {
         
        Alert alert1 = new Alert(AlertType.ERROR);
        alert1.setTitle("Erreur");
        alert1.setHeaderText("Impossible de valider reservation ");
         alert1.showAndWait();
    }

} 
    }
   
}
