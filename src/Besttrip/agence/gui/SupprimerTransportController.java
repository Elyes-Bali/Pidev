/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import java.io.IOException;
import java.net.URL;
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
import Besttrip.agence.services.ServiceTransport;
import Besttrip.agence.services.servicereservation;
import Besttrip.agence.entity.Transport;
import Besttrip.agence.entity.reservationTransport;

/**
 * FXML Controller class
 *
 * @author amadd
 */
public class SupprimerTransportController implements Initializable {
   
     private Button supprimertButton;
    private Button retourtbutton;
    @FXML
    private TextField idt;
     @FXML
    private Button supprimert;
   
    @FXML
    private TableView<Transport> tablesupp;
      ObservableList<Transport> listeM ;

    @FXML
    private TableColumn<Transport, Integer> capsup;
    @FXML
    private TableColumn<Transport, String> typesup;
    @FXML
    private TableColumn<Transport, String> ddsup;
    @FXML
    private TableColumn<Transport, String> dasup;
    @FXML
    private TableColumn<Transport, Integer> prixsup;
     ServiceTransport t=new ServiceTransport();
    @FXML
    private Button oksup;
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{ 
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
        
    }    
    
    @FXML
   private void supprimerTransport() {
    /* String idToDelete = idt.getText();

     try {
        int id = Integer.parseInt(idToDelete);
        Transport Transport =new Transport(id, 0, null, null , null, 0 );
        ServiceTransport servicetransport = new ServiceTransport();
        servicetransport.deleteTransport(Transport);


        idt.clear();
     } catch (NumberFormatException e) {
        System.out.println("ID non valide : " + idToDelete);
     }*/
     
     /*reservationTransport selectedReservation = tablesupp.getSelectionModel().getSelectedItem();
    servicereservation sr = new servicereservation();
    int idReserve = sr.getReservationIdByClientId(selectedReservation.getClientId());
     sr.deletereservation(idReserve);
     tablesupp.refresh();*/
     Transport NewTransport = tablesupp.getSelectionModel().getSelectedItem();
    
    ServiceTransport transportdao = new ServiceTransport();
    
    Transport selectedTransport = transportdao.getTransportParID(NewTransport.getId());
     t.deleteTransport(selectedTransport.getId());
     tablesupp.refresh();
     
}
   @FXML
private void retournerALaPagePrincipale() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listeTransport.fxml"));
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
    private Button retourt;
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



