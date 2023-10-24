/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;

import jxl.write.WritableWorkbook;
import org.controlsfx.control.Notifications;
import tn.esprit.Services.ServiceTransport;
import tn.esprit.entity.Transport;

/**
 * FXML Controller class
 *
 * @author amadd
 */
public class TransportController implements Initializable {

  @FXML
    private Button si_add;

    @FXML
    private Button si_delete;

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
    private TextField sp_da;

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
    private ServiceTransport ServiceTransport;
    
    @FXML
    private TextField fieldcap;
   
    @FXML
    private TextField sp_prix;
    @FXML
    private Label sp_datea1;
    @FXML
    private Button sp_ret;
    @FXML
    private Button gotoreserv;
   
    
    @FXML
    private Button gotostats;
    
    
    
  @FXML
    public void switchForm(ActionEvent event){
     
    TranslateTransition slider = new TranslateTransition();
    if (event.getSource()== si_add){
        slider.setNode(so_form);
        slider.setToX(300);
        slider.setDuration(Duration.seconds(.5));
        
        slider.play();
    }
    
    
    //handleStatsButtonClicked
    
    
    }
    
     @FXML
    public void handleStatsButtonClicked(ActionEvent event)  throws IOException{
     
    Parent root = FXMLLoader.load(getClass().getResource("transportStats.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) gotostats.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    
    @FXML
    public void ajouterMoyen(){
        String type = sp_type.getText();
        String dd = sp_dd.getText();
        String da = sp_da.getText();
        String capaciteText = fieldcap.getText();
        int cap;
        try {
        cap = Integer.parseInt(capaciteText);
    } catch (NumberFormatException e) {
       
        System.out.println("valeur invalid");
        return;
    }
        String prixText = sp_prix.getText();
        int prix;
        try {
        prix = Integer.parseInt(prixText);
    } catch (NumberFormatException e) {
        
        System.out.println("valeur invalid");
        return;
    }
         if (!validateTransportInputs(type, dd, da, cap, prix)) {
        return; // If validation fails, return without adding the transport
    }
        ServiceTransport ps = new ServiceTransport();
        Transport transport = new Transport(cap, type, dd, da, prix);
        
        ps.addTransport(transport);
        
        sp_type.clear();
        sp_dd.clear();
        sp_da.clear();
        fieldcap.clear() ;
        sp_prix.clear();
        Notifications.create()
            .title("Le transport a été ajouté")
            .text("Transport de la "+ dd + ", a été ajouté avec succès.")
            .hideAfter(Duration.seconds(5))
            .showError();
        
    }
   public boolean validateTransportInputs(String type, String dd, String da, int capacite, int prix) {
    // Validate type
    List<String> allowedTypes = Arrays.asList("bateau", "voiture", "metro", "train", "avion");
    if (!allowedTypes.contains(type.toLowerCase())) {
        Notifications.create()
            .title("Type non valide")
            .text("Le type doit être l'un des suivants : bateau, voiture, métro, train, avion.")
            .hideAfter(Duration.seconds(5))
            .showError();
        return false;
    }

    // Validate date formats and order
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH-mm");
    try {
        LocalDateTime ddDateTime = LocalDateTime.parse(dd, formatter);
        LocalDateTime daDateTime = LocalDateTime.parse(da, formatter);

        // Check if da is after dd
        if (daDateTime.isBefore(ddDateTime)) {
            Notifications.create()
                .title("Dates non valides")
                .text("La date de départ doit être antérieure à la date d'arrivée")
                .hideAfter(Duration.seconds(5))
                .showError();
            return false;
        }

        // Check if dates are in the future
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (ddDateTime.isBefore(currentDateTime) || daDateTime.isBefore(currentDateTime)) {
            Notifications.create()
                .title("Dates non valides")
                .text("Les dates doivent se situer dans le futur")
                .hideAfter(Duration.seconds(5))
                .showError();
            return false;
        }
    } catch (DateTimeParseException e) {
        Notifications.create()
            .title("Format de date invalide")
            .text("Le format de la date doit être : jj/MM/aaaa HH-mm")
            .hideAfter(Duration.seconds(5))
            .showError();
        return false; // Invalid date format
    }

    // Validate capacite (should be a positive integer)
    if (capacite <= 0) {
        Notifications.create()
            .title("Capacité non valide")
            .text("La capacité doit être un nombre entier positif")
            .hideAfter(Duration.seconds(5))
            .showError();
        return false;
    }

    // Validate prix (should be a positive integer)
    if (prix <= 0) {
        Notifications.create()
            .title("Prix non valide")
            .text("Le prix doit être un nombre entier positif")
            .hideAfter(Duration.seconds(5))
            .showError();
        return false;
    }

    return true; // All validations passed
}


//    public void ajouterMoyen() {
//    String type = sp_type.getText();
//    String dd = sp_dd.getText();
//    String da = sp_da.getText();
//    String capaciteText = sp_cap.getText();
//
//    if (!capaciteText.isEmpty()) {
//        try {
//            int cap = Integer.parseInt(capaciteText);
//            Transport transport = new Transport(cap, type, dd, da);
//            ServiceTransport.addTransport(transport);
//
//            sp_type.clear();
//            sp_dd.clear();
//            sp_da.clear();
//            sp_cap.clear();
//
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Succès");
//            alert.setHeaderText(null);
//            alert.setContentText("Le moyen a été ajouté avec succès.");
//            alert.showAndWait();
//        } catch (NumberFormatException e) {
//            // Handle the case where the input is not a valid integer.
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText("La capacité doit être un nombre entier.");
//            alert.showAndWait();
//        }
//    } else {
//        // Handle the case where the input is empty.
//        Alert alert = new Alert(AlertType.ERROR);
//        alert.setTitle("Erreur");
//        alert.setHeaderText(null);
//        alert.setContentText("La capacité ne peut pas être vide.");
//        alert.showAndWait();
//    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceTransport = new ServiceTransport();
        // TODO
    }    

    @FXML
    private void retourpanal(ActionEvent event) {
       
    TranslateTransition slider = new TranslateTransition();
    if (event.getSource()== sp_ret){
    slider.setNode(so_form);
    slider.setToX(0); // Set it to the initial state (0 or whatever your initial state is).
    slider.setDuration(Duration.seconds(.5));
    
    slider.play();
}

    }

    @FXML
    private void modifierTransport(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ModifierTransport.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) si_update.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void supprimerTransport(ActionEvent event) throws IOException{
   Parent root = FXMLLoader.load(getClass().getResource("SupprimerTransport.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) si_delete.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    private void gotoreserv(ActionEvent event) throws IOException{
   Parent root = FXMLLoader.load(getClass().getResource("reservation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) gotoreserv.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void chercherpanel(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Afficher.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) si_search.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        @FXML
    public void importExcel() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Excel File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel Files", "*.xls", "*.xlsx"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            try {
                Workbook workbook = Workbook.getWorkbook(selectedFile);
                Sheet sheet = workbook.getSheet(0);

                ServiceTransport transportdao = new ServiceTransport();

                // Start reading from row 1, since row 0 contains the column headers
                for (int i = 1; i < sheet.getRows(); i++) {
                    int capacity =  Integer.parseInt(sheet.getCell(0, i).getContents());
                    String type = sheet.getCell(1, i).getContents();
                    String dd = sheet.getCell(2, i).getContents();
                   
                    String da = sheet.getCell(3, i).getContents();
                    int prix = Integer.parseInt(sheet.getCell(4, i).getContents());
                    
                    Transport transport = new Transport(capacity, type, dd, da, prix);
                   
                   
                    transportdao.addTransport(transport);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Import successfull!");
                    alert.setHeaderText("Transport imported.");

                    alert.showAndWait();

                }

                workbook.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void exportExcel() {
        // create a file chooser
        FileChooser fileChooser = new FileChooser();

        // set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xls)", "*.xls");
        fileChooser.getExtensionFilters().add(extFilter);

        // show save dialog
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                // create a writable workbook
                WritableWorkbook workbook = Workbook.createWorkbook(file);

                // create a writable sheet
                WritableSheet sheet = workbook.createSheet("Transport", 0);

                // add column headers
                sheet.addCell(new jxl.write.Label(0, 0, "id"));
                sheet.addCell(new jxl.write.Label(1, 0, "capacity"));
                sheet.addCell(new jxl.write.Label(2, 0, "type"));
                sheet.addCell(new jxl.write.Label(3, 0, "Date debut"));
                sheet.addCell(new jxl.write.Label(4, 0, "Date fin"));
                sheet.addCell(new jxl.write.Label(5, 0, "prix"));


                // get all the joueurs from the database
                List<Transport> transportdao = ServiceTransport.getAllTransports();

                // add joueurs data to the sheet
                int row = 1;
                for (Transport transport : transportdao) {
                    sheet.addCell(new jxl.write.Label(0, row, Integer.toString(transport.getId())));
                    sheet.addCell(new jxl.write.Label(1, row, Integer.toString(transport.getCap())));
                    sheet.addCell(new jxl.write.Label(2, row, transport.getType()));
                    sheet.addCell(new jxl.write.Label(3, row, transport.getDd()));

                    sheet.addCell(new jxl.write.Label(4, row, transport.getDa()));
                    sheet.addCell(new jxl.write.Label(5, row, Integer.toString(transport.getPrix())));

                   

                    row++;
                }

                // write and close the workbook
                workbook.write();
                workbook.close();

                // show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Export to Excel");
                alert.setHeaderText(null);
                alert.setContentText("Data exported to " + file.getAbsolutePath());
                alert.showAndWait();

            } catch (Exception e) {
                e.printStackTrace();
                // show error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Export to Excel");
                alert.setHeaderText(null);
                alert.setContentText("Error: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }
}
