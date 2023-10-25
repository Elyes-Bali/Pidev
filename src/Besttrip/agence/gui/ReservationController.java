package Besttrip.agence.gui;


import Besttrip.agence.entity.Transport;
import Besttrip.agence.entity.User;
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
import Besttrip.agence.services.servicereservation;
import Besttrip.agence.entity.reservationTransport;
import Besttrip.agence.services.ServiceTransport;
import Besttrip.agence.services.Services;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


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
    //retourrr
    
    @FXML
    private TableView<reservationTransport> tableafficher;
    ObservableList<reservationTransport> listeM;
    /*@FXML
    private TableColumn<reservation, Integer> capacol;
    @FXML
    private TableColumn<reservation, Integer> typecol;*/
    @FXML
    private TableColumn<reservationTransport, String> ddcol;
   
    @FXML
    private ComboBox<User> clientComboBox; // Add a ComboBox for selecting clients

    @FXML
    private ComboBox<Transport> transportComboBox; // Add a ComboBox for selecting transports

    private List<User> userList;
    private List<Transport> transportList;

    @FXML
    private TextField rechfield; // TextField for user input

    servicereservation t = new servicereservation();

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
    public void ajouterMoyen(){
         User selectedClient = clientComboBox.getValue(); // Get the selected client
    Transport selectedTransport = transportComboBox.getValue();
        if (selectedClient == null || selectedTransport == null) {
        // Handle the case where either the client or transport is not selected
        // You might want to show an alert or provide some feedback to the user
        return;
    }
        //java.util.Date Date = sp_da.getValue();
        LocalDate localDate = sp_da.getValue();
      
        int tr = selectedTransport.getId(); // Get the ID of the selected transport
        int cl = selectedClient.getIdU();
        Date utildate = java.sql.Date.valueOf(localDate);
        
        servicereservation ps = new servicereservation();
        reservationTransport res = new reservationTransport(tr, cl, utildate);
        
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
         try{ 
        List<reservationTransport> reservationList = t.getAllreservation();
        listeM = FXCollections.observableArrayList(reservationList);
        //capacol.setCellValueFactory(new PropertyValueFactory<>("transport_id"));
        //typecol.setCellValueFactory(new PropertyValueFactory<>("client_id"));
        ddcol.setCellValueFactory(new PropertyValueFactory<>("debutReservation"));
        tableafficher.setItems(listeM);
        // TODO
       }catch(Exception e){
           System.out.println(e.getMessage());
       };
      //retourt.setOnAction(event -> retournerALaPagePrincipale());
      Services userService = new Services();
            userList = userService.afficher();
            ObservableList<User> clientOptions = FXCollections.observableArrayList(userList);
            clientComboBox.setItems(clientOptions);

            // Load the transport list and populate the ComboBox
            ServiceTransport transportService = new ServiceTransport();
            transportList = transportService.getAllTransports();
            ObservableList<Transport> transportOptions = FXCollections.observableArrayList(transportList);
            transportComboBox.setItems(transportOptions);
    }

    /*@FXML
    private void retourpanal(ActionEvent event) {

        TranslateTransition slider = new TranslateTransition();
        if (event.getSource() == sp_ret) {
            slider.setNode(so_form);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.play();
        }
    }*/
@FXML
    private Button gotomodif;
    @FXML
private void modifierReservation(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("ModifierReservationDeux.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) gotomodif.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
@FXML
    private Button retourrr;
    @FXML
private void retournerALaPagePrincipale(ActionEvent event) throws IOException {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listeTransport.fxml"));
        Parent root = loader.load();

        Scene scene = retourrr.getScene();

        scene.setRoot(root);

        Stage stage = (Stage) scene.getWindow();
        stage.setTitle("besttrip");

    } catch (IOException e) {
    }
}
@FXML
    private Button gotodelete;
@FXML
private void supprimerReservation(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("supprimerReservationDeux.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) gotodelete.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}


@FXML
    private void ListeEvent(ActionEvent event) {
    }
    //retourt
    
     
    @FXML
    private void ListeTransp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("listeTransport.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) gototransport.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ListeCircuit(ActionEvent event) {
    }

    @FXML
    private void ListeHeberg(ActionEvent event) {
    }

    @FXML
    private void ListeRec(ActionEvent event) {
    }

    @FXML
    private void ListeForum(ActionEvent event) {
    }

    @FXML
    private void LogoutListeUtilisateur(ActionEvent event) {
    }

}
