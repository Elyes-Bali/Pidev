/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;
import Besttrip.agence.entity.Transport;
import Besttrip.agence.entity.reservationTransport;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import Besttrip.agence.services.servicereservation;
import java.util.Random;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javafx.scene.image.Image;
import util.CurrentUser;
import Besttrip.agence.entity.User;
import Besttrip.agence.services.Services;

/**
 *
 * @author PC
 */
public class TransportCardController implements Initializable {
    private Services pcd ;
    @FXML
    private Label labelCapacite;


    @FXML
    private Label labelPrix;

    @FXML
    private Label labelType;
    
    @FXML
    private Button btnReserver;
    
    
    @FXML
    private Label dd;
 
    
    MyListener myListener;
    @FXML
private ImageView typeimg;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    Transport heb;
    Random random = new Random();
    
    private void ajouterReservation(Transport h) {
        
        int id_transport = h.getId();
        LocalDateTime currentDateTime = LocalDateTime.now();;
        Date dateReservation = java.sql.Timestamp.valueOf(currentDateTime);
        User user = CurrentUser.getCurrentUser();
        int idclient = user.getIdU();
        System.out.print(idclient);
        reservationTransport r = new reservationTransport(id_transport,idclient, dateReservation);
        
        servicereservation rs = new servicereservation();
       /* if (r == null) {
        throw new NullPointerException("Reservation cannot be null");
    }*/
        rs.addreservation(r);
        
       
        Notifications.create()
    .title("Nouvelle reservation ajouté")
    .text("Votre réservation a été enregistrée !")
    .darkStyle() 
                .hideAfter(Duration.seconds(2))
                .showInformation();
        
    }
    public void setData(Transport h, MyListener myListener){
        this.heb = h ;
        //typeimg
        this.myListener = myListener;
       String imagePath = "/images/";
        dd.setText(String.valueOf(h.getDd()));
        labelPrix.setText(String.valueOf(h.getPrix())+" DT  |  ");
        labelCapacite.setText(String.valueOf(h.getCap()));
        labelType.setText(h.getType());
        switch (h.getType().toLowerCase()) {
        case "avion":
            typeimg.setImage(new Image(getClass().getResourceAsStream(imagePath + "avion.png")));
            break;
        case "bateau":
            typeimg.setImage(new Image(getClass().getResourceAsStream(imagePath + "bateau.png")));
            break;
        case "metro":
            typeimg.setImage(new Image(getClass().getResourceAsStream(imagePath + "metro.png")));
            break;
        case "voiture":
            typeimg.setImage(new Image(getClass().getResourceAsStream(imagePath + "voiture.png")));
            break;
            case "train":
            typeimg.setImage(new Image(getClass().getResourceAsStream(imagePath + "train.png")));
            break;
        default:
            // If type doesn't match any of the above, you can set a default image here
            typeimg.setImage(new Image(getClass().getResourceAsStream(imagePath + "transportation.png")));
            break;
    }
        //ajouterReservation(h);
    }
    
    @FXML
    void ajoutRes(ActionEvent event){
        ajouterReservation(this.heb);
    }
}
