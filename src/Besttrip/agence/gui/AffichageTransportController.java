/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import Besttrip.agence.entity.Transport;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import Besttrip.agence.services.ServiceTransport;
/**
 *
 * @author PC
 */
public class AffichageTransportController  implements Initializable {
    @FXML
    private GridPane grid;

    @FXML
    private HBox hbox;


    //private MyListener myListener;
    
    @FXML
    private Pagination pag;
    @FXML
    private Button gototransport; 

    @FXML
    private Button reserv;

    @FXML
    private HBox vbox;

     MyListener myListener;
    @FXML
    private Button event;
    @FXML
    private Button forum;
    @FXML
    private Button deconn;
    @FXML
    private Button backend;
    
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceTransport hs = new ServiceTransport();
            List<Transport> heb = hs.getAllTransports();
            pag.setPageCount((int) Math.ceil(heb.size() / 3.0));
            //System.out.print(heb.size());// Nombre total de pages nécessaire pour afficher toutes les cartes
            pag.setPageFactory(pageIndex -> {
                HBox hbox = new HBox();
                hbox.setSpacing(10);
                hbox.setAlignment(Pos.CENTER);
                int itemsPerPage = 3; // Nombre d'hebergement à afficher par page
                int page = pageIndex * itemsPerPage;
                for (int i = page; i < Math.min(page + itemsPerPage, heb.size()); i++) {
                    
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("TransportCard.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                         
                        TransportCardController itemController = fxmlLoader.getController();
                       itemController.setData(heb.get(i), myListener);
                        hbox.getChildren().add(anchorPane);
                        HBox.setMargin(anchorPane, new Insets(10)); // Marges entre les cartes
                    } catch (IOException ex) {
                        Logger.getLogger(AffichageTransportController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                return hbox;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    @FXML
    private void gotoreserv(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ReservationUser.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) reserv.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void backend(ActionEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("listeTransport.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) backend.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
