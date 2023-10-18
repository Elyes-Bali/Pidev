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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maroua SANDI
 */
public class InscrirController implements Initializable {

    @FXML
    private ImageView imageInscrire;
    @FXML
    private TextField emailInscrir;
    @FXML
    private TextField mdpInscrire;
    @FXML
    private TextField NomU;
    @FXML
    private TextField prenomU;
    @FXML
    private TextField telIscrir;
    @FXML
    private Button sinscrir;
    @FXML
    private Button exit1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Sinscrir(ActionEvent event) {
          String Nom  = NomU.getText();
         String Prenom  = prenomU.getText();
          String email  = emailInscrir.getText();
           String mdp  = mdpInscrire.getText();
           String telFn =telIscrir.getText();
           Services ps =new Services();
           
           int tel;
           try{
           tel = Integer.parseInt(telFn);
           }catch(NumberFormatException e){
           System.out.println("Invalid phone number");
           return;}
           try {
            showAlert(Alert.AlertType.INFORMATION, "Succès", "compte ajoutée avec succès", "");
    }        catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de l'ajout de compte", e.getMessage());
                e.printStackTrace();
            }


           if (Nom.isEmpty() || mdp.isEmpty() || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
        } else if (mdp.length() < 8) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message d'erreur");
            alert.setHeaderText(null);
            alert.setContentText("Mot de passe invalide (doit avoir au moins 8 caractères) !");
            alert.showAndWait();
        } else {
           }
           User u = new User(Nom, Prenom, email, tel, mdp);
           ps.ajouter(u);
           clearTextFields();
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);

    alert.showAndWait();
    
}

    @FXML
    private void exit1(ActionEvent event) {
        try{FXMLLoader loader =new FXMLLoader(getClass().getResource("/Besttrip/agence/gui/Login.fxml"));
        Parent root =loader.load();
        
        LoginController starController =loader.getController();
        Scene scene =new Scene (root);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        }catch (IOException ex){
            System.out.println("Error loading Login.fxml :" + ex.getMessage());
        }
    }
    
    private void clearTextFields(){
    
    emailInscrir.clear();
    mdpInscrire.clear();
    NomU.clear();
    prenomU.clear();
    telIscrir.clear();
    }

   
    }
    

