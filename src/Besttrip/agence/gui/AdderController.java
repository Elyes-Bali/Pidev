/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Besttrip.agence.entity.Circuit;
import Besttrip.agence.services.ServiceCircuit;

/**
 * FXML Controller class
 *
 * @author balia
 */
public class AdderController implements Initializable {
    @FXML
    private TextField start;
    @FXML
    private TextField end;
    @FXML
    private TextField time;
    @FXML
    private TextField price;
    
    @FXML
    private TextField category;
    
     private ObservableList<Circuit> circuitList = FXCollections.observableArrayList();
   
    @FXML
    private TextArea descripting;
  
    @FXML
    private Button idDelete;
    @FXML
    private Button idEdite;
  
    @FXML
    private Button idAjoute;
 
  
    @FXML
    private TableView<Circuit> idTable;
    @FXML
    private TableColumn<Circuit, String> departColumn;
    @FXML
    private TableColumn<Circuit, String> arriveColumn;
    @FXML
    private TableColumn<Circuit, String> payscolumn;
    @FXML
    private TableColumn<Circuit, String> prixColumn;
    @FXML
    private TableColumn<Circuit, String> tempsColumn;
    @FXML
    private TableColumn<Circuit, String> categorieColumn;
    @FXML
    private TableColumn<Circuit, String> descriptionColumn;
    @FXML
    private TextField country;
    
    @FXML
    private TextField Trecherche;
    
   
  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadInitialDataFromDatabase();
        idTable.setItems(circuitList);
         departColumn.setCellValueFactory(new PropertyValueFactory<>("depart"));
arriveColumn.setCellValueFactory(new PropertyValueFactory<>("arrive"));
payscolumn.setCellValueFactory(new PropertyValueFactory<>("pays"));
tempsColumn.setCellValueFactory(new PropertyValueFactory<>("temps"));
prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));
descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));


        
         idTable.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2) { // Double-click event
            Circuit selectedCircuit = idTable.getSelectionModel().getSelectedItem();
            if (selectedCircuit != null) {
                // Populate the TextFields with the selected item's data
                start.setText(selectedCircuit.getDepart());
                end.setText(selectedCircuit.getArrive());
                country.setText(selectedCircuit.getPays());
                time.setText(selectedCircuit.getTemps());
                price.setText(Integer.toString(selectedCircuit.getPrix()));
                category.setText(selectedCircuit.getCategorie());
                descripting.setText(selectedCircuit.getDescription());                
                

            }
        }
    });
         
         ImageView imageView1 = new ImageView(getClass().getResource("/images/trash1.png").toExternalForm());
         idDelete.setGraphic(imageView1);
          ImageView imageView2 = new ImageView(getClass().getResource("/images/editing1_1.png").toExternalForm());
         idEdite.setGraphic(imageView2);
          ImageView imageView3 = new ImageView(getClass().getResource("/images/adder1.png").toExternalForm());
         idAjoute.setGraphic(imageView3);
        // TODO
    }    
      private void loadInitialDataFromDatabase() {
    ServiceCircuit ps = new ServiceCircuit();
    List<Circuit> initialCircuits = ps.afficher();    
   

    
    
    // Populate circuitList with the initial data from the database
    circuitList.clear();
    circuitList.addAll(initialCircuits);
}


      
   @FXML
