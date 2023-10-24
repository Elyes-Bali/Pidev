/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Hebergement;
import entities.Reservation;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author Ons
 */
public class ListHebFrontCardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private Label labelAdresse;

    @FXML
    private Label labelCapacite;

    @FXML
    private Label labelDescription;

    @FXML
    private Label labelPrix;

    @FXML
    private Label labelType;
    
    @FXML
    private Button btnReserver;
    
    MyListener myListener;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    Hebergement heb;
    
    
    private void ajouterReservation(Hebergement h) {
        
        int id_hebergement = h.getId();
        LocalDateTime currentDateTime = LocalDateTime.now();;
        Date dateReservation = java.sql.Timestamp.valueOf(currentDateTime);
        
        Reservation r = new Reservation(dateReservation, id_hebergement);
        ReservationService rs = new ReservationService();
        rs.ajouter(r);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ajouté avec succès");
        alert.setHeaderText(null);
        alert.setContentText("Votre Reservation a été ajoutée avec succès.");
        Optional<ButtonType> option = alert.showAndWait();
    }
    
    public void setData (Hebergement h, MyListener myListener){
        this.heb = h ;
        this.myListener = myListener;
        
        labelAdresse.setText(h.getAdresse());
        labelPrix.setText(String.valueOf(h.getPrix())+" DT  |  ");
        labelCapacite.setText(String.valueOf(h.getCapacite()));
        labelType.setText(h.getType());
        labelDescription.setText(h.getDescription());
        //ajouterReservation(h);
    }
    
    @FXML
    void ajoutRes(ActionEvent event){
        ajouterReservation(this.heb);
    }
    
    
    
}
