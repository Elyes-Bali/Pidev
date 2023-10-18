/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.agence.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author balia
 */
public class MyDB {
    //
    String url = "jdbc:mysql://localhost:3306/BTAgency?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC";
    String user = "root";
    String pwd = "";
   private Connection cnx;
    private static MyDB instance;
 
    private MyDB() {
       
        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    public Connection getCnx() {
        return cnx;
    }
    
    public static MyDB getInstance(){
        if(instance == null){
            instance = new MyDB();
        }
         return instance;
    }
    
}
