/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Besttrip.agence.entity.Circuit;
import Besttrip.agence.tools.MyDB;

/**
 *
 * @author balia
 */
public class ServiceCircuit implements IServiceCircuit<Circuit> {
    Connection con;
    Statement ste;
    
    
    public ServiceCircuit() {
    con = MyDB.getinstance().getCon(); 
}
    
   
   @Override
    public void ajouter(Circuit t) {
        try {
            String req = "INSERT INTO circuit(prix,depart,arrive,temps,categorie,description,pays)values(?,?,?,?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
           pre.setInt(1, t.getPrix());
        pre.setString(2, t.getDepart());
        pre.setString(3, t.getArrive());
        pre.setString(5, t.getCategorie());
        pre.setString(4, t.getTemps());
        pre.setString(6, t.getDescription());
        pre.setString(7, t.getPays());

            pre.executeUpdate();
            
            
            
            } catch (SQLException ex) {
                System.out.println(ex);
            
        }
       
    }
    
     @Override
    public void supprimer(Circuit t) {
       try {
            String req = "DELETE FROM circuit WHERE id=?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, t.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Circuit t) {
        try {
        String req = "UPDATE circuit SET prix=?, depart=?, arrive=?, temps=?, description=?, categorie=?, pays=? WHERE id=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, t.getPrix());
        pre.setString(2, t.getDepart());
        pre.setString(3, t.getArrive());
        pre.setString(4, t.getTemps());
        pre.setString(5, t.getDescription());
        pre.setString(7, t.getPays());
        pre.setString(6, t.getCategorie());
        pre.setInt(8, t.getId());// This should be the 7th parameter.


        pre.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex);
    } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
public List<Circuit> afficher() {
    List<Circuit> circuits = new ArrayList<>();
    String sql = "SELECT * FROM circuit";
    try {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery(sql);
        while (rs.next()) {
           // Circuit p = new Circuit(rs.getInt("id"), rs.getInt("prix"), rs.getString("depart"), rs.getString("arrive"), rs.getString("categorie"),rs.getString("temps"),rs.getString("description"),rs.getString("pays")) ;
           Circuit p = new Circuit(rs.getInt("id"), rs.getInt("prix"), rs.getString("depart"), rs.getString("arrive"), rs.getString("temps"), rs.getString("categorie"), rs.getString("description"), rs.getString("pays"));
           circuits.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return circuits;
}

   

}
