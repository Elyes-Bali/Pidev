package besttrip.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
      private Connection conn;
 
    String url = "jdbc:mysql://localhost:3306/besttrip2_pi";
    String user = "root";
    String pwd = "";

    private static DB instance;

    public DB() {
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion établie!!!");
        } catch (SQLException ex) {
            System.out.println("Problème de connexion!!! " + ex.getMessage());
        }
    }

    // Singleton
    public static DB getInstance() {
        if (DB.instance == null) {
            DB.instance = new DB();
        }
        return DB.instance;
    }

    // Appel de la méthode pour obtenir la connexion
    public static Connection getConnection() {
        return DB.getInstance().conn;
    }

    public static void main(String[] args) {
        // Test de la connexion
        Connection connection = DB.getConnection();
        if (connection != null) {
           
        } else {
            System.out.println("La connexion à la base de données a échoué.");
        }
    }
}
