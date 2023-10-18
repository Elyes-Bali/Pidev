/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import Besttrip.agence.entity.User;
import Besttrip.agence.services.Services;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Maroua SANDI
 */
public class ListeutilisateurController implements Initializable {

    @FXML
    private ListView<User> ListeUser;
    @FXML
    private ImageView imageListe;
    private ObservableList<User> ListU =FXCollections.observableArrayList();
    @FXML
    private TextField idD;
    @FXML
    private TextField emailD;
    @FXML
    private TextField nomD;
    @FXML
    private TextField prenomD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadInitialDataFromDatabase();
        ListeUser.setItems(ListU);
       
        ListeUser.setOnMouseClicked(event ->{
        if (event.getClickCount()==2){
            User selectedUser = ListeUser.getSelectionModel().getSelectedItem();
            if (selectedUser != null){
              idD.setText(Integer.toString(selectedUser.getIdU()));
              emailD.setText(selectedUser.getEmail());
              nomD.setText(selectedUser.getNom());
              prenomD.setText(selectedUser.getPrenom());
            }
        }});}
    private void loadInitialDataFromDatabase() {
    Services ps = new Services();
    List<User> initialUser = ps.afficher();
   
    // Populate circuitList with the initial data from the database
    ListU.clear();
    ListU.addAll(initialUser);
}
        // TODO
      
    

    @FXML
    private void Supprimer(ActionEvent event) {
        User selectedUser = ListeUser.getSelectionModel().getSelectedItem();

    // Check if an item is selected
    if (selectedUser == null) {
        System.out.println("No item selected for deletion.");
        return;
    }

    // Remove the selected item from the database
    Services ps = new Services();
    ps.supprimer(selectedUser);

    // Remove the selected item from the ListView
    ListU.remove(selectedUser);
    }

    @FXML
    private void clearD(ActionEvent event) {
        clearTextFields();
    }
    private void clearTextFields(){
    
    emailD.clear();
    idD.clear();
    nomD.clear();
    prenomD.clear();
    
}}
