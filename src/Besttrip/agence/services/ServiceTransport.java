package Besttrip.agence.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Besttrip.agence.entity.Transport;

import Besttrip.agence.tools.MyDB;

public class ServiceTransport implements IServices<Transport> {
    Connection con ;
    Statement ste ;


    public ServiceTransport() {
        con = MyDB.getInstance().getCon() ;
       
    }

    @Override
    public void addTransport(Transport transport) {
        try {
            String sql = "INSERT INTO transport (cap, type, dd, da,prix) VALUES (?, ?, ?,?, ?)";
            PreparedStatement pre = MyDB.getInstance().getCon().prepareStatement(sql);
//            pre.setInt(1, transport.getId());
            pre.setInt(1, transport.getCap());
            pre.setString(2, transport.getType());
            pre.setString(3, transport.getDd());
            pre.setString(4, transport.getDa());
            pre.setInt(5, transport.getPrix());
            pre.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param transport
     */
    @Override
    public void updateTransport(Transport transport) {
        try {
            String sql = "UPDATE transport SET cap = ?, type = ?, dd = ?, da = ?, prix = ? WHERE id = ?";
            PreparedStatement pre = MyDB.getInstance().getCon().prepareStatement(sql);
            pre.setInt(1, transport.getCap());
            pre.setString(2, transport.getType());
            pre.setString(3, transport.getDd());
            pre.setString(4, transport.getDa());
            pre.setInt(5, transport.getPrix());
            pre.setInt(6, transport.getId());

            pre.executeUpdate();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean updateTransports(Transport transport) {
       Connection con = MyDB.getInstance().getCon(); // Get the database connection

    try {
        String sql = "UPDATE transport SET cap = ?, type = ?, dd = ?, da = ?, prix = ? WHERE id = ?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setInt(1, transport.getCap());
        pre.setString(2, transport.getType());
        pre.setString(3, transport.getDd());
        pre.setString(4, transport.getDa());
        pre.setInt(5, transport.getPrix());
        pre.setInt(6, transport.getId());

        int rowsAffected = pre.executeUpdate();

        if (rowsAffected > 0) {
            // Commit the changes since the update was successful
            con.commit();
            pre.close();
            return true;
        } else {
            // If no rows were affected, the update was not successful
            con.rollback();
            pre.close();
            return false;
        }
    } catch (SQLException e) {
        System.out.println("Erreur updating transport: " + e.getMessage());
        return false;
    }
    }
    
    
    public void deleteTransport(Transport transport) {
       try {
            String req = "DELETE FROM Transport WHERE id=?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, transport.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
    }

    @Override
    public List<Transport> getAllTransports() {
        List<Transport> transports = new ArrayList<>();

        try {
            String sql = "SELECT * FROM transport";
            Statement stmt =MyDB.getInstance().getCon().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int cap = rs.getInt("cap");
                String type = rs.getString("type");
                String dd = rs.getString("dd");
                String da = rs.getString("da");
                int prix = rs.getInt("prix");
                transports.add(new Transport( id, cap, type, dd, da,prix));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transports;
       
    }
    public Transport getTransportParID(int i) {
        Transport transport = null;
        String sql = "SELECT * FROM transport WHERE id = ?";
        
 
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, i);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                String dd = resultSet.getString("dd");
                int cap = resultSet.getInt("cap");    
                String da = resultSet.getString("da");
                int prix = resultSet.getInt("prix"); 
                transport = new Transport( id,  cap,  type,  dd,  da, prix);
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving data by ID from the database: " + ex.getMessage());
        }

        return transport ;
    }
    
    public Transport getTransportById(int transportId) {
        Transport transport = null;

        try {
            String sql = "SELECT type FROM transport WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, transportId);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String type = rs.getString("type");
                transport = new Transport(type);
            }

            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transport;
    }
    @Override
    public void deleteTransport(int id) {
    String sql = "DELETE FROM transport WHERE id = ?";

    try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
        preparedStatement.setInt(1, id);

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows > 0) {
            System.out.println("Transport with ID " + id + " deleted successfully.");
        } else {
            System.out.println("No transport found with ID " + id + ". No records deleted.");
        }
    } catch (SQLException ex) {
        System.out.println("Error deleting transport from the database: " + ex.getMessage());
    }
}
    
    public int getCapacitiesBelow10() {
        int count = 0;
        try {
            
            PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM transport WHERE cap < 10");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    public int getCapacitiesBetween10And50() {
        int count = 0;
        try {
                      PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM transport WHERE cap BETWEEN 10 AND 50");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    public int getCapacitiesAbove50() {
        int count = 0;
        try {
            
            PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM transport WHERE cap > 50");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    public int getPricesBelow300() {
        int count = 0;
        try {
            
            PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM transport WHERE prix < 300");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    public int getPricesAbove300() {
        int count = 0;
        try {
           
            PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM transport WHERE prix > 300");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
       
}
