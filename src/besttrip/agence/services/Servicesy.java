/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.agence.services;

import besttrip.agence.entity.Reclamation;
import besttrip.agence.entity.ReponseReclamation;
import besttrip.agence.tools.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zouar
 */
public class Servicesy implements IServicey<Reclamation>{
    Connection con;
    Statement ste;
   
   
    public Servicesy() {
    con = MyDB.getInstance().getCnx();
}
   
//    public void create(Reclamation t) {
//        try {
//            String requete = "INSERT INTO reclamation(intitule,textRec,idU)values(?,?,?)";
//            PreparedStatement pst = MyDB.getInstance().getCnx()
//                    .prepareStatement(requete);
//            //pst.setInt(1, t.getIdRec());
//            pst.setString(1, t.getIntituleRec());
//            pst.setString(2, t.getTextRec());
//            pst.setInt(3,t.getIdU());
//            pst.executeUpdate();
//            System.out.println("Done!");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
   @Override
    public void ajouter(Reclamation t) {
        try {
            String req = "INSERT INTO reclamation(intitule,textRec,idU,emailU )values(?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
//            pre.setInt(1,t.getIdRec());
            pre.setString(1,t.getIntituleRec() );
            pre.setString(2,t.getTextRec());            
            pre.setInt(3,t.getIdU());   
            pre.setString(4,t.getEmailU());
            pre.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex);
           
        }
       
    }
//    @Override
//    public void save (ReponseReclamation t) {
//        try {
//            String req = "INSERT INTO reponsereclamation (textRec,textRepRec,idU ,idRec)values(?,?,?,?)";
//            PreparedStatement pre = con.prepareStatement(req);
//            pre.setString(1,t.getTextRec());
//            pre.setString(2,t.getTextRepRec());            
//            pre.setInt(3,t.getIdU());   
//            pre.setInt(4,t.getIdRec());
//            pre.executeUpdate();
//            } catch (SQLException ex) {
//                System.out.println(ex);
//           
//        }
//       
//    }    
     @Override
    public void supprimer(Reclamation t) {
       try {
            String req = "DELETE FROM reclamation WHERE idRec=?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, t.getIdRec());
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } //To change body of generated methods, choose Tools | Templates.
    }
   
//    public int count() throws SQLException {
//         int count = 0;
//        try {
//            String request = "SELECT COUNT(*) FROM reclamation";
//            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(request);
//            ResultSet rs = pst.executeQuery(request);
//            if (rs.next()) {
//                count = rs.getInt(1);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//
//        }
//        return count;
//    }

      @Override
public List<Reclamation> afficher() {
    List<Reclamation> reclamation = new ArrayList<>();
    String sql = "SELECT * FROM reclamation";
    try {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery(sql);
        while (rs.next()) {
            Reclamation p = new Reclamation(rs.getInt("idRec"), rs.getString("intitule"), rs.getString("textRec"), rs.getInt("idU"), rs.getString("emailU"));
           // Reclamation p = new Reclamation(rs.getInt("idRec"), rs.getString("intitule"), rs.getString("textRec"), rs.getInt("idU"),rs.getString("emailU")) ;
            reclamation.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return reclamation;
}
   
    

    
}
