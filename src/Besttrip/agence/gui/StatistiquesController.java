/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import Besttrip.agence.entity.Hebergement;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import Besttrip.agence.services.HebergementService;

/**
 * FXML Controller class
 *
 * @author Ons
 */
public class StatistiquesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private LineChart<String, Integer> lineChartHeb;
    
    
    @FXML
    private AnchorPane statPane;
    
    @FXML
    void return_listHebergement(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("listHebergement.fxml"));
        statPane.getChildren().removeAll();
        statPane.getChildren().setAll(fxml);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statistique();
    }    
    
    
    public void statistique() {
        HebergementService hs = new HebergementService();

        List<Hebergement> heb = null;
        heb = hs.Show();
        
        

        // Créer les axes pour le graphique
        final NumberAxis yAxis = new NumberAxis();
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Adresse Hebergement");
        yAxis.setLabel("Capacite Hebergement");

        // Créer la série de données à afficher
        XYChart.Series series = new XYChart.Series();
        series.setName("Statistiques des hebergements selon leurs capacite");
        for (Hebergement h : heb) {
            series.getData().add(new XYChart.Data<>(h.getAdresse(), h.getCapacite()));
        }

        // Créer le graphique et ajouter la série de données
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Statistiques des Hebergements");
        lineChart.getData().add(series);

        // Afficher le graphique dans votre scène
        lineChartHeb.setCreateSymbols(false);
        lineChartHeb.getData().add(series);
        
    }
    
}
