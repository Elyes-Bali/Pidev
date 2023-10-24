/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Besttrip.agence.gui;

import Besttrip.agence.entity.Evenement;
import Besttrip.agence.entity.Reservation;
import besttrip.agence.services.ReservationServiceImpl;
import besttrip.agence.services.ServiceEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

 import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

 
 import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;
import javax.smartcardio.Card;
 
public class AffichageEventFrontController implements Initializable {
    @FXML
    private TilePane eventsTilePane;
    ServiceEvent a = new ServiceEvent();
    public static Evenement pr ; 
    
  
 ObservableList<Evenement> obList;
    @FXML
    private Button homie;
   
        
  @Override
public void initialize(URL url, ResourceBundle rb) {
    ServiceEvent a = new ServiceEvent();
    obList = a.obtenirTousLesEvenements(); 
    
    if (obList.isEmpty()) {
        // La liste d'événements est vide, afficher une alerte
        showNoEventsAlert();
    } else {
        for (Evenement event1 : obList) {
            Card card = new Card(event1);
            eventsTilePane.getChildren().add(card);
        }
    }
}

private void showNoEventsAlert() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Aucun événement");
    alert.setHeaderText("Il n'y a actuellement aucun événement à afficher.");
    alert.setContentText("Revenez plus tard pour consulter de nouveaux événements.");
    alert.showAndWait();
}

    @FXML
    private void retourAcceuil(ActionEvent event) {
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

    public class Card extends VBox {
    public Card(Evenement event1) {
        // Create an Image object from the file path
        Image image = new Image("file:" + event1.getImage());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        Label nomLabel = new Label(event1.getNom());
        Label descriptionLabel = new Label("Nombre de places: " + event1.getPlacesDisponibles());
        Label prixLabel = new Label("Prix: " + event1.getTarif());
        prixLabel.setTextFill(Color.GREEN);
        VBox.setMargin(imageView, new Insets(10, 0, 0, 0));
        VBox.setMargin(nomLabel, new Insets(10, 0, 0, 0));
        VBox.setMargin(descriptionLabel, new Insets(10, 0, 0, 0));
        VBox.setMargin(prixLabel, new Insets(10, 0, 0, 0));

       // Create a reservation button
        Button reserveButton = new Button("Réserver");
        reserveButton.getStyleClass().add("reserver-button");

        reserveButton.setOnAction(event -> {
        ReservationDialog.showReservationDialog((phoneNumber, numberOfSeats) -> {
        // Code pour traiter la réservation ici
        // Vous pouvez maintenant utiliser phoneNumber et numberOfSeats
        Reservation reservation = new Reservation();
        reservation.setPlacesReservees(numberOfSeats);
        reservation.setEvenement(event1); // Assurez-vous d'avoir l'événement actuel
        ReservationServiceImpl sc = new ReservationServiceImpl();
        try {
            int participantId=-1;
            participantId=sc.foundParticpant(phoneNumber);
          if (participantId == -1) {
        // Afficher une alerte ou lancer une exception en cas de participant non trouvé
           Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Aucun événement");
    alert.setHeaderText("Il n'y a actuellement aucun participant à trouver.");
    alert.setContentText("Revenez plus tard pour consulter particiapant.");
    alert.showAndWait();
        System.out.println("Participant non trouvé pour le numéro de téléphone : " + phoneNumber);
        // Vous pouvez également lancer une exception ici si nécessaire
    } else {
            // Appelez la méthode pour ajouter la réservation
            sc.ajouterReservation(reservation, participantId);
            
       
            showAlert(AlertType.CONFIRMATION, "Succès", "Réservation effectuée avec succès !", "Numéro de téléphone : " + phoneNumber + "\nNombre de places réservées : " + numberOfSeats);
        
            //nbadel blyes l9a3det with bd update 
        

            int numberOfSeatsReserved = reservation.getPlacesReservees();
            int numberOfSeatsAvailable = event1.getPlacesDisponibles();
            int newNumberOfSeatsAvailable = numberOfSeatsAvailable - numberOfSeatsReserved;
            event1.setPlacesDisponibles(newNumberOfSeatsAvailable);
            ServiceEvent sc1 =new ServiceEvent();
                sc1.modifier(event1);
            descriptionLabel.setText("place :"+String.valueOf(newNumberOfSeatsAvailable));
            showQRCodeStage();
                closeCurrentStage();
          }
        } catch (Exception ex) {
            // Gérez les exceptions
        }
    });
});
        
        
        HBox buttonBox = new HBox(reserveButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        VBox.setMargin(buttonBox, new Insets(0, 10, 0, 0));

        // Add all nodes to the card
        getChildren().addAll(imageView, nomLabel, descriptionLabel, prixLabel, buttonBox);
        setStyle("-fx-background-color: #E5D6EE; -fx-border-color: black; -fx-padding: 10;");
        setPrefWidth(200);
        setPrefHeight(250);
        getStyleClass().add("product-card");
    }
    
    private void showQRCodeStage() {
    JavaFX_QRCodeWriterController qrCodeWriterController = new JavaFX_QRCodeWriterController();
    qrCodeWriterController.start(new Stage());
}

private void closeCurrentStage() {
    Stage currentStage = (Stage) eventsTilePane.getScene().getWindow();
    currentStage.close();
}
//In this modified code, after a successful reservation, the showQRCodeStage() method is called to display the QR code window using the JavaFX_QRCodeWriterController. Then, the closeCurrentStage() method is called to close the current stage (AffichageEventFrontController). This approach ensures that the QR code window is displayed after a successful reservation, and the reservation window is closed.



        private void showAlert(AlertType alertType, String title, String headerText, String contentText) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
}
    }
}
 




  





 