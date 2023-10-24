/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Besttrip.agence.entity.Circuit;

/**
 * FXML Controller class
 *
 * @author balia
 */
public class DetailsController implements Initializable {

    @FXML
    private Label Pdepart;
    @FXML
    private Label Parrive;
    @FXML
    private Label Ptemps;
    @FXML
    private Label Pprix;
    @FXML
    private Label Pcategorie;
    @FXML
    private TextArea Pdescription;
    @FXML
    private AnchorPane achro;
    @FXML
    private TextArea newsTextArea;
    @FXML
    private Label Pweather;

    @FXML
    private TextArea Weatherdesc;
    @FXML
    private Label Ppays;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void initializeDetails(Circuit circuit) {
        Pdepart.setText(circuit.getDepart());
        Parrive.setText(circuit.getArrive());
        Ptemps.setText(circuit.getTemps());
        Pprix.setText(String.valueOf(circuit.getPrix()));
        Pcategorie.setText(circuit.getCategorie());
        Pdescription.setText(circuit.getDescription());        
        Ppays.setText(circuit.getPays());

        fetchAndDisplayNews();
        fetchAndDisplayWeather();
   
    }
     
     
     public void fetchAndDisplayNews() {
    // Replace with your News API key
    String apiKey = "9e225e9070cc4c2f93c52287dc3720be";

 String country = Pcategorie.getText().trim();

    // Replace with the endpoint URL for the news source or category you want
    String apiUrl = "https://newsapi.org/v2/top-headlines?country=" + country + "&apiKey=" + apiKey;
System.out.println("API URL: " + apiUrl);
    try {
        // Create a URL object
        URL url = new URL(apiUrl);

        // Open a connection to the URL
       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == 200) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Clear the existing text in the TextArea
            newsTextArea.clear();
newsTextArea.setWrapText(true);
            // Parse the JSON response using Gson
            JsonElement jsonElement = JsonParser.parseString(response.toString());

            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                if (jsonObject.has("articles")) {
                    JsonArray articles = jsonObject.getAsJsonArray("articles");

                    for (JsonElement article : articles) {
                        if (article.isJsonObject()) {
                            JsonObject articleObject = article.getAsJsonObject();

                            JsonElement titleElement = articleObject.get("title");
                            JsonElement descriptionElement = articleObject.get("description");

                            String title = (titleElement != null && !titleElement.isJsonNull()) ? titleElement.getAsString() : "Title not available";
                            String description = (descriptionElement != null && !descriptionElement.isJsonNull()) ? descriptionElement.getAsString() : "Description not available";

                            // Append each article on a new line
                            newsTextArea.appendText("Title: " + title + "\n");
                            newsTextArea.appendText("Description: " + description + "\n\n");
                        }
                    }
                }
            }
        } else {
            System.err.println("HTTP Error: " + responseCode);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
     
     
   public void fetchAndDisplayWeather() {
    String apiKey = "171ed24ecb9a792457ced5f5dab6c147";
    String country = Parrive.getText().trim();
    //String country ="paris";
    String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + country + "&appid=" + apiKey;

    try {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == 200) {
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response for weather data
            JsonElement jsonElement = JsonParser.parseString(response.toString());

            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();

                // Extract the relevant weather information (e.g., temperature, conditions)
                JsonObject main = jsonObject.getAsJsonObject("main");
                double temperature = main.get("temp").getAsDouble();

                JsonArray weatherArray = jsonObject.getAsJsonArray("weather");
                JsonObject weatherObject = weatherArray.get(0).getAsJsonObject();
                String weatherDescription = weatherObject.get("description").getAsString();
 // Convert Kelvin to Celsius
                double temperatureCelsius = temperature - 273.15;
                // Display the weather information in your UI
                // For example:
                 Pweather.setText(String.format("%.2f °C", temperatureCelsius));
                Weatherdesc.setText("Weather: " + weatherDescription + "\n");
            }
        } else {
            System.err.println("HTTP Error: " + responseCode);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}



     
     

    @FXML
    private void returnHome(ActionEvent event) {
          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Besttrip/agence/gui/Liste.fxml"));
        Parent root = loader.load();
        
        // Access the StartController if needed
        ListeController listeController = loader.getController();
        // Initialize data or perform other operations here
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println("Error loading Start.fxml: " + ex.getMessage());
    }
    }
}
