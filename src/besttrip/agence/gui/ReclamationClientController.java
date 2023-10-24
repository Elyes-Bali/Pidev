/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.agence.gui;

import besttrip.agence.entity.Reclamation;
import besttrip.agence.services.Servicesy;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author zouar
 */
public class ReclamationClientController implements Initializable {

    @FXML
    private TextField intituleRecClient;
    @FXML
    private TextField messageRecClient;
    @FXML
    private Button AcceuilRec;
    @FXML
    private WebView webview;
    private WebEngine webEngine;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      

    // Load the HTML page containing


        // TODO
        webEngine = webview.getEngine();
    // Load the HTML page containing the reCAPTCHA widget
   webEngine.load(getClass().getResource("/besttrip/agence/gui/newhtml.html").toExternalForm());
    }    

    @FXML
    private void buttonAcceuilRecClient(ActionEvent event) {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
//            Parent root = loader.load();
//            AcceuilController dc = loader.getController();
//            AcceuilRec.getScene().setRoot(root);
    }
//apiBadwords
    private String filterText(String text) {
        // Les mots à filtrer
        String[] filteredWords = {"mauvais mot", "débile", "bete", "stupid", "un con","hot","sexy", "chaud", "viagra", "cure", "sexuel", "amour" };

        for (String word : filteredWords) {
            String replacement = "";
            for (int i = 0; i < word.length(); i++) {
                replacement += "*";
            }
            text = text.replaceAll("(?i)\\b" + word + "\\b", replacement);
        }

        return text;
    }
    @FXML
    private void buttonEnvoyerRecClient(ActionEvent event) {
          String intitule  = intituleRecClient.getText();        
        String textRec  = messageRecClient.getText();        
        String s1 = filterText(messageRecClient.getText());
boolean isCaptchaValid = true;

    if (isCaptchaValid) {
        Servicesy ps = new Servicesy();
  
        Reclamation p = new Reclamation(intitule,textRec,1102,"youssef.zouari@esprit.com");
        
       p.setTextRec(s1);
        ps.ajouter(p);
//           List<Reclamation> updatedCircuits = ps.afficher();
//
//    // Update circuitList with the new data
//    circuitList.clear();
//    circuitList.addAll(updatedCircuits);
//
//    // Set the ListView items to circuitList to reflect the updated data
//    listv.setItems(circuitList);
clearTextFields();
    // Clear the TextFields after saving
    try {
    showAlert(Alert.AlertType.INFORMATION, "Succès", "Réclamation ajoutée avec succès", "");
    }    catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de l'ajout de Réclamation", e.getMessage());
                e.printStackTrace();
            }
//         else {
//            showAlert(Alert.AlertType.INFORMATION, "", "Remplissez tous les champs", "");
//     }   }
    }}
    
    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);

    alert.showAndWait();
}
      private void clearTextFields() {
    intituleRecClient.clear();
    messageRecClient.clear();
    

}

    @FXML
    private void buttonLogoutRecClient(ActionEvent event) {
    }
}
