/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.agence.entity;

import besttrip.agence.tools.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author zouar
 */
public class Reclamation {

    public static void ajouter(Reclamation R) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int idRec;
    private String intitule;
    private String textRec;
    private int idU;

    public Reclamation(int idRec, String intitule, String textRec, int idU, String emailU) {
        this.idRec = idRec;
        this.intitule = intitule;
        this.textRec = textRec;
        this.idU = idU;
        this.emailU = emailU;
    }
    private String emailU ;

    public Reclamation(String intitule, String textRec, int idU, String emailU) {
        this.intitule = intitule;
        this.textRec = textRec;
        this.idU = idU;
        this.emailU = emailU;
    }

    public int getIdRec() {
        return idRec;
    }

    public String getEmailU() {
        return emailU;
    }

    public void setEmailU(String emailU) {
        this.emailU = emailU;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public String getIntituleRec() {
        return intitule;
    }

    public void setIntituleRec(String intitule) {
        this.intitule = intitule;
    }

    public String getTextRec() {
        return textRec;
    }

    public void setTextRec(String textRec) {
        this.textRec = textRec;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public Reclamation() {
    }

    public Reclamation(int idRec, String intitule, String textRec, int idU) {
        this.idRec = idRec;
        this.intitule = intitule;
        this.textRec = textRec;
        this.idU = idU;
       
    }

    public Reclamation(String intitule, String textRec, int idU) {
        this.intitule = intitule;
        this.textRec = textRec;
        this.idU = idU;
    }
    @Override
    public String toString() {
        return "L'id de réclamation :"+this.idRec +" , l'id de reclameur : "+this.idU+" , l'intitule est : "+this.intitule+ " , le texte est : "+ this.textRec 
                + ", email est: "+this.emailU;
    }
    
    
    // ... (other attributes and constructors)

    public static Reclamation getReclamationById(int id) {
        Reclamation reclamation = null;

        try {
            Connection connection = MyDB.getInstance().getCnx();

            String query = "SELECT * FROM reclamations WHERE idRec = idRec";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                reclamation = new Reclamation(
                    resultSet.getInt("idRec"),
                    resultSet.getString("intitule"),
                    resultSet.getString("textRec"),
                    resultSet.getInt("idU"),
                    resultSet.getString("emailU")
                );
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return reclamation;
    }
    
}
