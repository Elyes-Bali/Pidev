/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DB.BD;
import Entity.Commentaire;
import Entity.Post;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abdennour
 */
public class CommentaireService {

    Connection cnx = BD.getInstance().getCnx();
    PreparedStatement stmt;

    public void ajouterCommentaire(Commentaire c, int id_p) {
        try {
            // Define the SQL INSERT statement
            String sql = "INSERT INTO commentaire (contenu, post_id) VALUES (?, ?)";
            stmt = cnx.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Set the parameter values for the INSERT statement
            stmt.setString(1, c.getContenu());
           // stmt.setString(2, c.getNom());
            stmt.setInt(2, id_p); // Use the provided post_id

            stmt.executeUpdate();
            System.out.println("Commentaire ajoutée avec succès !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
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
        
        public List<Commentaire> afficheCommentaireByIdPost(int idPost) {
        List<Commentaire> commentaires = new ArrayList<>();

        try {
            // Define the SQL SELECT statement
            String sql = "SELECT * FROM commentaire WHERE post_id = ?";
            stmt = cnx.prepareStatement(sql);
            stmt.setInt(1, idPost);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Commentaire commentaire = new Commentaire();
                commentaire.setId_coment(rs.getInt("id_coment"));
                commentaire.setContenu(rs.getString("contenu"));
                commentaire.setNom(rs.getString("nom"));
                commentaire.setPost_id(rs.getInt("post_id"));

                commentaires.add(commentaire);
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return commentaires;
    }
        
        
        
        
        
            public void supprimerComment(int id) {
    try {
        String req=" DELETE FROM commentaire WHERE id_coment="+ id ;

        PreparedStatement St = cnx.prepareStatement(req);
        St.executeUpdate();
        System.out.println("Le Commentaire est supprimé");}
     catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }    
   }
        
        
    
       public void Clear(int id) {
    String req = "DELETE FROM commentaire  WHERE post_id="+ id;
    try {
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.executeUpdate();
        System.out.println("Toutes les lignes de la table 'commentaire' ont été supprimées !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
       }
}