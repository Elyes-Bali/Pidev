package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField; // Added import
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.Services.ServiceTransport;
import tn.esprit.entity.Transport;

public class AfficherController implements Initializable {

    @FXML
    private Button affichert;
    @FXML
    private Button retourt;
    @FXML
    private TableView<Transport> tableafficher;
    ObservableList<Transport> listeM;
    @FXML
    private TableColumn<Transport, Integer> capacol;
    @FXML
    private TableColumn<Transport, String> typecol;
    @FXML
    private TableColumn<Transport, String> ddcol;
    @FXML
    private TableColumn<Transport, String> dacol;
    @FXML
    private TableColumn<Transport, Integer> prixcol;

    @FXML
    private TextField rechfield; // TextField for user input

    ServiceTransport t = new ServiceTransport();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Transport> TransportList = t.getAllTransports();
            listeM = FXCollections.observableArrayList(TransportList);
            capacol.setCellValueFactory(new PropertyValueFactory<Transport, Integer>("cap"));
            typecol.setCellValueFactory(new PropertyValueFactory<Transport, String>("type"));
            ddcol.setCellValueFactory(new PropertyValueFactory<Transport, String>("dd"));
            dacol.setCellValueFactory(new PropertyValueFactory<Transport, String>("da"));
            prixcol.setCellValueFactory(new PropertyValueFactory<Transport, Integer>("Prix"));
            tableafficher.setItems(listeM);
            // TODO
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void retournerALaPagePrincipale(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Transport.fxml"));
            Parent root = loader.load();

            Scene scene = retourt.getScene();

            scene.setRoot(root);

            Stage stage = (Stage) scene.getWindow();
            stage.setTitle("besttrip");

        } catch (IOException e) {
        }
    }

    @FXML
    private void rechercherTransport(ActionEvent event) {
        String recherche = rechfield.getText(); 

        List<Transport> filteredTransports = filterTransports(recherche);

        listeM.clear();
        listeM.addAll(filteredTransports);
    }

    private List<Transport> filterTransports(String recherche) {
        List<Transport> filteredTransports = new ArrayList<>();

        for (Transport transport : listeM) {
           
            if (transport.getType().toLowerCase().contains(recherche.toLowerCase())
                    || transport.getDd().contains(recherche)
                    || transport.getDa().contains(recherche) )
                    
            {
                filteredTransports.add(transport);
            }
        }

        return filteredTransports;
    }
}
