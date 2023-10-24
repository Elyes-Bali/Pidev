/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Ons
 */
public class DashboardController implements Initializable {
    
    @FXML
    private Button btnCateg;

    @FXML
    private Button btnHeb;

    @FXML
    private Button btnHome;

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane viewPages;
    
    
    @FXML
    public void switchForm(ActionEvent event) throws IOException{
        if(event.getSource()== btnHeb){
            Parent fxml= FXMLLoader.load(getClass().getResource("listHebergement.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        }else if(event.getSource()==btnCateg){
            Parent fxml= FXMLLoader.load(getClass().getResource("listCateg.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }      
    
}
