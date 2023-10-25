/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import Besttrip.agence.entity.User;
import Besttrip.agence.services.Services;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.CurrentUser;

/**
 * FXML Controller class
 *
 * @author Maroua SANDI
 */
public class ModifierUserController implements Initializable {

    @FXML
    private TextField nomM;
    @FXML
    private TextField PrenomM;
    @FXML
    private TextField emailM;
    @FXML
    private TextField mdpM;
    @FXML
    private TextField telM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         User currentUser = CurrentUser.getCurrentUser();
    
    // Check if the current user is not null
    if (currentUser != null) {
        // Set the text of the labels to display user information
        nomM.setText(currentUser.getNom());
         PrenomM.setText( currentUser.getPrenom());
        emailM.setText( currentUser.getEmail());
        mdpM.setText(currentUser.getMdp());
        
        int tel=currentUser.getTel();
           telM.setText( String.valueOf(tel));
        
       
    } else {
        // Handle the case where there is no current user (optional)
        // For example, you can display a message that no user is logged in.
    }
}
        // TODO
       

    @FXML
    private void ModifierProfil(ActionEvent event) {
    String updatedNom = nomM.getText();
    String updatedPrenom = PrenomM.getText();
    String updatedEmail = emailM.getText();
    String updatedMdp = mdpM.getText();
    int updatedTel = Integer.parseInt(telM.getText());
    //// Accès à l'utilisateur actuel
    User currentUser = CurrentUser.getCurrentUser();
    // Check if the current user is not null
    if (currentUser != null) {
        // Mettre à jour les informations de l'utilisateur actuel avec les valeurs mises à jour
        currentUser.setNom(updatedNom);
        currentUser.setPrenom(updatedPrenom);
        currentUser.setEmail(updatedEmail);
        currentUser.setMdp(updatedMdp);
        currentUser.setTel(updatedTel);
// appele fonction modifier dans pack services
        Services pcd = new Services();
        pcd.modifier(currentUser);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Mettez à jour votre fichier");
        alert.setHeaderText(null);
        alert.setContentText("Votre profil a été mis a jour avec succés");
        alert.showAndWait();

       

        
    } else {
        
       Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Aucun utilisateur n'est connecté. Impossible de mettre à jour les informations utilisateur.");
        alert.showAndWait();
    }
     clearTextFields();

    }
    
    @FXML
    private void retouacceuil(ActionEvent event) {
         try{FXMLLoader loader =new FXMLLoader(getClass().getResource("/Besttrip/agence/gui/Acceuil.fxml"));
        Parent root =loader.load();
        
        AcceuilController starController =loader.getController();
        Scene scene =new Scene (root);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        }catch (IOException ex){
            System.out.println("Error loading .fxml :" + ex.getMessage());
        }
    }
      private void clearTextFields(){
    
    emailM.clear();
    mdpM.clear();
    nomM.clear();
    PrenomM.clear();
    telM.clear();

    
    
}
}
