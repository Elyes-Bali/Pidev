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
import java.time.LocalDate;
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
import javafx.scene.control.Label;
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
public class UpdateController implements Initializable {

    @FXML
    private TextField tf_titre;
    @FXML
    private TextField tf_sujet;
    @FXML
    private ImageView iv_gallerie;
    @FXML
    private DatePicker dateField;
    @FXML
    private Button id_ann;
    private File selectedFile;
    private Post postToAdd = new Post();


           private int idE;
    private Post e = new Post(); 
    @FXML
    private Label id;

         public void tarata()
       {
        
        PostService ps = new PostService();
        e=ps.readById(idE);
        //System.out.print(e);
       }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    

    @FXML
    private void update_post(ActionEvent event) throws IOException {
        
       Post r = new Post();
       PostService su = new PostService() ;  
        r.setId(idE); 
        r.setTitre(tf_titre.getText());
        r.setSujet(tf_sujet.getText());
        
            try {
        r.setTitre(tf_titre.getText());
      } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le champ prix doit étre un nombre !");
        alert.showAndWait();
        return;
       }
               if (dateField.getValue().isAfter(LocalDate.now())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("La date doit être inferieure ou égale à aujourd'hui !");
                alert.showAndWait();
                return;}
                r.setDate(dateField.getValue());
                
                
                

        r.setDate(dateField.getValue()); 
       String imageName = selectedFile.getName();
       postToAdd.setImage(imageName);

    // Vérifier si le fichier sélectionné est null
     if (selectedFile != null) {
        System.out.println("Selected File: " + selectedFile.getAbsolutePath());
        copyImageToServer(selectedFile);
        System.out.println("Image copied successfully.");
    } else {
        System.out.println("No image selected.");
    }
    r.setImage(imageName);

    su.update(r);
        
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("modification éffectuée");
                alert.showAndWait();
                
        
                
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
    
    
    
        public void initData(int data) {
       this.idE=data;
      String stringNumber = Integer.toString(idE);

      id.setText(stringNumber);
      tarata();
      
    tf_titre.setText(e.getTitre());
    tf_sujet.setText(e.getSujet());
    dateField.setValue(e.getDate());
            }
        
        
private void copyImageToServer(File selectedFile) throws IOException {
    String destinationFolderPath = "../Blog_EArt/"; // Chemin relatif au dossier de destination
    
    String to = destinationFolderPath + selectedFile.getName();
    System.out.println("Copying image from: " + selectedFile.getAbsolutePath());
    System.out.println("Copying image to: " + to);
    
    Path src = Paths.get(selectedFile.getAbsolutePath());
    Path dest = Paths.get(to);
    Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
    
    System.out.println("Image copied successfully.");
}


}
