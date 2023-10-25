/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import Besttrip.agence.tools.MyDB;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Maroua SANDI
 */
public class MdpOubliéController implements Initializable {

    @FXML
    private TextField oublieEmail;
    @FXML
    private Button Demande;

     Connection con;
    Statement ste;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void demandeMdp(ActionEvent event) {
    }

    @FXML
    private void retourOUBlie(ActionEvent event) {
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
     public void verifyAndSendResetEmail() {
        String emailToReset = oublieEmail.getText(); // Obtenez l'e-mail à réinitialiser depuis votre champ de texte

        // Vérifiez si l'e-mail existe dans la base de données
        if (emailExistsInDatabase(emailToReset)) {
            // Générez un mot de passe aléatoire
            String randomPassword = generateRandomPassword();

            // Enregistrez le nouveau mot de passe dans la base de données (vous devrez implémenter cette partie)

            // Envoyez un e-mail avec le nouveau mot de passe
            sendResetEmail(emailToReset, randomPassword);
            updatePasswordInDatabase(emailToReset, randomPassword);

            // Affichez un message à l'utilisateur pour lui dire que l'e-mail de réinitialisation a été envoyé
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Réinitialisation réussie");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Un e-mail avec un nouveau mot de passe a été envoyé à " + emailToReset);
            successAlert.showAndWait();

            
        } else {
            // L'e-mail n'existe pas dans la base de données, affichez un message d'erreur à l'utilisateur
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erreur");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("L'adresse e-mail n'existe pas dans notre base de données.");
            errorAlert.showAndWait();
        }
    }
    private void updatePasswordInDatabase(String email, String newPassword) {
        // Mettez à jour le mot de passe dans la base de données en utilisant l'e-mail comme condition
        con = MyDB.getInstance().getCon();
        String sql = "UPDATE user SET mdp = ? WHERE email = ?";

        try {
             PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, newPassword);
            pre.setString(2, email);
            int rowsAffected = pre.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Mot de passe mis à jour avec succès dans la base de données !");
            } else {
                System.err.println("Échec de la mise à jour du mot de passe dans la base de données.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la mise à jour du mot de passe dans la base de données : " + e.getMessage());
        }
    }
   private boolean emailExistsInDatabase(String email) {
    con = MyDB.getInstance().getCon();
    String sql = "SELECT * FROM user WHERE email = ?";
    try {
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1, email);
        ResultSet rs = pre.executeQuery(); // Use pre to execute the query
        return rs.next(); // If the result contains rows, the email exists
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // In case of an error, assume the email doesn't exist
    }
}


    private String generateRandomPassword() {
        // Générez un mot de passe aléatoire de 8 caractères (modifiable)
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }

        return password.toString();
    }

   public void sendResetEmail(String recipientEmail, String randomPassword) {
    // SMTP Configuration for Gmail
    String host = "smtp.gmail.com";
    String port = "587";
    String username = "besttrip.esprit1@gmail.com"; // Replace with your Gmail address
    String password = "qsfa wypu itwf miiu "; // Replace with your App Password

    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", port);

    // Create a session for sending the email
    Session session = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

    try {
        // Create a MimeMessage object
        Message message = new MimeMessage(session);

        // Set sender and recipient
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

        // Email subject
        message.setSubject("Password Reset");

        // Email body
        message.setText("Your new password is: " + randomPassword);

        // Send the email
        Transport.send(message);

        System.out.println("Email sent successfully!");
    } catch (MessagingException e) {
        e.printStackTrace();
        System.err.println("Error sending email: " + e.getMessage());
    }
}


    
}
