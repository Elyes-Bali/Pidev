/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import Besttrip.agence.entity.CategoryHebergement;
import Besttrip.agence.entity.Hebergement;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import Besttrip.agence.services.CategoryService;
import Besttrip.agence.services.HebergementService;
import com.twilio.Twilio;
import static com.twilio.example.Example.ACCOUNT_SID;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * FXML Controller class
 *
 * @author Ons
 */
public class AjoutHebergementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private AnchorPane addHebPane;

    @FXML
    private Button btnAddCateg;

    @FXML
    private Button btnClearCateg;

    @FXML
    private Button btnReturnAct;

    @FXML
    private TextField textAdresse;

    @FXML
    private TextField textCap;

    @FXML
    private ComboBox<String> textCateg;

    @FXML
    private TextArea textDescription;

    @FXML
    private TextField textPrix;

    @FXML
    private TextField textType;
    
    
    @FXML
    void return_ListHebergement()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listHebergement.fxml"));
        addHebPane.getChildren().removeAll();
        addHebPane.getChildren().setAll(fxml);
    }
    
    
    CategoryService cs = new CategoryService();
    List<CategoryHebergement> categ = cs.Show();
    private int categId=-1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map<String, Integer> valuesMap = new HashMap<>();
        for(CategoryHebergement c : categ){
            textCateg.getItems().add(c.getContenu());
            valuesMap.put(c.getContenu(),c.getId());
        }
        
        textCateg.setOnAction(event ->{
            String SelectedOption = null;
            SelectedOption = textCateg.getValue();
            int SelectedValue = 0;
            SelectedValue = valuesMap.get(SelectedOption);
            categId = SelectedValue;
        });
    }    
    
    
    @FXML
    private void AjoutHebergement(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddCateg){
            if (textAdresse.getText().isEmpty() || textType.getText().isEmpty() || textPrix.getText().isEmpty() || 
                    textDescription.getText().isEmpty() || textCap.getText().isEmpty()) 
            {    
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre hébergement.");
                Optional<ButtonType> option = alert.showAndWait();
                
            } else {
                ajouterHebergement();
                //send_SMS();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajouté avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre hébergement a été ajoutée avec succès.");
                Optional<ButtonType> option = alert.showAndWait();
                
                clearFieldsHebergement();
            }
        }
        if(event.getSource() == btnClearCateg){
            clearFieldsHebergement();
        }
    }
    
    
    @FXML
    private void clearFieldsHebergement() {
        textAdresse.clear();
        textType.clear();
        textPrix.clear();
        textDescription.clear();
        textCap.clear();
    }
    
    
    private void ajouterHebergement() {
        
        String adresseHeb = textAdresse.getText();
        String typeHeb = textType.getText();
        String descriptionHeb = textDescription.getText();
        float prixHeb = Float.parseFloat(textPrix.getText());
        int capHeb = Integer.parseInt(textCap.getText());
        int category = categId;
        
        Hebergement h = new Hebergement(
                capHeb, prixHeb, adresseHeb, typeHeb, descriptionHeb, category);
        HebergementService hs = new HebergementService();
        hs.ajouter(h);
    }
    
    
    
    void send_SMS(){
        // Initialisation de la bibliothèque Twilio avec les informations de votre compte ;
        String ACCOUNT_SID = "ACc382f9f003776eed47d67e592c17d616";
        String AUTH_TOKEN = "847aa6f0a93959a3de562a1fa8b176bb";
             
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            String recipientNumber = "+21622711171";
            String message = "Bonjour Mr ,\n"
                    + "Nous sommes ravis de vous informer qu'une hébergement a été ajouté.\n "
                    + "Veuillez contactez l'administration pour plus de details.\n "
                    + "Merci de votre fidélité et à bientôt chez BestTrip.\n"
                    + "Cordialement,\n"
                    + "BestTrip 2023";
                
            Message twilioMessage = Message.creator(
                new PhoneNumber(recipientNumber),
                new PhoneNumber("+12293745487"),message).create();
                
            System.out.println("SMS envoyé : " + twilioMessage.getSid());

        
         
     }
    
    
   
}
