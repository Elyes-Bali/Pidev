/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import Besttrip.agence.entity.Hebergement;
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
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import Besttrip.agence.services.HebergementService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author Ons
 */
public class ListHebergementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private TableColumn<Hebergement, String> AdresseCell;

    @FXML
    private TableColumn<Hebergement, Integer> CapaciteCell;

    @FXML
    private TableColumn<Hebergement, Integer> CategorieCell;

    @FXML
    private TableColumn<Hebergement, String> DescriptionCell;

    @FXML
    private TableColumn<Hebergement, Float> PrixCell;

    @FXML
    private TableColumn<Hebergement, String> TypeCell;

    @FXML
    private Button bntAddHeb;

    @FXML
    private Button btnDeleteHeb;


    @FXML
    private ComboBox<String> comboBoxTriHeb;

    @FXML
    private AnchorPane listHebPane;

    @FXML
    private TableView<Hebergement> tableHeb;

    @FXML
    private TextField txtSearchHeb;
    
    @FXML
    void open_addHebergement(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("ajoutHebergement.fxml"));
        listHebPane.getChildren().removeAll();
        listHebPane.getChildren().setAll(fxml);
    }
    
    @FXML
    void open_Statistiques(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("Statistiques.fxml"));
        listHebPane.getChildren().removeAll();
        listHebPane.getChildren().setAll(fxml);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherHebergement();
    }    
    
    
    ObservableList<Hebergement> dataHebergement = FXCollections.observableArrayList();  
    
    
    public void AfficherHebergement()
    {
        HebergementService hs = new HebergementService();
        hs.Show().stream().forEach((c) -> {
            dataHebergement.add(c);
        });
        
        CapaciteCell.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        CapaciteCell.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        CapaciteCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Hebergement, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Hebergement, Integer> event) {
                Hebergement h = event.getRowValue();
                h.setCapacite(event.getNewValue());
                HebergementService hs = new HebergementService();
                hs.modifier(h);
            }
        });
        PrixCell.setCellValueFactory(new PropertyValueFactory<>("prix"));
        PrixCell.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Float>() {
            @Override
            public String toString(Float object) {
                return object.toString();
            }

            @Override
            public Float fromString(String string) {
                return Float.valueOf(string);
            }
        }));
        PrixCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Hebergement, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Hebergement, Float> event) {
                Hebergement h = event.getRowValue();
                h.setPrix(event.getNewValue());
                HebergementService hs = new HebergementService();
                hs.modifier(h);
            }
        });
        AdresseCell.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        AdresseCell.setCellFactory(TextFieldTableCell.forTableColumn());
        AdresseCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Hebergement, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Hebergement, String> event) {
                Hebergement h = event.getRowValue();
                h.setAdresse(event.getNewValue());
                HebergementService hs = new HebergementService();
                hs.modifier(h);
            }
        });
        TypeCell.setCellValueFactory(new PropertyValueFactory<>("type"));
        TypeCell.setCellFactory(TextFieldTableCell.forTableColumn());
        TypeCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Hebergement, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Hebergement, String> event) {
                Hebergement h = event.getRowValue();
                h.setType(event.getNewValue());
                HebergementService hs = new HebergementService();
                hs.modifier(h);
            }
        });
        DescriptionCell.setCellValueFactory(new PropertyValueFactory<>("description"));
        DescriptionCell.setCellFactory(TextFieldTableCell.forTableColumn());
        DescriptionCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Hebergement, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Hebergement, String> event) {
                Hebergement h = event.getRowValue();
                h.setDescription(event.getNewValue());
                HebergementService hs = new HebergementService();
                hs.modifier(h);
            }
        });
        CategorieCell.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        CategorieCell.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        CategorieCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Hebergement, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Hebergement, Integer> event) {
                Hebergement h = event.getRowValue();
                h.setCategorie(event.getNewValue());
                HebergementService hs = new HebergementService();
                hs.modifier(h);
            }
        });
        tableHeb.setItems(dataHebergement);
        comboBoxTriHeb.getItems().addAll("Trier Selon",  "Adresse", "Description");
        try {
            searchHebergement();
        } catch (SQLException ex) {
            Logger.getLogger(ListHebergementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void supprimerHebergement(ActionEvent event) throws SQLException {
        HebergementService hs = new HebergementService();
        
        if (tableHeb.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner l'hébergement à supprimer");
            alert.showAndWait();
            return;
        }

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette hébergement ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de l'hebergement sélectionnée dans la vue de la table
            int id = tableHeb.getSelectionModel().getSelectedItem().getId();

            // Supprimer le cours de la base de données
            hs.supprimer(id);
            // Rafraîchir la liste de données
            dataHebergement.clear();
            AfficherHebergement();
            // Rafraîchir la vue de la table
            tableHeb.refresh();
        }
    }
    
    
    public HebergementService hs = new HebergementService();
    
    public void searchHebergement() throws SQLException {    
        FilteredList<Hebergement> filteredData = new FilteredList<>(FXCollections.observableArrayList(hs.Show()), p -> true);
        txtSearchHeb.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(heb -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String capacite = String.valueOf(heb.getCapacite());
                String prix = String.valueOf(heb.getPrix());
                String adresse = String.valueOf(heb.getAdresse());
                String type = String.valueOf(heb.getType());
                String description = String.valueOf(heb.getDescription());
                String lowerCaseFilter = newValue.toLowerCase();

                if (capacite.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (prix.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (adresse.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (type.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (description.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else {
                    return false;
                }
            });
        });
        SortedList<Hebergement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableHeb.comparatorProperty());
        tableHeb.setItems(sortedData);
    }
    
    
    private void TriAdresse() {
        HebergementService hs = new HebergementService();
        List<Hebergement> h = hs.triAdresse();
        CapaciteCell.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        PrixCell.setCellValueFactory(new PropertyValueFactory<>("prix"));
        AdresseCell.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        TypeCell.setCellValueFactory(new PropertyValueFactory<>("type"));
        DescriptionCell.setCellValueFactory(new PropertyValueFactory<>("description"));
        

        tableHeb.setItems(FXCollections.observableList(h));
    }
    
    private void TriDescription() {
        HebergementService hs = new HebergementService();
        List<Hebergement> h = hs.triDescription();
        CapaciteCell.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        PrixCell.setCellValueFactory(new PropertyValueFactory<>("prix"));
        AdresseCell.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        TypeCell.setCellValueFactory(new PropertyValueFactory<>("type"));
        DescriptionCell.setCellValueFactory(new PropertyValueFactory<>("description"));
        

        tableHeb.setItems(FXCollections.observableList(h));
    }
    
    
    @FXML
    private void TriChoice(ActionEvent event) throws IOException {
        if (comboBoxTriHeb.getValue().equals("Adresse")) {
            TriAdresse();
        } else if (comboBoxTriHeb.getValue().equals("Description")) {
            TriDescription();
        }

    }
    
    
    @FXML
    void genererPDF(ActionEvent event) {
        // Afficher la boîte de dialogue de sélection de fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le fichier PDF");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers PDF", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            // Générer le fichier PDF avec l'emplacement de sauvegarde sélectionné
            // Récupérer la liste 
            HebergementService as = new HebergementService();
            List<Hebergement> HebergementList = as.Show();

            try {
                // Créer le document PDF
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();

                // Créer une instance de l'image
                Image image = Image.getInstance(System.getProperty("user.dir") + "/src/images/5783695.png");

                // Positionner l'image en haut à gauche
                image.setAbsolutePosition(5, document.getPageSize().getHeight() - 120);

                // Modifier la taille de l'image
                image.scaleAbsolute(152, 100);

                // Ajouter l'image au document
                document.add(image);

                // Créer une police personnalisée pour la date
                Font fontDate = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                BaseColor color = new BaseColor(249, 180, 30); // Rouge: 114, Vert: 0, Bleu: 0
                fontDate.setColor(color); // Définir la couleur de la police

                // Créer un paragraphe avec le lieu
                Paragraph tunis = new Paragraph("Ariana", fontDate);
                tunis.setIndentationLeft(455); // Définir la position horizontale
                tunis.setSpacingBefore(-30); // Définir la position verticale
                // Ajouter le paragraphe au document
                document.add(tunis);

                // Obtenir la date d'aujourd'hui
                LocalDate today = LocalDate.now();

                // Créer un paragraphe avec la date
                Paragraph date = new Paragraph(today.toString(), fontDate);

                date.setIndentationLeft(437); // Définir la position horizontale
                date.setSpacingBefore(1); // Définir la position verticale
                // Ajouter le paragraphe au document
                document.add(date);

                // Créer une police personnalisée
                Font font = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD);
                BaseColor titleColor = new BaseColor(249, 180, 30); //
                font.setColor(titleColor);

                // Ajouter le contenu au document
                Paragraph title = new Paragraph("Liste des Hebergement", font);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingBefore(50); // Ajouter une marge avant le titre pour l'éloigner de l'image
                title.setSpacingAfter(20);
                document.add(title);

                PdfPTable table = new PdfPTable(5); // 5 colonnes pour les 5 attributs des activités
                table.setWidthPercentage(100);
                table.setSpacingBefore(30f);
                table.setSpacingAfter(30f);

                // Ajouter les en-têtes de colonnes
                Font hrFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
                BaseColor hrColor = new BaseColor(255, 255, 255); //
                hrFont.setColor(hrColor);

                PdfPCell cell1 = new PdfPCell(new Paragraph("ID", hrFont));
                BaseColor bgColor = new BaseColor(249, 180, 30);
                cell1.setBackgroundColor(bgColor);
                cell1.setBorderColor(titleColor);
                cell1.setPaddingTop(20);
                cell1.setPaddingBottom(20);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell2 = new PdfPCell(new Paragraph("Nom", hrFont));
                cell2.setBackgroundColor(bgColor);
                cell2.setBorderColor(titleColor);
                cell2.setPaddingTop(20);
                cell2.setPaddingBottom(20);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell3 = new PdfPCell(new Paragraph("Duree", hrFont));
                cell3.setBackgroundColor(bgColor);
                cell3.setBorderColor(titleColor);
                cell3.setPaddingTop(20);
                cell3.setPaddingBottom(20);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell4 = new PdfPCell(new Paragraph("Tenue", hrFont));
                cell4.setBackgroundColor(bgColor);
                cell4.setBorderColor(titleColor);
                cell4.setPaddingTop(20);
                cell4.setPaddingBottom(20);
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell5 = new PdfPCell(new Paragraph("Difficulté", hrFont));
                cell5.setBackgroundColor(bgColor);
                cell5.setBorderColor(titleColor);
                cell5.setPaddingTop(20);
                cell5.setPaddingBottom(20);
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);

                Font hdFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
                BaseColor hdColor = new BaseColor(255, 255, 255); //
                hrFont.setColor(hdColor);
                // Ajouter les données des produits
                for (Hebergement act : HebergementList) {
                    PdfPCell cellR1 = new PdfPCell(new Paragraph(String.valueOf(act.getId()), hdFont));
                    cellR1.setBorderColor(titleColor);
                    cellR1.setPaddingTop(10);
                    cellR1.setPaddingBottom(10);
                    cellR1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR1);

                    PdfPCell cellR2 = new PdfPCell(new Paragraph(String.valueOf(act.getCapacite()), hdFont));
                    cellR2.setBorderColor(titleColor);
                    cellR2.setPaddingTop(10);
                    cellR2.setPaddingBottom(10);
                    cellR2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR2);

                    PdfPCell cellR3 = new PdfPCell(new Paragraph(String.valueOf(act.getPrix()), hdFont));
                    cellR3.setBorderColor(titleColor);
                    cellR3.setPaddingTop(10);
                    cellR3.setPaddingBottom(10);
                    cellR3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR3);

                    PdfPCell cellR4 = new PdfPCell(new Paragraph(String.valueOf(act.getAdresse()), hdFont));
                    cellR4.setBorderColor(titleColor);
                    cellR4.setPaddingTop(10);
                    cellR4.setPaddingBottom(10);
                    cellR4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR4);

                    PdfPCell cellR5 = new PdfPCell(
                            new Paragraph(String.valueOf(act.getDescription()), hdFont));
                    cellR5.setBorderColor(titleColor);
                    cellR5.setPaddingTop(10);
                    cellR5.setPaddingBottom(10);
                    cellR5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR5);

                }
                table.setSpacingBefore(20);
                document.add(table);
                document.close();

                System.out.println("Le fichier PDF a été généré avec succès.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    
    }
}
