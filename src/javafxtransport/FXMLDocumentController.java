/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtransport;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author amadd
 */
public class FXMLDocumentController implements Initializable {
    
   @FXML
    private Button sa_add;

    @FXML
    private Button sa_delete;

    @FXML
    private AnchorPane sa_form;

    @FXML
    private Label sa_g;

    @FXML
    private Hyperlink sa_ret;

    @FXML
    private Button sa_search;

    @FXML
    private Button sa_update;

    @FXML
    private Label se_aj;

    @FXML
    private TextField se_capa;

    @FXML
    private Label se_capaci;

    @FXML
    private Label se_dad;

    @FXML
    private Label se_dar;

    @FXML
    private TextField se_dda;

    @FXML
    private TextField se_dde;

    @FXML
    private AnchorPane se_form;

    @FXML
    private Label se_ty;

    @FXML
    private ComboBox<?> se_typ;

    @FXML
    private Button se_valider;

    @FXML
    private Button sz_bu;

    @FXML
    private Button sz_but;

    @FXML
    private Label sz_label;
    
     public void switchForm(ActionEvent event){
     
    TranslateTransition slider = new TranslateTransition();
    if (event.getSource()== sa_add){
        slider.setNode(sa_form);
        slider.setToX(300);
        slider.setDuration(Duration.seconds(.5));
        
        slider.play();
    }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
