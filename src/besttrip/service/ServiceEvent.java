package besttrip.service;

import besttrip.entity.Categorie;
import besttrip.entity.Evenement;
import besttrip.entity.Participant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import besttrip.service.IEvent;
import besttrip.tools.DB;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ServiceEvent implements IEvent {
    private Connection conn;

    public ServiceEvent() {
        // Initialisez la connexion à la base de données ici
        conn = DB.getInstance().getConnection();
    }
 
@Override
public void ajouter(Evenement evenement) {
    String req = "INSERT INTO evenements (nom, dateDebut, description, categorie_id, lieu, image, tarif, places_disponibles) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
              System.out.println(evenement.getId());

    try {
        PreparedStatement stm = conn.prepareStatement(req);
        stm.setString(1, evenement.getNom());
java.util.Date utilDate = evenement.getDateDebut();
java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
stm.setDate(2, sqlDate);
        stm.setString(3, evenement.getDescription());
        stm.setInt(4, evenement.getCategorieId()); // Utilisez l'ID de la catégorie
        stm.setString(5, evenement.getLieu());
        stm.setString(6, evenement.getImage());
        stm.setDouble(7, evenement.getTarif()); // Ajoutez le tarif
        stm.setInt(8, evenement.getPlacesDisponibles()); // Ajoutez les places disponibles
        stm.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Gérer l'erreur d'insertion de l'événement
    }
}




 @Override
public List<Evenement> chercherCategorie(Categorie categorie) {
    // Recherchez et retournez tous les événements de la catégorie spécifiée depuis la base de données
    List<Evenement> result = new ArrayList<>();
    String req = "SELECT * FROM evenements WHERE categorie = ?"; // Mise à jour de la requête SQL
    try {
        PreparedStatement stm = conn.prepareStatement(req);
        stm.setObject(1, categorie);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            Date dateDebut = rs.getDate("dateDebut");
            String description = rs.getString("description");
            double tarif = rs.getDouble("tarif"); // Ajout du tarif
            int placesDisponibles = rs.getInt("places_disponibles"); // Ajout des places disponibles
            String lieu = rs.getString("lieu");
            String image = rs.getString("image");
            Evenement event = new Evenement(id, nom, dateDebut, description, categorie.getId(), lieu, image, tarif, placesDisponibles); // Mise à jour de la création de l'événement
            result.add(event);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return result;
}


  @Override
public void modifier(Evenement evenement) {
    // Modifiez l'événement dans la base de données
    String req = "UPDATE evenements SET nom = ?, dateDebut = ?, description = ?, lieu = ?, image = ?, tarif = ?, places_disponibles = ? WHERE id = ?";
    try {
        PreparedStatement stm = conn.prepareStatement(req);
        stm.setString(1, evenement.getNom());
        stm.setDate(2, new java.sql.Date(evenement.getDateDebut().getTime()));
        stm.setString(3, evenement.getDescription());
        stm.setString(4, evenement.getLieu());
        stm.setString(5, evenement.getImage());
        stm.setDouble(6, evenement.getTarif()); // Ajout du tarif
        stm.setInt(7, evenement.getPlacesDisponibles()); // Ajout des places disponibles
        stm.setInt(8, evenement.getId());
        stm.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}


    @Override
    public void supprimer(Evenement evenement) {
        // Supprimez l'événement de la base de données
        String req = "DELETE FROM evenements WHERE id = ?";
        try {
            PreparedStatement stm = conn.prepareStatement(req);
            stm.setInt(1, evenement.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

 
    
   
  
@Override
public ObservableList<Evenement> obtenirTousLesEvenements() {
    ObservableList<Evenement> result = FXCollections.observableArrayList();
    String req = "SELECT e.id, e.nom, e.dateDebut, e.description, e.tarif, e.places_disponibles, c.nom AS categorie_nom, e.lieu, e.image " +
                "FROM evenements e " +
                "LEFT JOIN categories c ON e.categorie_id = c.id";

    try {
        PreparedStatement stm = conn.prepareStatement(req);
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            Date dateDebut = rs.getDate("dateDebut");
            String description = rs.getString("description");
            double tarif = rs.getDouble("tarif"); // Nouvelle colonne tarif
            int placesDisponibles = rs.getInt("places_disponibles"); // Nouvelle colonne places_disponibles
            String categorieNom = rs.getString("categorie_nom"); // Nom de la catégorie
            String lieu = rs.getString("lieu");
            String image = rs.getString("image");

            Evenement event = new Evenement(id, nom, dateDebut, description, lieu, image, tarif, placesDisponibles,categorieNom);
            result.add(event);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return result;
}


    public ObservableList<Participant> obtenirTousLesParticipant() {
        
           ObservableList<Participant> result = FXCollections.observableArrayList();
    String req = "SELECT id, nom, prenom, email, telephone FROM participants";

    try {
        PreparedStatement stm = conn.prepareStatement(req);
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String email = rs.getString("email");
            String telephone = rs.getString("telephone");

            Participant participant = new Participant(id, nom, prenom, email, telephone);
            result.add(participant);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return result;
     }







}