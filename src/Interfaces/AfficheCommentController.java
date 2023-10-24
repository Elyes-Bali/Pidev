/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entity.Commentaire;
import Entity.Post;
import Service.CommentaireService;
import Service.PostService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author abdennour
 */
public class AfficheCommentController implements Initializable {

    @FXML
    private TableView<Commentaire> id_liste;
    @FXML
    private TableColumn<Commentaire, String> id_nom;
    @FXML
    private TableColumn<Commentaire, String> id_contenu;
    @FXML
    private TableColumn<Commentaire, String> id_date;
    @FXML
    private Button id_liste_comment;
    @FXML
    private Label id;

    /**
     * Initializes the controller class.
     */
    
               private int idE;
    private Post e = new Post(); 
    @FXML
    private Button id_del;
    @FXML
    private Button id_clear;
   
        public void tarata()
       {
        
        PostService ps = new PostService();
        e=ps.readById(idE);
        //System.out.print(e);
       }
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ImageView imageView3 = new ImageView(getClass().getResource("/Utils/remouve.png").toExternalForm());
        id_clear.setGraphic(imageView3);
        
        
        ImageView imageView1 = new ImageView(getClass().getResource("/Utils/del.png").toExternalForm());
        id_del.setGraphic(imageView1);
        
        ImageView imageView = new ImageView(getClass().getResource("/Utils/2222.png").toExternalForm());
        id_liste_comment.setGraphic(imageView);

            id_liste_comment.setOnAction((ActionEvent event) -> {
                listPost();
        });
        // TODO
    }    

    @FXML
    private void listPost() {
        
         CommentaireService uc =new CommentaireService();
        ArrayList arrayList = null;
        arrayList = (ArrayList) uc.afficheCommentaireByIdPost(idE);
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        id_liste.setItems(observableList);
        id_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        id_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("dateNow"));  
        
    }
        public void initData(int data) {
       this.idE=data;
      String stringNumber = Integer.toString(idE);

      id.setText(stringNumber);
      tarata();
            }

    @FXML
    private void deleteComment(ActionEvent event) {
           int id = Integer.valueOf(id_liste.getSelectionModel().getSelectedItem().toString().split("=")[1].substring(0, 1));
           CommentaireService rs = new  CommentaireService();
           rs.supprimerComment(id);
           id_liste.getItems().removeAll(id_liste.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void clear(ActionEvent event) {
        CommentaireService e =new CommentaireService();
        e.Clear(idE);
        JOptionPane.showMessageDialog(null, "List vidé !");
    }

    @FXML
    private void goToPost(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/AffichePoste.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
