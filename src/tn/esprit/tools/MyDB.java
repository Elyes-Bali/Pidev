/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author amadd
 */
public class MyDB {

    
    private final String url = "jdbc:mysql://localhost:3306/pidev";
    private final String user = "root";
    private final String password = "";

     private Connection con;
    private static MyDB instance;

    public MyDB() {
        try {
            
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.err.println("Connection error: " + ex.getMessage());
            
        }
    }

    public static MyDB getInstance() {
        if (instance == null) {
            instance = new MyDB();
        }
        return instance;
    }

    public Connection getCon() {
        return con;
    }

}
