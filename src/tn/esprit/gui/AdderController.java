/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.entity.Circuit;
import tn.esprit.services.ServiceCircuit;

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
      @FXML
    private ListView<Circuit> listv;
    
     private ObservableList<Circuit> circuitList = FXCollections.observableArrayList();
   
    @FXML
    private TextArea descripting;
  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadInitialDataFromDatabase();
        listv.setItems(circuitList);
        
         listv.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2) { // Double-click event
            Circuit selectedCircuit = listv.getSelectionModel().getSelectedItem();
            if (selectedCircuit != null) {
                // Populate the TextFields with the selected item's data
                start.setText(selectedCircuit.getDepart());
                end.setText(selectedCircuit.getArrive());
                time.setText(selectedCircuit.getTemps());
                price.setText(Integer.toString(selectedCircuit.getPrix()));
                category.setText(selectedCircuit.getCategorie());
                descripting.setText(selectedCircuit.getDescription());
            }
        }
    });
        // TODO
    }    
      private void loadInitialDataFromDatabase() {
    ServiceCircuit ps = new ServiceCircuit();
    List<Circuit> initialCircuits = ps.afficher();
    
    // Populate circuitList with the initial data from the database
    circuitList.clear();
    circuitList.addAll(initialCircuits);
}

  //  @FXML
   // private void editCircuit(ActionEvent event) {
   //    Circuit selectedCircuit = listv.getSelectionModel().getSelectedItem();
    
    // Check if an item is selected
   // if (selectedCircuit == null) {
     //   System.out.println("No item selected for editing.");
    //    return;
   // }
    
    // Populate the TextFields with the selected item's data
  //  start.setText(selectedCircuit.getDepart());
 //   end.setText(selectedCircuit.getArrive());
  //  time.setText(selectedCircuit.getTemps());
  //  price.setText(Integer.toString(selectedCircuit.getPrix())); // Convert int to String
  //  category.setText(selectedCircuit.getCategorie());    
  //  descripting.setText(selectedCircuit.getDescription());

  // }

      
   @FXML
private void saveEditedCircuit(ActionEvent event) {
    Circuit selectedCircuit = listv.getSelectionModel().getSelectedItem();

    // Check if an item is selected
    if (selectedCircuit == null) {
        System.out.println("No item selected for editing.");
        return;
    }
    
    // Update the selected item with the edited data
    selectedCircuit.setDepart(start.getText());
    selectedCircuit.setArrive(end.getText());
    selectedCircuit.setTemps(time.getText());    
    selectedCircuit.setDescription(descripting.getText());    

    try {
        int editedPrix = Integer.parseInt(price.getText());
        selectedCircuit.setPrix(editedPrix);
    } catch (NumberFormatException e) {
        // Handle the case where prixStr is not a valid integer
        System.out.println("Invalid prix value: " + price.getText());
        return; // Exit the method or handle the error as needed.
    }
    
    selectedCircuit.setCategorie(category.getText());

    // Ensure that the id is set before modifying
    selectedCircuit.setId(selectedCircuit.getId()); // You might not need this line if the id is already set.

    // Call the modifier method
    ServiceCircuit ps = new ServiceCircuit();
    ps.modifier(selectedCircuit);

    // Clear the TextFields after saving
    clearTextFields();

    // Refresh the ListView to reflect the changes
    listv.refresh();
}
    


    @FXML
    private void addCircuit(ActionEvent event) {
        String depart = start.getText();
    String arrive = end.getText();
    String temps = time.getText();
    String prixStr = price.getText();
    String categorie = category.getText();
    String description = descripting.getText();

   

    int prix;
    try {
        prix = Integer.parseInt(prixStr);
    } catch (NumberFormatException e) {
        // Handle the case where prixStr is not a valid integer
       displayAlert("Invalid Input", "Prix value is not a valid integer.");
        
        return;
    }
     // Validate input
     // Validate input
    if (depart.isEmpty() || arrive.isEmpty() || temps.isEmpty() || prixStr.isEmpty() || categorie.isEmpty() || description.isEmpty()) {
        displayAlert("Missing Information", "Please fill in all fields.");
        return;
    }

    ServiceCircuit ps = new ServiceCircuit();
    Circuit p = new Circuit(depart, arrive, temps, categorie, prix, description);

    ps.ajouter(p);
    List<Circuit> updatedCircuits = ps.afficher();

    // Update circuitList with the new data
    circuitList.clear();
    circuitList.addAll(updatedCircuits);

    // Set the ListView items to circuitList to reflect the updated data
    listv.setItems(circuitList);
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
    Circuit selectedCircuit = listv.getSelectionModel().getSelectedItem();

    // Check if an item is selected
    if (selectedCircuit == null) {
        displayAlert("No Item Selected", "No item selected for deletion.");
        return;
    }

    // Display a confirmation alert
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm Delete");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to delete this circuit?");
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/gui/Start.fxml"));
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

}
}
