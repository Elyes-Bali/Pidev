/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.services;

import Besttrip.agence.entity.CategoryHebergement;
import Besttrip.agence.entity.CrudCategHeb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import Besttrip.agence.tools.MyDB;

/**
 *
 * @author Ons
 */
public class CategoryService implements CrudCategHeb<CategoryHebergement>{

    public Connection conx;
    public Statement stm;

    
    public CategoryService() {
        conx = MyDB.getInstance().getConx();

    }
    
    @Override
    public void ajouter(CategoryHebergement c) {
        String req = 
                "INSERT INTO categorie_heb"
                + "(contenu)"
                + "VALUES(?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setString(1, c.getContenu());
            ps.executeUpdate();
            System.out.println("Categorie Hebergement Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(CategoryHebergement c) {
        try {
            String req = "UPDATE categorie_heb SET contenu=? WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(2, c.getId());
            pst.setString(1, c.getContenu());
            pst.executeUpdate();
            System.out.println("Categorie Hebergement Modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM categorie_heb WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Categorie Hebergement suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    @Override
    public List<CategoryHebergement> Show() {
        List<CategoryHebergement> list = new ArrayList<>();

        try {
            String req = "SELECT * from categorie_heb";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new CategoryHebergement(rs.getInt("id"), rs.getString("contenu")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<CategoryHebergement> Search(String t) {
        List<CategoryHebergement> list1 = new ArrayList<>();
        List<CategoryHebergement> list2 = Show();
        list1 = (list2.stream().filter(c -> c.getContenu().startsWith(t)).collect(Collectors.toList()));

        return list1;
    }
    
    public List<CategoryHebergement> triContenu() {

        List<CategoryHebergement> list1 = new ArrayList<>();
        List<CategoryHebergement> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getContenu().compareTo(o2.getContenu())).collect(Collectors.toList());
        return list1;
    }
    
}
