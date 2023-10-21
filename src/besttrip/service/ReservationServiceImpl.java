/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package besttrip.service;
import besttrip.entity.Evenement;
import besttrip.entity.Participant;
import besttrip.entity.Reservation;
import java.sql.Connection;

import besttrip.tools.DB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dhouha
 */
public class ReservationServiceImpl  implements ReservationService{
    
    private Connection conn; // Initialisez cette connexion dans le constructeur

    public ReservationServiceImpl() {
        conn = DB.getInstance().getConnection();
    }

@Override
public void ajouterReservation(Reservation reservation, int participantId) {
    String sql = "INSERT INTO reservations (places_reservees, evenement_id, participant_id, dateheure_reservation, validate) " +
                 "VALUES (?, ?, ?, ?, ?)";
    
    try {
       // Étape 1 : Rechercher le participant par numéro de téléphone
 

        // Étape 2 : Insérer la réservation
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, reservation.getPlacesReservees());
        preparedStatement.setInt(2, reservation.getEvenement().getId());
        preparedStatement.setInt(3, participantId); // Utilisez l'ID du participant trouvé ou -1 s'il n'existe pas
        preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // Date et heure actuelles
        preparedStatement.setBoolean(5, false); // Par défaut, la colonne 'validate' est définie sur false
        
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérez les exceptions ici en fonction de votre application
    }
}



@Override
public void modifierReservation(Reservation reservation) {
    String sql = "UPDATE reservations SET places_reservees = ?, evenement_id = ?, participant_id = ?, dateheure_reservation = ?, validate = ? WHERE id = ?";

    try {
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setInt(1, reservation.getPlacesReservees());
        preparedStatement.setInt(2, reservation.getEvenement().getId());
        preparedStatement.setInt(3, reservation.getParticipant().getId());
        preparedStatement.setTimestamp(4, new Timestamp(reservation.getDateHeureReservation().getTime()));
        preparedStatement.setBoolean(5, reservation.isValidate());
        preparedStatement.setInt(6, reservation.getId()); // ID de la réservation à mettre à jour

        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérez les exceptions ici en fonction de votre application
    }
}



    @Override
public void supprimerReservation(int id) {
    String sql = "DELETE FROM reservations WHERE id = ?";

    try   {
  PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);

         preparedStatement.executeUpdate();

        
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérez les exceptions ici en fonction de votre application
    }
}

@Override
public Reservation obtenirReservationParId(int id) {
    String sql = "SELECT id, places_reservees, evenement_id, participant_id, dateheure_reservation FROM reservations WHERE id = ?";
    Reservation reservation = null;

    try {
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Récupérer les données de la réservation depuis le résultat
            int reservationId = resultSet.getInt("id");
            int placesReservees = resultSet.getInt("places_reservees");
            int evenementId = resultSet.getInt("evenement_id");
            int participantId = resultSet.getInt("participant_id");
            Timestamp dateheureReservation = resultSet.getTimestamp("dateheure_reservation");

            // Créer un objet Reservation avec les données
            Evenement evenement = obtenirEvenementParId(evenementId); // Vous devez implémenter cette méthode
            Participant participant = obtenirParticipantParId(participantId); // Vous devez implémenter cette méthode

            reservation = new Reservation(reservationId, placesReservees, evenement, participant, dateheureReservation);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérez les exceptions ici en fonction de votre application
    }

    return reservation;
}

public Evenement obtenirEvenementParId(int id) {
     
    PreparedStatement stm = null;
    ResultSet rs = null;
    Evenement evenement = null;

    try {
 
        String sql = "SELECT * FROM evenements WHERE id = ?";
        stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        rs = stm.executeQuery();

        if (rs.next()) {
            int evenementId = rs.getInt("id");
            String nom = rs.getString("nom");
            Date dateDebut = rs.getDate("dateDebut");
            String description = rs.getString("description");
            int categorieId = rs.getInt("categorie_id");
            String lieu = rs.getString("lieu");
            String image = rs.getString("image");
            double tarif = rs.getDouble("tarif"); // Nouvelle colonne tarif
            int placesDisponibles = rs.getInt("places_disponibles"); 

            // Créez un objet Evenement avec les données de la base de donnéestarif
            evenement = new Evenement(evenementId, nom, dateDebut, description, categorieId, lieu, image,tarif,placesDisponibles);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }  

    return evenement;
}
public Participant obtenirParticipantParId(int id) {
     PreparedStatement stm = null;
    ResultSet rs = null;
    Participant participant = null;

    try {
 
        String sql = "SELECT * FROM participants WHERE id = ?";
        stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        rs = stm.executeQuery();

        if (rs.next()) {
            int participantId = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String email = rs.getString("email");
            String telephone = rs.getString("telephone");

            // Créez un objet Participant avec les données de la base de données
            participant = new Participant(participantId, nom, prenom, email, telephone);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }  

    return participant;
}

 
@Override
public ObservableList<Reservation> obtenirToutesReservations() {
    ObservableList<Reservation> reservations = FXCollections.observableArrayList();
    String sql = "SELECT id, places_reservees, evenement_id, participant_id, dateheure_reservation ,validate FROM reservations";

    try {
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            // Récupérer les données de chaque réservation
            int reservationId = rs.getInt("id");
            int placesReservees = rs.getInt("places_reservees");
            int evenementId = rs.getInt("evenement_id");
            int participantId = rs.getInt("participant_id");
            Timestamp dateheureReservation = rs.getTimestamp("dateheure_reservation");
            boolean validate=rs.getBoolean("validate");
            // Créer un objet Reservation avec les données
            Evenement evenement = obtenirEvenementParId(evenementId); // Vous devez implémenter cette méthode
            Participant participant = obtenirParticipantParId(participantId); // Vous devez implémenter cette méthode

            Reservation reservation = new Reservation(reservationId, placesReservees, evenement, participant, dateheureReservation,validate);
            reservation.toString();
            reservations.add(reservation);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérez les exceptions ici en fonction de votre application
    }
     return reservations;
}
@Override

    public int foundParticpant(String phoneNumber) {
     String query = "SELECT id FROM participants WHERE telephone = ?";
    try {
        PreparedStatement participantQuery = conn.prepareStatement(query);
        participantQuery.setString(1, phoneNumber);
        ResultSet participantResult = participantQuery.executeQuery();

        if (participantResult.next()) {
            return participantResult.getInt("id");
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Gérer les exceptions de base de données ici
    }

    // Retourner -1 si le participant n'est pas trouvé
    return -1;
}

    }



