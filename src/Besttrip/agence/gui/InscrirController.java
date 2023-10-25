/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;
import Besttrip.agence.entity.User;
import Besttrip.agence.services.Services;
import org.apache.commons.lang3.RandomStringUtils;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Maroua SANDI
 */
public class InscrirController implements Initializable {

    @FXML
    private ImageView imageInscrire;
    @FXML
    private TextField emailInscrir;
    @FXML
    private TextField mdpInscrire;
    @FXML
    private TextField NomU;
    @FXML
    private TextField prenomU;
    @FXML
    private TextField telIscrir;
    @FXML
    private Button sinscrir;
    @FXML
    private Button exit1;
    @FXML
    private Label deuxiemeemail;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
    }    

    @FXML
private void Sinscrir(ActionEvent event) {
    String Nom = NomU.getText();
    String Prenom = prenomU.getText();
    String email = emailInscrir.getText();
    String mdp = mdpInscrire.getText();
    String telFn = telIscrir.getText();
    Services ps = new Services();

    int tel;
    try {
        tel = Integer.parseInt(telFn);
    } catch (NumberFormatException e) {
        showAlert(Alert.AlertType.ERROR, "Erreur", "Numéro de téléphone invalide", e.getMessage());
        return;
    }

    if (Nom.isEmpty() || mdp.isEmpty() || email.isEmpty()) {
        showAlert(Alert.AlertType.ERROR, "Message d'erreur", null, "Veuillez remplir tous les champs !");
        return;
    } else if (mdp.length() < 8) {
        showAlert(Alert.AlertType.ERROR, "Message d'erreur", null, "Mot de passe invalide (doit avoir au moins 8 caractères) !");
        return;
    }

    // Check if the entered username already exists
    String enteredUsername = emailInscrir.getText();
    // You can use the email as the username for this example
    if (isUsernameTaken(enteredUsername)) {
        String suggestedUsername = generateSuggestedUsername(enteredUsername);
        showAlert(Alert.AlertType.INFORMATION, "email existe",
                "L'email d'utilisateur est déjà utilisé.",
                "Vous pouvez essayer un autre email d'utilisateur ou utiliser le nom d'utilisateur suggéré: " + suggestedUsername);
        deuxiemeemail.setText(suggestedUsername); // Update your UI field with the suggested username
        emailInscrir.setText(suggestedUsername); // Update the email field with the suggested username
        return;
    }

    String hashedPassword = BCrypt.hashpw(mdp, BCrypt.gensalt());
    //String Role = "Client";
    User u = new User(Nom, Prenom, email, tel, hashedPassword);

    try {
        ps.ajouter(u);
        showAlert(Alert.AlertType.INFORMATION, "Succès", "Compte ajouté avec succès", null);
        clearTextFields();
    } catch (Exception e) {
        showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de l'ajout de compte", e.getMessage());
        e.printStackTrace();
    }
}

    
    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);

    alert.showAndWait();
    
}
    
    private void showAlerting(String title, String contentText) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(contentText);

    alert.showAndWait();
}


    @FXML
    private void exit1(ActionEvent event) {
        try{FXMLLoader loader =new FXMLLoader(getClass().getResource("/Besttrip/agence/gui/Login.fxml"));
        Parent root =loader.load();
        
        LoginController starController =loader.getController();
        Scene scene =new Scene (root);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        }catch (IOException ex){
            System.out.println("Error loading Login.fxml :" + ex.getMessage());
        }
    }
    
    private void clearTextFields(){
    
    emailInscrir.clear();
    mdpInscrire.clear();
    NomU.clear();
    prenomU.clear();
    telIscrir.clear();   
   

    }

    @FXML
    private void emaildeux(ActionEvent event) {
      
       
    }
    private boolean isUsernameTaken(String username) {
   try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC", "root", "")) {
        // Write an SQL query
        String sql = "SELECT COUNT(*) FROM user WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    // Check the result
                    return count > 0;
                }
            }
        }
    } catch (SQLException e) {
        // Handle any database-related exceptions here
        e.printStackTrace();
    }

    // Return false by default or in case of any errors
    return false;
}
  
    private String generateSuggestedUsername(String originalUsername) {
    String suggestedUsername;
    do {
        String suffix = RandomStringUtils.randomAlphanumeric(4); // You can adjust the length as needed
        suggestedUsername = suffix+originalUsername;
    } while (isUsernameTaken(suggestedUsername));
    return suggestedUsername;
}
    
         
        
    }
     
    

