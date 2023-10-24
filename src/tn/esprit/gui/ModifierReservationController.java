/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

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
import tn.esprit.Services.servicereservation;
import tn.esprit.entity.reservation;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import tn.esprit.entity.Transport;
/**
 *
 * @author PC
 */
public class ModifierReservationController  implements Initializable{
     @FXML
    private TextField capt;

    @FXML
    private DatePicker ddt;

    @FXML
    private TextField dat;

    @FXML
    private Button retourt;


    @FXML
    private TextField typet;
    
    private servicereservation servicereservation;
   
    @FXML
    private AnchorPane Modifierpanal;
    @FXML
    private TableView<reservation> listTransport;
    
    ObservableList<reservation> listeM ;

    @FXML
    private TableColumn<reservation,String> capmod;
    @FXML
    private TableColumn<reservation, String> typemod;
    @FXML
    private TableColumn<reservation, String> ddmod;
  
        
    @FXML
    private Button okmod;
    @FXML
    private Button Modifiert;
    
     servicereservation t=new servicereservation();
     ObservableList<reservation> dataHebergement = FXCollections.observableArrayList(); 
     ObservableList<reservation> data = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{ 
        List<reservation> reservationList = t.getAllreservation();
        System.out.println("Number of reservations: " + reservationList.size());
        listeM = FXCollections.observableArrayList(reservationList);
        capmod.setCellValueFactory(new PropertyValueFactory("transportId"));
        typemod.setCellValueFactory(new PropertyValueFactory("clientId"));
        ddmod.setCellValueFactory(new PropertyValueFactory("debutReservation"));
      
        listTransport.setItems(listeM);
       }catch(Exception e){
           System.out.println(e.getMessage());
       };
        servicereservation = new servicereservation();
         retourt.setOnAction(event -> retournerALaPagePrincipale());
         listTransport.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
        populateFields(newSelection);
    }
});
        
        //////////////////////////////////////////////
        /*
         servicereservation hs=new servicereservation();
        hs.getAllreservation().stream().forEach((c) -> {
            dataHebergement.add(c);
        });
        
        capmod.setCellValueFactory(new PropertyValueFactory<>("id_transport"));
        capmod.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        capmod.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<reservation, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<reservation, Integer> event) {
                reservation h = event.getRowValue();
                h.setTransportId(event.getNewValue());
                servicereservation hs = new servicereservation();
                hs.updatereservation(h);
            }
        });
        typemod.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        typemod.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
    
        listTransport.setItems(dataHebergement);*/
        /////////////////////////////////////////////////////////////////
        /*servicereservation = new servicereservation();

        List<reservation> reservationList = servicereservation.getAllreservation();
        data.addAll(reservationList);

        capmod.setCellValueFactory(cellData -> cellData.getValue().id_transportProperty().asObject());
        typemod.setCellValueFactory(cellData -> cellData.getValue().id_clientProperty().asObject());
        ddmod.setCellValueFactory(cellData -> cellData.getValue().debutReservartionProperty());

        listTransport.setItems(data);*/
       

        
// TODO
    }
    
     private void populateFields(reservation selectedReservation) {
    
/*java.util.Date utilDate = selectedReservation.getDebutReservation();
LocalDate localDate = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
ddt.setValue(localDate);*/

    capt.setText(String.valueOf(selectedReservation.getTransportId()));
    typet.setText(String.valueOf(selectedReservation.getClientId()));
   
}
      @FXML
private void modifierreservation() {
    reservation selectedReservation = listTransport.getSelectionModel().getSelectedItem();
    servicereservation sr = new servicereservation();
    
    if (selectedReservation == null) {
        System.out.println("Selected reservation is null");
        showAlert("Sélectionnez un transport", "Veuillez sélectionner un transport à modifier.");
        return;
    } 
    
    LocalDate localDate = ddt.getValue();
    
    Date date = Date.valueOf(localDate);
    String id_transport = capt.getText();
        String id_client = typet.getText();
    
    int tr;
    int cl;

    try {
        tr = Integer.parseInt(id_transport);
        cl = Integer.parseInt(id_client);
    } catch (NumberFormatException e) {
        System.out.println("NumberFormatException caught");
        showAlert("Erreur de saisie", "Veuillez entrer des valeurs numériques valides pour Capacité et Prix.");
        return;
    }
    reservation existingReservation = sr.getReservationByTransportAndClient(tr, cl);
    int idReserve = sr.getReservationIdByClientId(selectedReservation.getClientId());
    // Show a confirmation dialog
    boolean confirmed = showConfirmationAlert("Confirmation de modification", "Voulez-vous vraiment modifier ce transport ?");
     
   if (confirmed) {
        // Retrieve a reservation by transport_id and client_id
            reservation NewReserve = new reservation(idReserve, tr, cl, date);
            

        
            // Update the transport in the database
            
            sr.updatereservation(NewReserve);
            
            System.out.println("Update completed");
            listTransport.refresh();
       
    }
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
    
    ddt.setValue(null);
    
    capt.clear();
    typet.clear();
    
}


    private void showAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
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
