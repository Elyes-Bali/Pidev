/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.services;

import Besttrip.agence.entity.CrudReservation;
import Besttrip.agence.entity.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Besttrip.agence.tools.MyDB;

/**
 *
 * @author Ons
 */
public class ReservationService implements CrudReservation<Reservation>{

    public Connection conx;
    public Statement stm;

    
    public ReservationService() {
        conx = MyDB.getInstance().getConx();

    }
    
    @Override
    public void ajouter(Reservation r) {
        String req = 
                "INSERT INTO reservation"
                + "(date, id_heb)"
                + "VALUES(?, ?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setDate(1, new java.sql.Date(r.getDate().getTime()));
            ps.setInt(2, r.getId_heb());
            ps.executeUpdate();
            System.out.println("Reservation Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Reservation r) {
        String req = "UPDATE reservation SET date=?, id_heb=? WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(3, r.getId());
            pst.setDate(1, new java.sql.Date(r.getDate().getTime()));
            pst.setInt(2, r.getId_heb());
            pst.executeUpdate();
            System.out.println("Reservation modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM reservation WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Reservation suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    @Override
    public List<Reservation> Show() {
        List<Reservation> list = new ArrayList<>();

        try {
            String req = "SELECT * from reservation";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Reservation(rs.getInt("id"), rs.getDate("date"), 
                        rs.getInt("id_heb")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
}
