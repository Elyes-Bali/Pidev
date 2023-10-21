/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package besttrip.gui;

import besttrip.entity.Evenement;
import besttrip.service.ServiceEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.Date;
import java.time.ZoneId;
import java.time.Instant;
import java.util.Optional;
import javafx.scene.control.ButtonType;

 
public class AjouterEventsController implements Initializable {

public enum Type {
    SPORT(1, "SPORT"),
    CULTURE(2, "CULTURE"),
    LOISIRS(3, "LOISIRS"),
    AVENTURE(4, "AVENTURE"),
    BIENETRE(5, "BIENÊTRE"),
    AUTRE(6, "AUTRE");

    private final int id;
    private final String libelle;

    Type(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}

    //nom
    
    @FXML
    private TextField t1;
    	
	@FXML
	private Stage stage;
    //dateevents

        @FXML
       private DatePicker t2;
     //description 
      @FXML
    private TextArea  t3;
       @FXML
    private ComboBox<Type> t4;
       //lieu
        @FXML
    private TextField t5;
        //image
         @FXML
    private TextField t6;
         //tarif
          @FXML
    private TextField t7;
          //placedipsonible
              @FXML
    private TextField t8;
              
              	@FXML
	private ImageView imageview;
                
	private File file;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        t4.setItems(FXCollections.observableArrayList(Type.values()));

    } 
     @FXML
void Ajouter(ActionEvent event) {
    int id_categorie = -1; // Default value in case nothing is selected

    String nom, desc, lieu, tarif;
     int palce_dispo;
     Date date1;

    if (t1.getText().isEmpty() || t5.getText().isEmpty()   || t3.getText().isEmpty() || t7.getText().isEmpty() || t8.getText().isEmpty() || t2.getValue() == null || t4.getValue() == null) {
        // Afficher une alerte si un champ est vide
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Champs vides");
        alert.setHeaderText("Veuillez remplir tous les champs.");
        alert.showAndWait();
     }
    else{
      

    try {
         Evenement event1 = new Evenement();
        nom = t1.getText();
        lieu = t5.getText();
        desc = t3.getText();
       Double prix = Double.parseDouble(t7.getText());
        palce_dispo = Integer.parseInt(t8.getText()); // Conversion en int
           LocalDate localDate = t2.getValue(); // Get the selected LocalDate from the DatePicker
        java.sql.Date dateDebut = java.sql.Date.valueOf(localDate);

        // Ici, vous pouvez convertir les champs en leurs types respectifs (Double, Date, etc.)
          if (t4.getSelectionModel().getSelectedItem() != null) {
              id_categorie = t4.getSelectionModel().getSelectedItem().id;
              System.out.println(id_categorie);
              event1.setCategorieId(id_categorie);
            }
        else {
                // Handle the case where nothing is selected (e.g., show an error message)
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Catégorie non sélectionnée");
                alert.setHeaderText("Veuillez sélectionner une catégorie.");
                alert.showAndWait();
                }
        event1.setNom(nom);
        event1.setDateDebut(dateDebut);
           event1.setDescription(desc);
           event1.setImage(file.getPath());
           event1.setTarif(prix);
           event1.setLieu(lieu);
           event1.setPlacesDisponibles(palce_dispo);
        // Ensuite, créez l'objet Evenement et effectuez l'opération souhaitée (ajout, modification, etc.).
         try{
         ServiceEvent bs =new ServiceEvent();
         bs.ajouter(event1);
         Alert alert2 = new Alert(AlertType.CONFIRMATION);
			alert2.setTitle("Information Dialog");
			alert2.setHeaderText(null);
			alert2.setContentText("Event insérée avec succès!");

			Optional<ButtonType> result = alert2.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				try {

					FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/EventInterface.fxml"));

					Parent page2 = loader.load();
					EventInterfaceController gbController = loader.getController();

					//gbController.setBoutique(nouvelleBoutique);
					Scene scene = new Scene(page2);
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) 
                                {
					e.printStackTrace();
				}}
        // ... Autres attributs de l'événement
        }catch(Error ex){
             // En cas d'erreur de conversion de chaîne en nombre
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur d'ajout");
        alert.setHeaderText("Veuillez Verifeir services !!!!!.");
        alert.showAndWait();
        }
        
    } catch (NumberFormatException e) {
        // En cas d'erreur de conversion de chaîne en nombre
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Veuillez saisir des valeurs valides pour le tarif et le nombre de places disponibles.");
        alert.showAndWait();
    }
    }
    // Réinitialisez les champs après l'ajout
    t1.clear();
    t2.setValue(null);
    t3.clear();
    t4.setValue(null);
    t5.clear();
    t6.clear();
    t7.clear();
    t8.clear();
     
    // Effectuez l'ajout de l'événement dans la base de données
}

     @FXML
    void back(ActionEvent event) {
        try {
              Parent page1 = FXMLLoader.load(getClass().getResource("../gui/EventInterface.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(EventInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
}
    
    	@FXML
	private void importer(ActionEvent event) {
		
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionnez un fichier PNG");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg"));
        File fichierSelectionne = fileChooser.showOpenDialog(stage);

        if (fichierSelectionne != null) {
        	t6.setText(fichierSelectionne.getName());
                  file = fichierSelectionne;
        }
		
	}
}
