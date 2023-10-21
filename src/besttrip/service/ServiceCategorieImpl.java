/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.service;

/**
 *
 * @author Dhouha
 */
import java.util.List;
import besttrip.entity. Categorie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

import besttrip.tools.DB;
public class ServiceCategorieImpl implements ServiceCategorie {
    ObservableList<Categorie>obListProd = FXCollections.observableArrayList();
    
      Connection conn;
    public ServiceCategorieImpl() {
        conn = DB.getInstance().getConnection();
    }
    @Override
    public void ajouterCategorie(Categorie.Type type, String description) {
         String req = "INSERT INTO categorie_p(nom_c, description_c) VALUES(?, ?)";

        try {
            PreparedStatement stm = conn.prepareStatement(req);
            stm.setString(1, type.getLibelle());
            stm.setString(2, description);

            stm.executeUpdate();
            System.out.println("Catégorie ajoutée : " + type.getLibelle());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    

    @Override
    public List<Categorie> affichageCategories() {
        // Récupérer la liste de catégories depuis la base de données
        // Retournez une liste de catégories
        return null;
    }

    @Override
    public void supprimerCategorie(Categorie.Type type) {
        // Supprimer la catégorie de la base de données en utilisant le type
    }

    @Override
    public Categorie getCategorie(Categorie.Type type) {
        // Récupérer la catégorie de la base de données en utilisant le type
        // Retournez la catégorie
        return null;
    }

    @Override
    public boolean existe(Categorie.Type type) {
        // Vérifiez si la catégorie existe dans la base de données en utilisant le type
        // Retournez true si la catégorie existe, sinon false
        return false;
    }
}