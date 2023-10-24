/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.CategoryHebergement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;
import services.CategoryService;


/**
 * FXML Controller class
 *
 * @author Ons
 */
public class ListCategController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableColumn<CategoryHebergement, String> ContenuCell;

    @FXML
    private Button bntAddCateg;

    @FXML
    private Button btnDeleteCateg;

    @FXML
    private ComboBox<String> comboBoxTriAct;

    @FXML
    private TableColumn<CategoryHebergement, Integer> idCell;

    @FXML
    private AnchorPane listCategPane;

    @FXML
    private TableView<CategoryHebergement> tableCateg;

    @FXML
    private TextField txtSearchCateg;
    
    
    @FXML
    void open_addCategory(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("ajoutCategory.fxml"));
        listCategPane.getChildren().removeAll();
        listCategPane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherCateg();
    }    
    
    ObservableList<CategoryHebergement> dataCateg = FXCollections.observableArrayList();  
    
    
    public void AfficherCateg()
    {
        CategoryService hs = new CategoryService();
        hs.Show().stream().forEach((c) -> {
            dataCateg.add(c);
        });
        
        idCell.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCell.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        ContenuCell.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        ContenuCell.setCellFactory(TextFieldTableCell.forTableColumn());
        ContenuCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CategoryHebergement, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CategoryHebergement, String> event) {
                CategoryHebergement c = event.getRowValue();
                c.setContenu(event.getNewValue());
                CategoryService cs = new CategoryService();
                cs.modifier(c);
            }
        });
        tableCateg.setItems(dataCateg);
        comboBoxTriAct.getItems().addAll("Trier Selon",  "Contenu");
        
        try {
            searchCateg();
        } catch (SQLException ex) {
            Logger.getLogger(ListCategController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void supprimerCateg(ActionEvent event) throws SQLException {
        CategoryService cs = new CategoryService();
        
        if (tableCateg.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner la catégorie à supprimer");
            alert.showAndWait();
            return;
        }

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette catégorie ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de la catégorie sélectionnée dans la vue de la table
            int id = tableCateg.getSelectionModel().getSelectedItem().getId();

            // Supprimer le cours de la base de données
            cs.supprimer(id);
            // Rafraîchir la liste de données
            dataCateg.clear();
            AfficherCateg();
            // Rafraîchir la vue de la table
            tableCateg.refresh();
        }
    }
    
    
    public CategoryService cs = new CategoryService();
    
    public void searchCateg() throws SQLException {    
        FilteredList<CategoryHebergement> filteredData = new FilteredList<>(FXCollections.observableArrayList(cs.Show()), p -> true);
        txtSearchCateg.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(categ -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String contenu = String.valueOf(categ.getContenu());
                
                String lowerCaseFilter = newValue.toLowerCase();

                if (contenu.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<CategoryHebergement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableCateg.comparatorProperty());
        tableCateg.setItems(sortedData);
    }
    
    
    private void TriContenu() {
        CategoryService cs = new CategoryService();
        List<CategoryHebergement> c = cs.triContenu();
        idCell.setCellValueFactory(new PropertyValueFactory<>("id"));
        ContenuCell.setCellValueFactory(new PropertyValueFactory<>("contenu"));

        tableCateg.setItems(FXCollections.observableList(c));
    }
    
    
    @FXML
    private void TriChoice(ActionEvent event) throws IOException {
        if (comboBoxTriAct.getValue().equals("Contenu")) {
            TriContenu();
        } 

    }
    
}
