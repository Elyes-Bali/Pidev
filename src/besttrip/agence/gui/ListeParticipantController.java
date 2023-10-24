/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Besttrip.agence.gui;

import Besttrip.agence.entity.Evenement;
import Besttrip.agence.entity.Participant;
import besttrip.agence.services.ServiceEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

 
public class ListeParticipantController implements Initializable {
 

 

    @FXML
    private TableColumn<Participant, String> nomcolumn;
    @FXML
    private TableColumn<Participant, String> prenomcolumn;
    @FXML
    private TableColumn<Participant, String> tel;

    @FXML
    private TableColumn<Participant, String> email;

    

    @FXML
    private TableView<Participant> table;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           show(); 
    }    

       

   ObservableList<Participant> listeB = FXCollections.observableArrayList();

    public void show(){
    ServiceEvent bs=new ServiceEvent();
        listeB=bs.obtenirTousLesParticipant();
        nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcolumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
 
 
      
       
 
        table.setItems(listeB);
    }
        @FXML
    void Aller(ActionEvent event) {
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
}
