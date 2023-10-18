/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aneex
 */
public class BD {

    private static BD instance;
    private Connection cnx;

    private final String URL = "jdbc:mysql://localhost:3306/blog";
    private final String LOGIN = "root";
    private final String PASSWORD = "";

    private BD() {
        try {
            cnx = (Connection) DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Conncting !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static BD getInstance() {
        if (instance == null) {
            instance = new BD();
        }
        return instance;
    }

    public java.sql.Connection getCnx() {
        return cnx;
    }


}
