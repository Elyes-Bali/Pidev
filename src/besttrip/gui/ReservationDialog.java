package besttrip.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class ReservationDialog {

    public static Pair<String, Integer> showReservationDialog(ReservationHandler handler) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Réservation d'événement");
        dialog.setHeaderText("Veuillez entrer vos informations de réservation");

        // Créez les champs de texte pour le numéro de téléphone et le nombre de places
        TextField phoneNumberField = new TextField();
        TextField numberOfSeatsField = new TextField();

        // Créez une grille pour afficher les champs
        GridPane grid = new GridPane();
        grid.add(new Label("Numéro de téléphone :"), 0, 0);
        grid.add(phoneNumberField, 1, 0);
        grid.add(new Label("Nombre de places :"), 0, 1);
        grid.add(numberOfSeatsField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // Ajoutez les boutons OK et Annuler
     // Utilisez ButtonType.OK et ButtonType.CANCEL pour les boutons
ButtonType buttonTypeOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
dialog.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);


       dialog.showAndWait().ifPresent(result -> {
        if (result == buttonTypeOK) {
            // Récupérez les valeurs entrées par l'utilisateur
            String phoneNumber = phoneNumberField.getText();
            int numberOfSeats = Integer.parseInt(numberOfSeatsField.getText());

            // Appelez la méthode de l'interface pour gérer la réservation
            handler.onReservation(phoneNumber, numberOfSeats);
        }
    });

    // Retournez les informations de réservation
    return null;
    }
}
