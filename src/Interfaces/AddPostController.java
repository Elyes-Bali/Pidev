/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entity.Post;
import Service.PostService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdennour
 */
public class AddPostController implements Initializable {

    @FXML
    private TextField tf_titre;
    @FXML
    private TextField tf_sujet;
    @FXML
    private ImageView iv_gallerie;
    private Post postToAdd = new Post();
    private File selectedFile;
    @FXML
    private DatePicker dateField;
    @FXML
    private Button id_ann;
    @FXML
    private Button id_add_im;
    @FXML
    private Button id_add_p;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ImageView imageView2 = new ImageView(getClass().getResource("/Utils/add.png").toExternalForm());
        id_add_p.setGraphic(imageView2);
        
         ImageView imageView3 = new ImageView(getClass().getResource("/Utils/telecharge.png").toExternalForm());
        id_add_im.setGraphic(imageView3);
        
         ImageView imageView1 = new ImageView(getClass().getResource("/Utils/sup3.png").toExternalForm());
        id_ann.setGraphic(imageView1);
    }    

    @FXML
    private void AddImage(ActionEvent event) {
           Stage s = new Stage();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.png", "*.jpeg", "*.JPEG"));
        
        selectedFile = fc.showOpenDialog(s);
       
        String imgPath = selectedFile.getAbsoluteFile().getPath().toString();
        postToAdd.setImage(selectedFile.getAbsoluteFile().getName().toString());

        Image img = new Image(selectedFile.toURI().toString());

        iv_gallerie.setImage(img);
    }
    
    
    private void copyImageToServer(File selectedFile) throws IOException {
    String to = "C:\\xampp\\htdocs\\Blog_EArt\\" + selectedFile.getName();
    System.out.println("Copying image from: " + selectedFile.getAbsolutePath());
    System.out.println("Copying image to: " + to);
    
    Path src = Paths.get(selectedFile.getAbsolutePath());
    Path dest = Paths.get(to);
    Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
    
    System.out.println("Image copied successfully.");
}


   @FXML
private void add_post(ActionEvent event) throws IOException {
    
        if (tf_titre.getText().isEmpty() || tf_sujet.getText().isEmpty() || dateField.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs obligatoires.");
        alert.showAndWait();
        return;
    }
        
    PostService postService = new PostService();
    postToAdd.setTitre(tf_titre.getText());
    postToAdd.setSujet(tf_sujet.getText());

    // Ajoutez la date ici, par exemple :
    postToAdd.setDate(dateField.getValue());

    postService.ajouterPost(postToAdd);
    try {
        copyImageToServer(selectedFile);
    } catch (IOException ex) {
        System.out.println(ex);       
    }
    
    
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Post bien ajouté !");
                alert.showAndWait();
                
                
                  String recipient = "amdouni.abdennour@esprit.tn";
                 try {
                 DB.Mail.envoyer(recipient);
                 System.out.println("Le message a été envoyé avec succès.");
                 } catch (Exception e) {
                 System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
                 e.printStackTrace();
               }
                 
                 
    
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/AffichePoste.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
}

    @FXML
    private void back_to_affiche(ActionEvent event) throws IOException {
        
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/AffichePoste.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }

    
}
