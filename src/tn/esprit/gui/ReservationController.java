package tn.esprit.gui;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.Services.servicereservation;
import tn.esprit.entity.reservation;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;


public class ReservationController implements Initializable {

    @FXML
    private Button si_add;

    @FXML
    private Button si_delete;
@FXML
    private Button gototransport;
    @FXML
    private Label si_g;

    @FXML
    private AnchorPane si_gform;

    @FXML
    private Button si_search;

    @FXML
    private Button si_update;

    @FXML
    private AnchorPane so_form;

    @FXML
    private Label sp_aj;

    @FXML
    private Label sp_capacite;

    @FXML
    private DatePicker sp_da;

    @FXML
    private Label sp_datea;

    @FXML
    private Label sp_dated;

    @FXML
    private TextField sp_dd;

    @FXML
    private AnchorPane sp_form;

    @FXML
    private Label sp_ty;

    @FXML
    private TextField sp_type;

    @FXML
    private Button sp_valide;

    @FXML
    private servicereservation servicereservation;

    @FXML
    private TextField fieldcap;

    @FXML
    private TextField sp_prix;

    @FXML
    private Label sp_datea1;

    @FXML
    private Button sp_ret;

    @FXML
    public void switchForm(ActionEvent event) {

        TranslateTransition slider = new TranslateTransition();
        if (event.getSource() == si_add) {
            slider.setNode(so_form);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));

            slider.play();
        }
    }
    
    @FXML
    public void ajouterReserv(){
        String id_transport = sp_type.getText();
        String id_client = sp_dd.getText();
        
        LocalDate localDate = sp_da.getValue();
        int tr;
        try {
        tr = Integer.parseInt(id_transport);
    } catch (NumberFormatException e) {
       
        System.out.println("valeur invalid");
        return;
    }
       
        int cl;
        try {
        cl = Integer.parseInt(id_client);
    } catch (NumberFormatException e) {
        
        System.out.println("valeur invalid");
        return;
    }
         Date date = Date.valueOf(localDate);
        servicereservation ps = new servicereservation();
        reservation res = new reservation(tr, cl, date);
    
        ps.addreservation(res);
        
        sp_type.clear();
        sp_dd.clear();
        sp_da.setValue(null);
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("La reservation a été ajouté avec succès.");
        alert.showAndWait();
        
    }

    

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        servicereservation = new servicereservation();
    }

    @FXML
    private void retourpanal(ActionEvent event) {

        TranslateTransition slider = new TranslateTransition();
        if (event.getSource() == sp_ret) {
            slider.setNode(so_form);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.play();
        }
    }

    @FXML
private void modifierReservation(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("ModifierReservation.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) si_update.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}

@FXML
private void supprimerReservation(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("supprimerReservation.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) si_delete.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
@FXML
private void gototransport(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Transport.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) gototransport.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}



}
