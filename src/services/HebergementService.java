/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CrudHebergement;
import entities.Hebergement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import utils.MyDB;

/**
 *
 * @author Ons
 */
public class HebergementService implements CrudHebergement<Hebergement>{

    public Connection conx;
    public Statement stm;

    
    public HebergementService() {
        conx = MyDB.getInstance().getConx();

    }
    
    @Override
    public void ajouter(Hebergement h) {
        String req = 
                "INSERT INTO hebergement"
                + "(capacite,prix,adresse,type,description,categorie)"
                + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, h.getCapacite());
            ps.setFloat(2, h.getPrix());
            ps.setString(3, h.getAdresse());
            ps.setString(4, h.getType());
            ps.setString(5, h.getDescription());
            ps.setInt(6, h.getCategorie());
            ps.executeUpdate();
            System.out.println("Hebergement Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Hebergement h) {
        try {
            String req = "UPDATE hebergement SET capacite=?, prix=?, adresse=?, type=?, description=?, categorie=? WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(7, h.getId());
            pst.setInt(1, h.getCapacite());
            pst.setFloat(2, h.getPrix());
            pst.setString(3, h.getAdresse());
            pst.setString(4, h.getType());
            pst.setString(5, h.getDescription());
            pst.setInt(6, h.getCategorie());
            pst.executeUpdate();
            System.out.println("Hebergement Modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM hebergement WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Hebergement suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    @Override
    public List<Hebergement> Show() {
        List<Hebergement> list = new ArrayList<>();

        try {
            String req = "SELECT * from hebergement";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Hebergement(rs.getInt("id"), rs.getInt("capacite"), 
                        rs.getFloat("prix"), rs.getString("adresse"), 
                        rs.getString("type"), rs.getString("description"), 
                        rs.getInt("categorie")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Hebergement> Search(String t) {
        List<Hebergement> list1 = new ArrayList<>();
        List<Hebergement> list2 = Show();
        list1 = (list2.stream().filter(c -> c.getAdresse().startsWith(t)).collect(Collectors.toList()));

        return list1;
    }
    
    public List<Hebergement> triAdresse() {

        List<Hebergement> list1 = new ArrayList<>();
        List<Hebergement> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getAdresse().compareTo(o2.getAdresse())).collect(Collectors.toList());
        return list1;
    }
    
    public List<Hebergement> triDescription() {

        List<Hebergement> list1 = new ArrayList<>();
        List<Hebergement> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getDescription().compareTo(o2.getDescription())).collect(Collectors.toList());
        return list1;
    }
    
    
}