private void saveEditedCircuit(ActionEvent event) {
       Circuit selectedCircuit = idTable.getSelectionModel().getSelectedItem();

    if (selectedCircuit == null) {
        System.out.println("Aucun élément sélectionné pour modification.");
        return;
    }

    selectedCircuit.setDepart(start.getText());
    selectedCircuit.setArrive(end.getText());
    selectedCircuit.setTemps(time.getText());  // This corresponds to "temps" in the Circuit object
    selectedCircuit.setDescription(descripting.getText());
    
    // Corrected mapping: set "categorie" from the "category" TextField
    selectedCircuit.setCategorie(category.getText());

    // "pays" corresponds to the "country" TextField
    selectedCircuit.setPays(country.getText());

    try {
        int editedPrix = Integer.parseInt(price.getText());
        selectedCircuit.setPrix(editedPrix);
    } catch (NumberFormatException e) {
        System.out.println("Valeur du prix invalide: " + price.getText());
        return;
    }

    selectedCircuit.setId(selectedCircuit.getId());

    ServiceCircuit ps = new ServiceCircuit();
    ps.modifier(selectedCircuit);

    clearTextFields();
    idTable.refresh();

}
    


    @FXML
    private void addCircuit(ActionEvent event) {
        String depart = start.getText();
    String arrive = end.getText();
    String temps = time.getText();
    String prixStr = price.getText();
    String categorie = category.getText();
    String description = descripting.getText();    
    String pays = country.getText();


   

    int prix;
    try {
        prix = Integer.parseInt(prixStr);
    } catch (NumberFormatException e) {
        // Handle the case where prixStr is not a valid integer
       displayAlert("Input Invalide", "La valeur du prix n'est pas un entier valide.");
        
        return;
    }
     // Validate input
     // Validate input
    if (depart.isEmpty() || arrive.isEmpty() || temps.isEmpty() || prixStr.isEmpty() || categorie.isEmpty() || description.isEmpty() || pays.isEmpty()) {
        displayAlert("Information manquante", "Merci de remplir tous les champs.");
        return;
    }

    ServiceCircuit ps = new ServiceCircuit();
    Circuit p = new Circuit(prix, depart, arrive, temps, categorie, description, pays);

    ps.ajouter(p);
    List<Circuit> updatedCircuits = ps.afficher();

    // Update circuitList with the new data
    circuitList.clear();
    circuitList.addAll(updatedCircuits);

    // Set the ListView items to circuitList to reflect the updated data
    idTable.setItems(circuitList);
    System.out.println("Success");

    // Clear the TextFields after saving
    clearTextFields();
    
    }
    
    private void displayAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}

    @FXML
    private void DeleteCircuit(ActionEvent event) {
        // Retrieve the selected item from the ListView
    Circuit selectedCircuit = idTable.getSelectionModel().getSelectedItem();

    // Check if an item is selected
    if (selectedCircuit == null) {
        displayAlert("Aucun élément sélectionné", "Aucun élément sélectionné pour la suppression.");
        return;
    }

    // Display a confirmation alert
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de la suppression");
    alert.setHeaderText(null);
    alert.setContentText("Êtes-vous sûr de vouloir supprimer ce circuit?");
    Optional<ButtonType> result = alert.showAndWait();

    if (result.isPresent() && result.get() == ButtonType.OK) {
        // Remove the selected item from the database
        ServiceCircuit ps = new ServiceCircuit();
        ps.supprimer(selectedCircuit);

        // Remove the selected item from the ListView
        circuitList.remove(selectedCircuit);

        // Clear the TextFields after deleting
        clearTextFields();
    }
    }

    @FXML
    private void returnPage(ActionEvent event) {
try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Besttrip/agence/gui/Start.fxml"));
        Parent root = loader.load();
        
        // Access the StartController if needed
        StartController startController = loader.getController();
        // Initialize data or perform other operations here
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println("Error loading Start.fxml: " + ex.getMessage());
    }
    }
    private void clearTextFields() {
    start.clear();
    end.clear();
    time.clear();
    price.clear();
    category.clear();    
    descripting.clear();
    country.clear();

}

    @FXML
    private void rechercherElement(ActionEvent event) {
        // Get the search text from the TextField
    String searchPays = Trecherche.getText().toLowerCase(); // Convert to lowercase for case-insensitive search

    // Create a filtered list to hold the matching circuits
    ObservableList<Circuit> filteredCircuits = FXCollections.observableArrayList();

    // Loop through the circuitList and add matching circuits to the filtered list
    for (Circuit circuit : circuitList) {
        if (circuit.getPays().toLowerCase().contains(searchPays)) {
            filteredCircuits.add(circuit);
        }else if(circuit.getArrive().toLowerCase().contains(searchPays)){
         filteredCircuits.add(circuit);
        }
        
       
    }

    // Set the items of the table view to the filtered list
    idTable.setItems(filteredCircuits);
    }
}
