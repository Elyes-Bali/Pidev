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
public class ServiceRepRecy implements IServiceRepRecy<ReponseReclamation>{
    Connection con;
    Statement ste;
    
        public ServiceRepRecy() {
    con = MyDB.getInstance().getCnx();
}
    @Override
    public void save (ReponseReclamation t) {
        try {
            String req = "INSERT INTO reponsereclamation (idU,Prenom,intitule,textRepRec,idRec)values(?,?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1,t.getIdU());
            pre.setString(2, t.getPrenom() != null ? t.getPrenom() : ""); 
            pre.setString(3, t.getIntitule() != null ? t.getIntitule() : "");              
            pre.setString(4,t.getTextRepRec());   
            pre.setInt(5,t.getIdRec());
            pre.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex);
           
        }  
    }
          @Override
public List<ReponseReclamation> afficher() {
    List<ReponseReclamation> reponsereclamation = new ArrayList<>();
          String sql = "SELECT user.idU,user.Prenom, reclamation.intitule,reponsereclamation.textRepRec , reclamation.idRec  from user   Left Join reclamation On user.idU = reclamation.idU  Left Join reponsereclamation On reclamation.idRec = reponsereclamation.idRec ";

    try {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery(sql);
        while (rs.next()) {
              ReponseReclamation p = new ReponseReclamation(rs.getInt("idU"),  rs.getString("Prenom"), rs.getString("intitule"), rs.getString("textRepRec"), rs.getInt("idRec"));
              reponsereclamation.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return reponsereclamation;
}
public String getIntituleByIdRec(int idRec) {
    try {
        String sql = "SELECT intitule FROM reclamation WHERE idRec = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, idRec);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            return rs.getString("intitule");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null; // Return null if not found
}

}
