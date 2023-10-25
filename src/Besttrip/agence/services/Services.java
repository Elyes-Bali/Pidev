/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.services;

import Besttrip.agence.entity.User;
import Besttrip.agence.tools.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Maroua SANDI
 */
public class Services implements UServices<User>{
    
    
      Connection con;
    Statement ste;
   
   
    public Services() {
    con = MyDB.getInstance().getCon();
}

    @Override
    public void ajouter(User u) {
        try {
            String req = "INSERT INTO user(Nom,Prenom,email,tel,mdp,Role,gender)values(?,?,?,?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1,u.getNom() );
            pre.setString(2,u.getPrenom() );
            pre.setString(3,u.getEmail());            
            pre.setInt (4,u.getTel());          
            pre.setString(5,u.getMdp()); 
            pre.setString(6,u.getRole());
            pre.setString(7,u.getGender());

            pre.executeUpdate();
           
           
           
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
        }
       
        
    }
    

    @Override
    public void modifier(User u) {
           try {
        String req = "UPDATE user SET Nom=?, Prenom=?, email=?, tel=?, mdp=?, gender=? WHERE idU=?";
        PreparedStatement pre = con.prepareStatement(req);
pre.setString(1, u.getNom());
pre.setString(2, u.getPrenom());
pre.setString(3, u.getEmail());
pre.setInt(4, u.getTel());
pre.setString(5, u.getMdp());
pre.setString(6, u.getGender());
pre.setInt(7, u.getIdU());  // This should be the 7th parameter.


        pre.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex);
    } //To change body of generated methods, choose Tools | Templates.
    
        
    }

    @Override
    public void supprimer(User u) {
        try {
            String req = "DELETE FROM user WHERE idU=?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, u.getIdU());
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
    }

    @Override
    public List<User> afficher() {
        List<User> users = new ArrayList<>();
    String sql = "SELECT idU,Nom,Prenom,email,tel,mdp,gender from user where idU != 1";
    try {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery(sql);
        while (rs.next()) {
           User u = new User(rs.getInt("idU"), rs.getString("Nom"), rs.getString("Prenom"), rs.getString("email"), rs.getInt("tel"), rs.getString("mdp"), rs.getString("gender"));

            users.add(u);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return users;
}
    public User getUserById(int userId) {
        User user = null;

        try {
            String sql = "SELECT Nom, Prenom FROM user WHERE idU = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                user = new User(nom, prenom);
            }

            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
        
        //////Authentification///////////////////
    
         public User getLogedUser(String email) throws SQLException {
        User user = new User();
        String query = "SELECT * FROM user WHERE email = ?";
        PreparedStatement pst = MyDB.getInstance().getCon().prepareStatement(query);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("idU");
            String emailtxt = rs.getString("email");
            String nom = rs.getString("Nom");
            String prenom = rs.getString("Prenom");
            String pwd = rs.getString("mdp");
            int tel = rs.getInt("tel");
            String gender = rs.getString("gender");
            String role = rs.getString("Role");
           // user = new User(id,emailtxt,nom,role,prenom,mdp,tel,gender);
           user = new User (id , emailtxt,nom,prenom,pwd,tel,gender,role);

        }
        return user;
    }

 public Boolean isValidCredentials(String email, String mdp) throws SQLException {

    Boolean isValid = false;
    String query = "SELECT * FROM user WHERE email = ? AND mdp = ?";
    PreparedStatement pst = MyDB.getInstance().getCon().prepareStatement(query);
    pst.setString(1, email);
    pst.setString(2, mdp); 
    ResultSet rs = pst.executeQuery();
    if (rs.next()) {
        isValid = true;
    }
    return isValid;
}



    public Boolean login(String email, String mdp) throws SQLException {
        return isValidCredentials(email, mdp);
    }
     public Boolean isUserExist(String email) throws SQLException {
        Boolean isExist = false;
        String query = "SELECT * FROM user WHERE email = ?";
        PreparedStatement pst = MyDB.getInstance().getCon().prepareStatement(query);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            isExist = true;
            return isExist;
        }
        return isExist;

    }
     
     
     
     private void loadInitialDataFromDatabase() {

    }
}

