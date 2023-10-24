/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import Besttrip.agence.entity.CategoryHebergement;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import Besttrip.agence.services.CategoryService;

/**
 * FXML Controller class
 *
 * @author Ons
 */
public class AjoutCategoryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane addCategRec;

    @FXML
    private Button btnAddCateg;

    @FXML
    private Button btnClearCateg;

    @FXML
    private Button btnReturnCateg;

    @FXML
    private TextArea textContenuCateg;
    
    
    @FXML
    void return_ListCategoryReclamation()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listCateg.fxml"));
        addCategRec.getChildren().removeAll();
        addCategRec.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void AjoutCategory(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddCateg){
            if (textContenuCateg.getText().isEmpty()) 
            {    
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre catégorie.");
                Optional<ButtonType> option = alert.showAndWait();
                
            } else {
                ajouterCategory();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajouté avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre catégorie a été ajoutée avec succès.");
                Optional<ButtonType> option = alert.showAndWait();
                
                clearFieldsCategory();
            }
        }
        if(event.getSource() == btnClearCateg){
            clearFieldsCategory();
        }
    }
    
    
    @FXML
    private void clearFieldsCategory() {
        textContenuCateg.clear();
    }
    
    
    private void ajouterCategory() {
        
        String contenuCateg = textContenuCateg.getText();
        
        CategoryHebergement c = new CategoryHebergement(contenuCateg);
        CategoryService cs = new CategoryService();
        cs.ajouter(c);
    }
    
}
