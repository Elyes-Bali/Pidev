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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Maroua SANDI
 */
public class AcceuilController implements Initializable {
    @FXML
    private Button event;

    @FXML
    private Button forum;
    @FXML
    private Button reserv;
    @FXML
    private Button deconn;
      @FXML
    private Button gototransport; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    private void MonProfil(ActionEvent event) {
        
    }


   
    @FXML
    private void deconnecter(ActionEvent event) {
    }

    @FXML
    private void EventAcceuil(ActionEvent event) {
    }

    @FXML
    private void TransportAcceuil(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("AffichageTransportDeux.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) gototransport.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void CircuitAcceuil(ActionEvent event) {
    }

    @FXML
    private void HebergementAcceuil(ActionEvent event) {
    }

    @FXML
    private void ReclamationAcceuil(ActionEvent event) {
    }

    @FXML
    private void ForumAcceuil(ActionEvent event) {
    }
    @FXML
    private void gotoreserv(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ReservationUser.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) reserv.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
