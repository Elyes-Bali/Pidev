/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Besttrip.agence.services.ServiceTransport;

/**
 *
 * @author PC
 */
public class transportStatsController {

    @FXML
    private PieChart capacityChart;

    @FXML
    private PieChart priceChart;
    @FXML
    private Label capacityLabel;

    @FXML
    private Label priceLabel;

    //retourrr
    @FXML
    private Button retourrr;
    ServiceTransport transportDAO = new ServiceTransport();

    public void initialize() {
        // Retrieve data (e.g., capacities and prices) from your data source
        int capacitiesBelow10 = transportDAO.getCapacitiesBelow10();
        int capacitiesBetween10And50 = transportDAO.getCapacitiesBetween10And50();
        int capacitiesAbove50 = transportDAO.getCapacitiesAbove50();
        int pricesBelow300 = transportDAO.getPricesBelow300();
        int pricesAbove300 = transportDAO.getPricesAbove300();

        // Populate the capacity chart
        // Populate the capacity chart
capacityChart.setData(FXCollections.observableArrayList(
    new PieChart.Data("Below 10", capacitiesBelow10),
    new PieChart.Data("10-50", capacitiesBetween10And50),
    new PieChart.Data("Above 50", capacitiesAbove50)
));
capacityChart.setLegendVisible(true);
capacityLabel.setText("Distribution of Capacities");

for (PieChart.Data data : capacityChart.getData()) {
    if (data.getName().equals("Below 10")) {
        data.getNode().setStyle("-fx-pie-color: #FF0000; -fx-font-size: 12px;"); // Red color
    } else if (data.getName().equals("10-50")) {
        data.getNode().setStyle("-fx-pie-color: #FFA500; -fx-font-size: 12px;"); // Orange color
    } else if (data.getName().equals("Above 50")) {
        data.getNode().setStyle("-fx-pie-color: #008000; -fx-font-size: 12px;"); // Green color
    }
}

// Populate the price chart
priceChart.setData(FXCollections.observableArrayList(
    new PieChart.Data("Below 300", pricesBelow300),
    new PieChart.Data("Above 300", pricesAbove300)
));
priceChart.setLegendVisible(true);
priceLabel.setText("Distribution of Prices");

for (PieChart.Data data : priceChart.getData()) {
    if (data.getName().equals("Below 300")) {
        data.getNode().setStyle("-fx-pie-color: #FF0000; -fx-font-size: 12px;"); // Red color
    } else if (data.getName().equals("Above 300")) {
        data.getNode().setStyle("-fx-pie-color: #FFA500; -fx-font-size: 12px;"); // Orange color
    }
}

    }

    @FXML
    private void retourrr(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("listeTransport.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) retourrr.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
 @FXML
    private void ListeEvent(ActionEvent event) {
    }
    //retourt
 
     @FXML
    private Button gototransport;
    @FXML
    private void ListeTransp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("listeTransport.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) gototransport.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ListeCircuit(ActionEvent event) {
    }

    @FXML
    private void ListeHeberg(ActionEvent event) {
    }

    @FXML
    private void ListeRec(ActionEvent event) {
    }

    @FXML
    private void ListeForum(ActionEvent event) {
    }

    @FXML
    private void LogoutListeUtilisateur(ActionEvent event) {
    }
    
    
    
}
