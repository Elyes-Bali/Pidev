/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.entity;

import besttrip.agence.tools.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Maroua SANDI
 */
public class User {
    private int idU;
    private String Nom;
     private String Prenom ;
      private String email;
       private int tel;
       private String mdp ;
        private String gender;
       private String Role;
     
      
       
      

    public User(String Nom, String Prenom, String email, int tel, String mdp) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.email = email;
        this.tel = tel;
        this.mdp = mdp;
    }

    public User(int id, String emailtxt, String nom, String prenom, String pwd, int tel, String gender, String role) {
   this.idU =id;
   this.Nom=nom;
   this.Prenom=prenom;
   this.email=emailtxt;
   this.mdp=pwd;
   this.tel=tel;
   this.Role=role;
   this.gender=gender;
        
    }
    public User(int idU, String nom, String prenom, String email, int tel, String mdp, String gender) {
        this.idU=idU;
         this.Nom = nom;
        this.Prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.mdp = mdp;
   this.gender=gender;
}

    
   

   

  
    
    

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getNom() {
        return Nom;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
   
    
    /////////////////////////
  
public User (int idU,String Nom, String Prenom, String email, int tel, String mdp , String Role, String gender){
    
   this.idU =idU;
   this.Nom=Nom;
   this.Prenom=Prenom;
   this.email=email;
   this.mdp=mdp;
   this.tel=tel;
   this.Role=Role;
   this.gender=gender;
   
}
/////////////////////////////

    public User(String Nom, String Prenom, String Role, String email, int tel, String mdp, String gender) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Role = Role;
        this.email = email;
        this.tel = tel;
        this.mdp = mdp;
        this.gender = gender;
    }

   
/////////////////////////////////
    public User (){
    } 

    @Override
    public String toString() {
        return "User{" + "idU=" + idU + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Role=" + Role + ", email=" + email + ", tel=" + tel + ", mdp=" + mdp + ", gender=" + gender + '}';
    }
    
  public static User getUserById(int id) {
    // Initialize a User object to null
    User user = null;

    try {
        // Get the database connection from MyDB
        Connection connection = MyDB.getInstance().getCnx();

        // Prepare and execute a SQL query to retrieve the user by ID
        String query = "SELECT * FROM user WHERE idU = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Create a User object with data from the database
            user = new User(
                resultSet.getInt("idU"),
                resultSet.getString("email"),
                resultSet.getString("Nom"),
                resultSet.getString("Prenom"),
                resultSet.getString("mdp"),
                resultSet.getInt("tel"),
                resultSet.getString("gender"),
                resultSet.getString("Role")
            );
        }

        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception as needed
    }

    // Return the User object if found, or null if not found
    return user;
}



    }

  
    

