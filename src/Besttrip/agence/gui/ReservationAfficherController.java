/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import Besttrip.agence.entity.Transport;
import Besttrip.agence.entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import Besttrip.agence.services.servicereservation;
import Besttrip.agence.entity.reservationTransport;
import Besttrip.agence.services.Services;
import Besttrip.agence.services.ServiceTransport;
import java.sql.Date;
import javafx.scene.control.ComboBox;

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
    private TableView<reservationTransport> tableafficher;
    ObservableList<reservationTransport> listeM;
    @FXML
    private TableColumn<reservationTransport, Integer> capacol;
    @FXML
    private TableColumn<reservationTransport, Integer> typecol;
    @FXML
    private TableColumn<reservationTransport, Date> ddcol;
   
    @FXML
    private ComboBox<User> clientComboBox; // Add a ComboBox for selecting clients

    @FXML
    private ComboBox<Transport> transportComboBox; // Add a ComboBox for selecting transports

    private List<User> userList;
    private List<Transport> transportList;

    @FXML
    private TextField rechfield; // TextField for user input

    servicereservation t = new servicereservation();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{ 
        List<reservationTransport> reservationList = t.getAllreservation();
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
      Services userService = new Services();
            userList = userService.afficher();
            ObservableList<User> clientOptions = FXCollections.observableArrayList(userList);
            clientComboBox.setItems(clientOptions);

            // Load the transport list and populate the ComboBox
            ServiceTransport transportService = new ServiceTransport();
            transportList = transportService.getAllTransports();
            ObservableList<Transport> transportOptions = FXCollections.observableArrayList(transportList);
            transportComboBox.setItems(transportOptions);
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
