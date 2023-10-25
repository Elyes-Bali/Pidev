/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import Besttrip.agence.entity.Transport;
import Besttrip.agence.entity.User;
import Besttrip.agence.entity.reservationTransport;
import Besttrip.agence.services.ServiceTransport;
import Besttrip.agence.services.Services;
import Besttrip.agence.services.servicereservation;
import java.io.IOException;
import java.net.URL;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.CurrentUser;
/**
 *
 * @author PC
 */
public class ReservationUser implements Initializable {
     @FXML
    private TableView<reservationTransport> myreserv;
    ObservableList<reservationTransport> listeM;
    @FXML
    private TableColumn<reservationTransport, String> ddeb;
    
    @FXML
    private Button gototransport; 
    
    @FXML
    private Button reserv;
    private servicereservation servicereservation;
    servicereservation t = new servicereservation();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        servicereservation = new servicereservation();
        User  user = CurrentUser.getCurrentUser();
         try{ 
        List<reservationTransport> reservationList = t.getReservationsByClientId(user.getIdU());
        listeM = FXCollections.observableArrayList(reservationList);
        System.out.println(user.getIdU());
        ddeb.setCellValueFactory(new PropertyValueFactory<>("debutReservation"));
        myreserv.setItems(listeM);
        // TODO
       }catch(Exception e){
           System.out.println(e.getMessage());
       };
     
    }
    
    @FXML
    private void deconnecter(ActionEvent event) {
    }

    @FXML
    private void EventAcceuil(ActionEvent event) {
    }

    @FXML
    private void TransportAcceuil(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("AffichageTransportDeux.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) gototransport.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void CircuitAcceuil(ActionEvent event) {
    }

    @FXML
    private void HebergementAcceuil(ActionEvent event) {
    }

    @FXML
    private void ReclamationAcceuil(ActionEvent event) {
    }

    @FXML
    private void ForumAcceuil(ActionEvent event) {
    }
    @FXML
    private void gotoreserv(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ReservationUser.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) reserv.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
