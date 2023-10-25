/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import Besttrip.agence.services.servicereservation;
import Besttrip.agence.entity.reservationTransport;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import Besttrip.agence.entity.Transport;
import javafx.event.ActionEvent;

/**
 *
 * @author PC
 */
public class SupprimerReservationController implements Initializable{
        private Button supprimertButton;
    private Button retourtbutton;
     @FXML
    private Button supprimert;
    @FXML
    private Button retourt;
    @FXML
    private TableView<reservationTransport> tablesupp;
    
    ObservableList<reservationTransport> listeM ;

      
    private servicereservation servicereservation;
      
    @FXML
    private TableColumn<reservationTransport, String> capsup;
    @FXML
    private TableColumn<reservationTransport, String> typesup;
    @FXML
    private TableColumn<reservationTransport, String> ddsup;

    @FXML
    private Button oksup;
    
    servicereservation t=new servicereservation();
     ObservableList<reservationTransport> dataHebergement = FXCollections.observableArrayList(); 
     ObservableList<reservationTransport> data = FXCollections.observableArrayList();
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try{ 
         List<reservationTransport> reservationList = t.getAllreservation();
        System.out.println("Number of reservations: " + reservationList.size());
        listeM = FXCollections.observableArrayList(reservationList);
       
        ddsup.setCellValueFactory(new PropertyValueFactory("debutReservation"));
      
        tablesupp.setItems(listeM);
       }catch(Exception e){
           System.out.println(e.getMessage());
       };
        servicereservation = new servicereservation();
        retourt.setOnAction(event -> retournerALaPagePrincipale());
         
 /*try{ 
        List<Transport> TransportList = t.getAllTransports();
        listeM = FXCollections.observableArrayList(TransportList);
        capsup.setCellValueFactory(new PropertyValueFactory<Transport,Integer>("cap"));
        typesup.setCellValueFactory(new PropertyValueFactory<Transport,String>("type"));
        ddsup.setCellValueFactory(new PropertyValueFactory<Transport,String>("dd"));
        dasup.setCellValueFactory(new PropertyValueFactory<Transport,String>("da"));
        prixsup.setCellValueFactory(new PropertyValueFactory<Transport,Integer>("Prix"));
        tablesupp.setItems(listeM);
        // TODO
       }catch(Exception e){
           System.out.println(e.getMessage());
       };
       retourt.setOnAction(event -> retournerALaPagePrincipale());
        */
    }    
    
    @FXML
   private void supprimerTransport() {
       reservationTransport selectedReservation = tablesupp.getSelectionModel().getSelectedItem();
    servicereservation sr = new servicereservation();
    int idReserve = sr.getReservationIdByClientId(selectedReservation.getClientId());
     sr.deletereservation(idReserve);
     tablesupp.refresh();


}
   @FXML
private void retournerALaPagePrincipale() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationDeux.fxml"));
        Parent root = loader.load();

        Scene scene = retourt.getScene();

        scene.setRoot(root);

        Stage stage = (Stage) scene.getWindow();
        stage.setTitle("besttrip");

    } catch (IOException e) {
    }
}
 @FXML
    private void ListeEvent(ActionEvent event) {
    }
    //retourt
    
     @FXML
    private Button gototransport;
    @FXML
    private void ListeTransp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("listeTransport.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) gototransport.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ListeCircuit(ActionEvent event) {
    }

    @FXML
    private void ListeHeberg(ActionEvent event) {
    }

    @FXML
    private void ListeRec(ActionEvent event) {
    }

    @FXML
    private void ListeForum(ActionEvent event) {
    }

    @FXML
    private void LogoutListeUtilisateur(ActionEvent event) {
    }
}
