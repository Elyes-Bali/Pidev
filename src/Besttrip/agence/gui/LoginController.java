/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import Besttrip.agence.entity.User;
import Besttrip.agence.services.Services;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.CurrentUser;

/**
 * FXML Controller class
 *
 * @author Maroua SANDI
 */
public class LoginController implements Initializable {
    private Services pcd ;

    @FXML
    private TextField emailLogin;
    @FXML
    private TextField passwordLogin;
    @FXML
    private CheckBox shownmdp;
    @FXML
    private Hyperlink mdpoublie;
    @FXML
    private Hyperlink Insecrirnew;
    @FXML
    private PasswordField password;
      private String originalPassword = "";

 
public LoginController() {
        // Initialize the Services instance in the constructor
        pcd = new Services();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         password.textProperty().addListener((observable, oldValue, newValue) -> {
        originalPassword = newValue;
    });
        // TODO
    }    
    
  

    @FXML
    private void SEconncter(ActionEvent event) {
        Services pcd = new Services();
        String emailText = emailLogin.getText();
        String pwdText = password.getText();
        if (emailText.isEmpty() || pwdText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("E-mail ou  Mot de passe vide !!!");
            alert.showAndWait();
        } else {
            try {
                if (pcd.login(emailText, pwdText)) {
                    User user = pcd.getLogedUser(emailText);
                    CurrentUser.setCurrentUser(user);
                    if (user.getEmail().equalsIgnoreCase("sandi.maroua@yahoo.com")) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Listeutilisateur.fxml"));
                        Parent root = loader.load();
                        ListeutilisateurController dc = loader.getController();
                        emailLogin.getScene().setRoot(root);

                    } else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
                        Parent root = loader.load();
                        AcceuilController dc = loader.getController();
                        System.out.println(user);
                        dc.setUserInformation(user);
                       
                        emailLogin.getScene().setRoot(root);
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("E-mail ou Mot de passe pas correct");
                    alert.showAndWait();

                }

            } catch (SQLException ex) {

                System.out.println("First error" + ex.getMessage());
            } catch (IOException ex) {

                System.out.println("Second error" + ex.getMessage());
            }

        }

    }
    
   

    @FXML
    private void shownpass(ActionEvent event) {
      if (password.isVisible()) {
        // Password is currently visible, so hide it
        password.setVisible(false);
        passwordLogin.setText(originalPassword);
    } else {
        // Password is currently hidden, so show it
        passwordLogin.clear();
        password.setVisible(true);
    }
        
    }

    @FXML
    private void HLmdp(ActionEvent event) {
       try{FXMLLoader loader =new FXMLLoader(getClass().getResource("/Besttrip/agence/gui/MdpOublié.fxml"));
        Parent root =loader.load();
        
        MdpOubliéController starController =loader.getController();
        Scene scene =new Scene (root);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        }catch (IOException ex){
            System.out.println("Error loading MdpOublié.fxml :" + ex.getMessage());
        }}
    

    @FXML
    private void changeFormInsercir(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscrir.fxml"));
            Parent root = loader.load();
            InscrirController dc = loader.getController();
            emailLogin.getScene().setRoot(root);// pour charge l'interface
        } catch (IOException ex) {
       
            System.out.println(ex.getMessage());
     }  
        }
        
    
}
