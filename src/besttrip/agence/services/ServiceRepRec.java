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
public class ServiceRepRec implements IServiceRepRec<ReponseReclamation>{
    Connection con;
    Statement ste;
    
        public ServiceRepRec() {
    con = MyDB.getInstance().getCnx();
}
    @Override
    public void save (ReponseReclamation t) {
        try {
            String req = "INSERT INTO reponsereclamation (textRec,textRepRec,idU ,idRec)values(?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1,t.getTextRec());
            pre.setString(2,t.getTextRepRec());            
            pre.setInt(3,t.getIdU());   
            pre.setInt(4,t.getIdRec());
            pre.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex);
           
        }  
    }
          @Override
public List<ReponseReclamation> afficher() {
    List<ReponseReclamation> reponsereclamation = new ArrayList<>();
    String sql = "SELECT * FROM reponsereclamation";
    try {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery(sql);
        while (rs.next()) {
            ReponseReclamation p = new ReponseReclamation(rs.getInt("idRepRec"), rs.getString("textRec"), rs.getString("textRepRec"), rs.getInt("idU"), rs.getInt("idRec"));
            reponsereclamation.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return reponsereclamation;
}
}
