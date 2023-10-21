/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip2;

import besttrip.entity.Categorie;
import besttrip.entity.Event;
import besttrip.entity.Participants;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dhouha
 */


public class BestTrip2 {
    public static void main(String[] args) {
        Participants participant = new Participants(1, "Nom", "Prenom", "email@example.com", "123456789");
        Categorie categorie = Categorie.SPORT;
        List<Participants> participantsList = new ArrayList<>();
        participantsList.add(participant);

        Event event;
        event = new Event(1, "Titre de l'événement", "Description de l'événement", Date.valueOf("2023-10-10"), Date.valueOf("2023-10-15"), categorie, "Lieu de l'événement", "Chemin/URL de l'image", participantsList);
        
        // utiliser l'objet "event" comme on veut
        // afficher ses attributs par exple
        System.out.println("Titre de l'événement : " + event.getTitre());
        System.out.println("Description de l'événement : " + event.getDescription());
        System.out.println("Date de début : " + event.getDateDebut());
        System.out.println("Date de fin : " + event.getDateFin());
        System.out.println("Catégorie : " + event.getCategorie().getLibelle());
        System.out.println("Lieu : " + event.getLieu());
        System.out.println("Image : " + event.getImage());
        System.out.println("Participants : " + event.getParticipants());}
}
   




