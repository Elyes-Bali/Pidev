package tn.esprit.Services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entity.Transport;
import tn.esprit.entity.reservation;
import tn.esprit.tools.MyDB;

/**
 *
 * @author amadd
 */
public class servicereservation implements IServicer<reservation> {

    public Connection conx;
    public Statement stm;

    
    public servicereservation() {
        conx = MyDB.getInstance().getCon();

    }

    @Override
    public void addreservation(reservation r) {
         String sql = "INSERT INTO reservation (transport_id, client_id, debutReservartion) VALUES (?,?,?)";
        try {
           
            PreparedStatement pre = MyDB.getInstance().getCon().prepareStatement(sql);
            pre.setInt(1, r.getTransportId());
            pre.setInt(2, r.getClientId());
            pre.setDate(3, new java.sql.Date(r.getDebutReservation().getTime()));
            pre.executeUpdate();
            System.out.println("Reservation Ajoutée !!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updatereservation(reservation r) {
        try {
            String sql = "UPDATE reservation SET transport_id = ?, client_id = ?, debutReservartion = ? WHERE id = ?";
            PreparedStatement pre = MyDB.getInstance().getCon().prepareStatement(sql);
            pre.setInt(1, r.getTransportId());
            pre.setInt(2, r.getClientId());
            pre.setDate(3, new java.sql.Date(r.getDebutReservation().getTime()));
            pre.setInt(4, r.getId());
           
            pre.executeUpdate();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletereservation(int reservationId) {
        try {
            String sql = "DELETE FROM reservation WHERE id = ?";
            PreparedStatement pre = MyDB.getInstance().getCon().prepareStatement(sql);
            pre.setInt(1, reservationId);
            pre.executeUpdate();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<reservation> getAllreservation() {
        List<reservation> reservations = new ArrayList<>();

        try {
            String sql = "SELECT * FROM reservation";
            Statement stmt = MyDB.getInstance().getCon().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int transport_id = rs.getInt("transport_id");
                int client_id = rs.getInt("client_id");
                
                Date debutReservartion = rs.getDate("debutReservartion");
                
                reservations.add(new reservation(transport_id, client_id, debutReservartion));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
    
    public reservation getReservationByTransportAndClient(int transportId, int clientId) {
    String sql = "SELECT * FROM reservation WHERE transport_id = ? AND client_id = ? LIMIT 1";

    try {
        PreparedStatement pre = conx.prepareStatement(sql);
        pre.setInt(1, transportId);
        pre.setInt(2, clientId);

        ResultSet rs = pre.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            Date debutReservation = rs.getDate("debutReservartion");

            return new reservation(id, transportId, clientId, debutReservation);
        }

        rs.close();
        pre.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null; // Return null if no matching reservation is found
}
    public int getReservationIdByClientId(int clientId) {
        int reservationId = -1; // Initialize to a default value (assuming -1 is not a valid ID)

        try {
            String sql = "SELECT id FROM reservation WHERE client_id = ? LIMIT 1";
            PreparedStatement pre = conx.prepareStatement(sql);
            pre.setInt(1, clientId);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                reservationId = rs.getInt("id");
            }

            rs.close();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservationId;
    }

}
