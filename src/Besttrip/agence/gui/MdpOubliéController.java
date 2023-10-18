/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maroua SANDI
 */
public class MdpOubliéController implements Initializable {

    @FXML
    private TextField oublieEmail;
    @FXML
    private Button Demande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void demandeMdp(ActionEvent event) {
    }

    @FXML
    private void retourOUBlie(ActionEvent event) {
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
    
}
