/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DB.BD;
import Entity.Post;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author abdennour
 */
public class PostService {
    
     Connection cnx = BD.getInstance().getCnx();
    private Object connexion;
    PreparedStatement stmt;
    PreparedStatement ste;

  
     public void ajouterPost(Post p){
        try {
            // Define the SQL INSERT statement
            String sql = "INSERT INTO post (titre,sujet,image,date) VALUES (?, ?, ?, ?)";
            stmt = cnx.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            // Set the parameter values for the INSERT statement
            stmt.setString(1, p.getTitre());
            stmt.setString(2, p.getSujet());
            stmt.setString(3, p.getImage());
            stmt.setDate(4,Date.valueOf(p.getDate()));
            stmt.executeUpdate();
             System.out.println("Post ajoutée avec succés !");
              }catch (SQLException e) {
            System.err.println(e.getMessage());
         }
     }
     
     public void update(Post e) {
    try {
          
         String req = "UPDATE `post` SET `id`='" + e.getId()+ "',`titre`='" + e.getTitre()+
                 "',`sujet`='" + e.getSujet()+ "', `Image`='" + e.getImage()+ "',`date`='"+Date.valueOf(e.getDate())+ 
                 "' WHERE id=" + e.getId();
         
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("L'Post est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
         
         
      public List<Post> afficherPost() {
      List<Post> post =  new ArrayList<>();
      String sql="select * from post";
      
      try
      {
          ste=cnx.prepareStatement(sql);
          
          ResultSet rs=ste.executeQuery();
                  while(rs.next()){
                      Post o = new Post();
                      o.setId(rs.getInt("id"));
                      o.setTitre(rs.getString("titre"));
                      o.setSujet(rs.getString("sujet"));
                      o.setImage(rs.getString("image"));
                     
                      o.setDate(rs.getDate("date").toLocalDate());
                      post.add(o);
                      System.out.println("id post : "+o.getId()+ "\n Titre : "+o.getTitre()+ "\n Sujet : "+o.getSujet()+"\n Image: " +o.getImage()+"\n cette peinture a été réalisée a la date "+o.getDate().toString()) ;
                  }
      }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return post;
    }
      

    
    public Post getPostById(int id) {

      String sql = "SELECT * FROM post WHERE id = ?";

      try {
        stmt = cnx.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Post post = new Post();
            post.setId(rs.getInt("id"));
            post.setTitre(rs.getString("titre"));
            post.setSujet(rs.getString("sujet"));
            post.setImage(rs.getString("image"));
            post.setDate(rs.getDate("date").toLocalDate());
            return post;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}
        
        
        
        
    public Post readById(int id) {
        Post p = null; // Déclarer la variable p en dehors du bloc if

    try {
        PreparedStatement statement = cnx.prepareStatement("SELECT * FROM post WHERE id = "+id);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            p = new Post (rs.getInt(1), 
                          rs.getString(2), 
                          rs.getString(3), 
                          rs.getString(4),
                          rs.getDate(5).toLocalDate() );
        }
    } catch (SQLException e) {
            // Gérer l'exception SQLException
            System.out.print(e.getMessage());
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, e);
    }
    return p;
    }
    
    
        public void supprimerPost(int id) {
    try {
        String req=" DELETE FROM post WHERE id="+ id ;

        PreparedStatement St = cnx.prepareStatement(req);
        St.executeUpdate();
        System.out.println("Le Post est supprimé");}
     catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }    
   }
        
        
    
       public void Clear() {
    String req = "DELETE FROM post";
    try {
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.executeUpdate();
        System.out.println("Toutes les lignes de la table 'post' ont été supprimées !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
       }
       
       
          public ObservableList<Post> searchent2(String searchTerm) {
        ObservableList<Post> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM post");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Post offre = new Post(
                          rs.getString(2), 
                          rs.getString(3),
                          rs.getString(4),
                          rs.getDate("date").toLocalDate()
                         
                );
                list.add(offre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
