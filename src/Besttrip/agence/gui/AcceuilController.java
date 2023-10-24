/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import Besttrip.agence.entity.User;
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
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.CurrentUser;

/**
 * FXML Controller class
 *
 * @author Maroua SANDI
 */
public class AcceuilController implements Initializable {
    @FXML
    private Button event;

    @FXML
    private Button home;
    @FXML
    private Button forum;
    @FXML
    private Button deconn;
       

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void MonProfil(ActionEvent event) {
         try{FXMLLoader loader =new FXMLLoader(getClass().getResource("/Besttrip/agence/gui/ModifierUser.fxml"));
        Parent root =loader.load();
        
        ModifierUserController starController =loader.getController();
        Scene scene =new Scene (root);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        }catch (IOException ex){
            System.out.println("Error loading ModifierUser.fxml :" + ex.getMessage());
        }
    }


    void setUserInformation(User user) {
      
    }

    @FXML
    private void event(ActionEvent event) {
    }

    @FXML
    private void transport(ActionEvent event) {
    }

    @FXML
    private void circuit(ActionEvent event) {
    }

    @FXML
    private void hebergement(ActionEvent event) {
    }

    @FXML
    private void reclamation(ActionEvent event) {
    }

    @FXML
    private void forum(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) {
    }
    
}
