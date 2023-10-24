/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import DB.Pdf;
import Entity.Commentaire;
import Entity.Post;
import Service.PostService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author abdennour
 */
public class AffichePosteController implements Initializable {

    @FXML
    private TableView<Post> id_liste;
    @FXML
    private TableColumn<Post, String> id_titre;
    @FXML
    private TableColumn<Post, String> id_sujet;
    @FXML
    private TableColumn<Post, String> id_image;
    @FXML
    private TableColumn<Post, LocalDate> id_date;
    @FXML
    private Button id_liste_post;
    @FXML
    private Button id_del;
    ObservableList<Post> eventList2;
    @FXML
    private Button id_update;
    @FXML
    private Button id_clear;
    @FXML
    private Button id_pdf;
    @FXML
    private TextField rech;
    @FXML
    private Button id_ajout;
    @FXML
    private Button id_comment;
    @FXML
    private Button id_listComment;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ImageView imageView7 = new ImageView(getClass().getResource("/Utils/details.png").toExternalForm());
        id_listComment.setGraphic(imageView7);
        
        ImageView imageView6 = new ImageView(getClass().getResource("/Utils/comment.png").toExternalForm());
        id_comment.setGraphic(imageView6);
        
        ImageView imageView5 = new ImageView(getClass().getResource("/Utils/add.png").toExternalForm());
        id_ajout.setGraphic(imageView5);
        
        ImageView imageView4 = new ImageView(getClass().getResource("/Utils/pdf.png").toExternalForm());
        id_pdf.setGraphic(imageView4);
        
        ImageView imageView3 = new ImageView(getClass().getResource("/Utils/remouve.png").toExternalForm());
        id_clear.setGraphic(imageView3);
        
        ImageView imageView2 = new ImageView(getClass().getResource("/Utils/update.png").toExternalForm());
        id_update.setGraphic(imageView2);
        
        ImageView imageView1 = new ImageView(getClass().getResource("/Utils/del.png").toExternalForm());
        id_del.setGraphic(imageView1);
        // TODO
        ImageView imageView = new ImageView(getClass().getResource("/Utils/2222.png").toExternalForm());
        id_liste_post.setGraphic(imageView);

            id_liste_post.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listPost();
            }
        });
    }    

    @FXML
    private void listPost() {
        
        PostService uc =new PostService();
        ArrayList arrayList = null;
        arrayList = (ArrayList) uc.afficherPost();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        id_liste.setItems(observableList);
        id_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        id_sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        id_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("date"));  
        
    }

    @FXML
    private void deletePost(ActionEvent event) {
           int id = Integer.valueOf(id_liste.getSelectionModel().getSelectedItem().toString().split("=")[1].substring(0, 1));
           PostService rs = new  PostService();
           rs.supprimerPost(id);
           id_liste.getItems().removeAll(id_liste.getSelectionModel().getSelectedItem());
    }

    private void back_to_add(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/AddPost.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void UpdatePost(ActionEvent event) throws IOException {
        
                eventList2=id_liste.getSelectionModel().getSelectedItems();
                int id=eventList2.get(0).getId();
                if ( id !=0)
                {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Update.fxml"));
                Parent root = loader.load();
                UpdateController aa = loader.getController();
                aa.initData(id);
                id_update.getScene().setRoot(root);
      }
   }

    @FXML
    private void clear(ActionEvent event) {
        PostService e =new PostService();
        e.Clear();
        JOptionPane.showMessageDialog(null, "List vidé !");
    }

    @FXML
    private void pdf(ActionEvent event) {
                  Pdf pd=new Pdf();
        try{
        pd.GeneratePdf("Liste des tableau artestique");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void FiltrerKeyReleased3(KeyEvent event) {
        
    String searchTerm = rech.getText();
    PostService su = new PostService ();
    ObservableList<Post> list = su.searchent2(searchTerm);
    List<Post> filteredList = list.stream()
        .filter(entretient -> entretient.getTitre().toLowerCase().contains(searchTerm.toLowerCase()))
        .collect(Collectors.toList());

   id_liste.setItems(FXCollections.observableArrayList(filteredList));
    }

    @FXML
    private void ajouterPost(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/AddPost.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }   

    @FXML
    private void commenter(ActionEvent event) throws IOException {
                 eventList2=id_liste.getSelectionModel().getSelectedItems();
                int id=eventList2.get(0).getId();
                if ( id !=0)
                {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddComment.fxml"));
                Parent root = loader.load();
                AddCommentController aa = loader.getController();
                aa.initData(id);
                id_update.getScene().setRoot(root);
      }
    }

    @FXML
    private void listerCommentaires(ActionEvent event) throws IOException {
                   eventList2=id_liste.getSelectionModel().getSelectedItems();
                int id=eventList2.get(0).getId();
                if ( id !=0)
                {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheComment.fxml"));
                Parent root = loader.load();
                AfficheCommentController aa = loader.getController();
                aa.initData(id);
                id_update.getScene().setRoot(root);
      }
    }
    
}
