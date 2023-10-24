/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.Services.ServiceTransport;
import tn.esprit.entity.Transport;
import tn.esprit.entity.reservation;

/**
 * FXML Controller class
 *
 * @author amadd
 */
public class ModifierTransportController implements Initializable {
    

    @FXML
    private TextField capt;

    @FXML
    private TextField ddt;

    @FXML
    private TextField dat;

    @FXML
    private Button retourt;


    @FXML
    private TextField typet;
    
    private ServiceTransport ServiceTransport;
    @FXML
    private TextField prixt;
    @FXML
    private AnchorPane Modifierpanal;
    @FXML
    private TableView<Transport> listTransport;
        ObservableList<Transport> listeM ;

    @FXML
    private TableColumn<Transport,Integer> capmod;
    @FXML
    private TableColumn<Transport, String> typemod;
    @FXML
    private TableColumn<Transport, String> ddmod;
    @FXML
    private TableColumn<Transport, String> damod;
    @FXML
    private TableColumn<Transport, Integer> prixmod;
        ServiceTransport t=new ServiceTransport();
    @FXML
    private Button okmod;
    @FXML
    private Button Modifiert;
    @FXML
    private TextField rechField;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{ 
        List<Transport> TransportList = t.getAllTransports();
        listeM = FXCollections.observableArrayList(TransportList);
        capmod.setCellValueFactory(new PropertyValueFactory<Transport,Integer>("cap"));
        typemod.setCellValueFactory(new PropertyValueFactory<Transport,String>("type"));
        ddmod.setCellValueFactory(new PropertyValueFactory<Transport,String>("dd"));
        damod.setCellValueFactory(new PropertyValueFactory<Transport,String>("da"));
        prixmod.setCellValueFactory(new PropertyValueFactory<Transport,Integer>("Prix"));
        listTransport.setItems(listeM);
       }catch(Exception e){
           System.out.println(e.getMessage());
       };
        ServiceTransport = new ServiceTransport();
         retourt.setOnAction(event -> retournerALaPagePrincipale());
         listTransport.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
        populateFields(newSelection);
    }
});

        
// TODO
    }
    private void populateFields(Transport selectedTransport) {
    rechField.setText(String.valueOf(selectedTransport.getId())); // If you have an "id" property
    ddt.setText(selectedTransport.getDd());
    dat.setText(selectedTransport.getDa());
    capt.setText(String.valueOf(selectedTransport.getCap()));
    typet.setText(selectedTransport.getType());
    prixt.setText(String.valueOf(selectedTransport.getPrix()));
}

//     @FXML
//    private void modifiertransport (){
//        Transport selectedTransport = listTransport.getSelectionModel().getSelectedItem();
//    if (selectedTransport == null) {
//        showAlert("Sélectionnez un transport", "Veuillez sélectionner un transport à modifier.");
//        return;
//    }
//
//    // Récupérez les nouvelles valeurs des champs
//    String newDd = ddt.getText();
//    String newDa = dat.getText();
//    int newCap = Integer.parseInt(capt.getText());
//    String newType = typet.getText();
//    int newPrix = Integer.parseInt(prixt.getText());
//
//    ServiceTransport serviceTransport = new ServiceTransport();
//    selectedTransport.setDd(newDd);
//    selectedTransport.setDa(newDa);
//    selectedTransport.setCap(newCap);
//    selectedTransport.setType(newType);
//    selectedTransport.setPrix(newPrix);
//    serviceTransport.updateTransport(selectedTransport);
//
//    listTransport.refresh();
//    showAlerts("Modification avec succés", "Votre Transport a été modifié Avec succés.");
//
//    
//    rechField.clear();
//    ddt.clear();
//    dat.clear();
//    capt.clear();
//    typet.clear();
//    prixt.clear();
//}
//
//private void showAlert(String title, String content) {
//    Alert alert = new Alert(AlertType.ERROR);
//    alert.setTitle(title);
//    alert.setContentText(content);
//    alert.showAndWait();
//}
//private void showAlerts(String title, String content) {
//    Alert alert = new Alert(AlertType.CONFIRMATION);
//    alert.setTitle(title);
//    alert.setContentText(content);
//    alert.showAndWait();
//}          
//    private void resetFields() {
//        ddt.clear();
//        dat.clear();
//        capt.clear();
//        typet.clear();    }
    @FXML
private void modifiertransport() {
    Transport NewTransport = listTransport.getSelectionModel().getSelectedItem();
    
    ServiceTransport transportdao = new ServiceTransport();
    
    Transport selectedTransport = transportdao.getTransportParID(NewTransport.getId());
    System.out.print(NewTransport.getId());
    System.out.print(selectedTransport.getId());
    if (selectedTransport == null) {
        showAlert("Sélectionnez un transport", "Veuillez sélectionner un transport à modifier.");
        return;
    }

    String newDd = ddt.getText();
    String newDa = dat.getText();
    String newType = typet.getText();
    int newCap = 0;
    int newPrix = 0;
    System.out.print(newDd);
    System.out.print(newDa);
    System.out.print(newType);
    
    
    try {
        newCap = Integer.parseInt(capt.getText());
        newPrix = Integer.parseInt(prixt.getText());
    } catch (NumberFormatException e) {
        showAlert("Erreur de saisie", "Veuillez entrer des valeurs numériques valides pour Capacité et Prix.");
        return;
    }
    System.out.print(newCap);
    System.out.print(newPrix);
    //getTransportParID
    
    //int idTransport = transportdao.getTransportParID(selectedTransport.getId());
    // Show a confirmation dialog
    boolean confirmed = showConfirmationAlert("Confirmation de modification", "Voulez-vous vraiment modifier ce transport ?");
    
    if (confirmed) {
        // Update the transport in the database
        Transport newTr = new Transport(selectedTransport.getId(),newCap, newType, newDd, newDa, newPrix);
        /*selectedTransport.setDd(newDd);
        selectedTransport.setDa(newDa);
        selectedTransport.setCap(newCap);
        selectedTransport.setType(newType);
        selectedTransport.setPrix(newPrix);*/
        transportdao.updateTransports(newTr);

        System.out.println("Update completed");
        listTransport.refresh();
    }
}

private boolean showConfirmationAlert(String title, String content) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setContentText(content);
    Optional<ButtonType> result = alert.showAndWait();
    return result.orElse(ButtonType.CANCEL) == ButtonType.OK;
}

private void showAlert(String title, String content) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setContentText(content);
    alert.showAndWait();
}

private void clearFields() {
    rechField.clear();
    ddt.clear();
    dat.clear();
    capt.clear();
    typet.clear();
    prixt.clear();
}


    private void showAlert(AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    @FXML
    private void retournerALaPagePrincipale() {
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
    private void searchTransport(ActionEvent event) {
         String recherche = rechField.getText(); 

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
    

