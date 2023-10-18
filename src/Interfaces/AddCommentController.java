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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdennour
 */
public class AddCommentController implements Initializable {

    @FXML
    private TextField id_comment;
    private Commentaire CommentToAdd = new Commentaire();

    /**
     * Initializes the controller class.
     */
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException{
        
         CommentaireService postService = new CommentaireService();
    CommentToAdd.setContenu(id_comment.getText());
   //  Integer.valueOf(id_post.getText());
  
    postService.ajouterCommentaire(CommentToAdd, idE);
    }

    public void initData(int data) {
       this.idE=data;
      String stringNumber = Integer.toString(idE);

      id.setText(stringNumber);
      tarata();
      
    id_comment.setText(e.getTitre());
   
            }

    @FXML
    private void afficheComment(ActionEvent event) throws IOException {
        
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/affichePoste.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
