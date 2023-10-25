/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class ModifierReservationController implements Initializable {

    @FXML
    private TextField capt;
    @FXML
    private TextField sp_dd;

    @FXML
    private DatePicker ddt;

    @FXML
    private TextField dat;

    @FXML
    private Button retourrr;
    @FXML
    private DatePicker sp_da;

    @FXML
    private TextField typet;

    private servicereservation servicereservation;

    @FXML
    private AnchorPane Modifierpanal;
    @FXML
    private TableView<reservationTransport> listTransport;

    ObservableList<reservationTransport> listeM;


    @FXML
    private TableColumn<reservationTransport, String> ddcol;

    @FXML
    private Button okmod;
    @FXML
    private Button Modifiert;

    servicereservation t = new servicereservation();
    ObservableList<reservationTransport> dataHebergement = FXCollections.observableArrayList();
    ObservableList<reservationTransport> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            List<reservationTransport> reservationList = t.getAllreservation();
            System.out.println("Number of reservations: " + reservationList.size());
            listeM = FXCollections.observableArrayList(reservationList);

            ddcol.setCellValueFactory(new PropertyValueFactory("debutReservation"));

            listTransport.setItems(listeM);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        };
        servicereservation = new servicereservation();

    }

    private void populateFields(reservationTransport selectedReservation) {
        

    }

    @FXML
    private void ajouterMoyen() {
        reservationTransport selectedReservation = listTransport.getSelectionModel().getSelectedItem();
        servicereservation sr = new servicereservation();
        reservationTransport selectedTransport = sr.getReservationByID(selectedReservation.getId());
        
        if (selectedReservation == null) {
            System.out.println("Selected reservation is null");
            showAlert("Sélectionnez un transport", "Veuillez sélectionner un transport à modifier.");
            return;
        }

        LocalDate localDate = sp_da.getValue();
        Date utildate = java.sql.Date.valueOf(localDate);

        
        int tr = selectedTransport.getTransportId();
        int cl = selectedTransport.getClientId();
        reservationTransport NewReserve = new reservationTransport(selectedTransport.getClientId(), tr, cl, utildate);
        //Transport newTr = new Transport(selectedTransport.getId(),newCap, newType, newDd, newDa, newPrix);

        // Update the transport in the database
        sr.updatereservation(NewReserve);

        System.out.println("Update completed");
        listTransport.refresh();

        //int idReserve = sr.getReservationIdByClientId(selectedTransport.getClientId());
        // Show a confirmation dialog
       

    }

    private boolean showConfirmationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        return result.orElse(ButtonType.CANCEL) == ButtonType.OK;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {

        sp_da.setValue(null);

    }

    private void showAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    private void retournerALaPagePrincipale() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ReservationDeux.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) retourrr.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
