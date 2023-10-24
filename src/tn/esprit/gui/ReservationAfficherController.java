/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.Services.servicereservation;
import tn.esprit.entity.reservation;
import java.sql.Date;

/**
 *
 * @author PC
 */
public class ReservationAfficherController implements Initializable {
     @FXML
    private Button affichert;
    @FXML
    private Button retourt;
    @FXML
    private TableView<reservation> tableafficher;
    ObservableList<reservation> listeM;
    @FXML
    private TableColumn<reservation, Integer> capacol;
    @FXML
    private TableColumn<reservation, Integer> typecol;
    @FXML
    private TableColumn<reservation, Date> ddcol;
   

    @FXML
    private TextField rechfield; // TextField for user input

    servicereservation t = new servicereservation();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{ 
        List<reservation> reservationList = t.getAllreservation();
        listeM = FXCollections.observableArrayList(reservationList);
        capacol.setCellValueFactory(new PropertyValueFactory<>("id_transport"));
        typecol.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        ddcol.setCellValueFactory(new PropertyValueFactory<>("debutReservartion"));
        tableafficher.setItems(listeM);
        // TODO
       }catch(Exception e){
           System.out.println(e.getMessage());
       };
      retourt.setOnAction(event -> retournerALaPagePrincipale());
    }

    @FXML
    private void retournerALaPagePrincipale() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
            Parent root = loader.load();

            Scene scene = retourt.getScene();

            scene.setRoot(root);

            Stage stage = (Stage) scene.getWindow();
            stage.setTitle("besttrip");

        } catch (IOException e) {
        }
    }

   
}
