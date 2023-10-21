/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package besttrip.service;

import besttrip.entity.Evenement;
import besttrip.entity.Participant;
import besttrip.entity.Reservation;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Dhouha
 */
public class TestReservation {
        public static void main(String[] args) {

    ReservationService reservationService = new ReservationServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Options :");
            System.out.println("1. Ajouter une nouvelle réservation");
            System.out.println("2. Modifier une réservation");
            System.out.println("3. Supprimer une réservation");
            System.out.println("4. Obtenir une réservation par ID");
            System.out.println("5. Obtenir toutes les réservations");
            System.out.println("6. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Lire la nouvelle ligne après avoir lu un entier

            switch (choix) {
             case 1:
    System.out.print("Nouveau tarif : ");
    double tarif = scanner.nextDouble();
    System.out.print("Nouvelles places disponibles : ");
    int placesDisponibles = scanner.nextInt();
    System.out.print("Nouvelles places réservées : ");
    int placesReservees = scanner.nextInt();
    
    // Demander à l'utilisateur l'ID de l'événement associé à la réservation
    System.out.print("ID de l'événement associé : ");
    int evenementId = scanner.nextInt();
    Evenement evenement =  reservationService.obtenirEvenementParId(evenementId);

    // Demander à l'utilisateur l'ID du participant associé à la réservation
    System.out.print("ID du participant associé : ");
    int participantId = scanner.nextInt();
    Participant participant = reservationService.obtenirParticipantParId(participantId);

    scanner.nextLine();  // Nettoyer la ligne après avoir lu l'entier

    System.out.print("Date et heure de réservation (aaaa-mm-jj hh:mm:ss) : ");
    String dateHeureStr = scanner.nextLine();
    Timestamp dateHeureReservation = Timestamp.valueOf(dateHeureStr);

    // Créer un objet de réservation
    Reservation nouvelleReservation = new Reservation();
    nouvelleReservation.setTarif(tarif);
    nouvelleReservation.setPlacesDisponibles(placesDisponibles);
    nouvelleReservation.setPlacesReservees(placesReservees);
    nouvelleReservation.setEvenement(evenement);
    nouvelleReservation.setParticipant(participant);
    nouvelleReservation.setDateHeureReservation(dateHeureReservation);

    // Appeler la méthode pour ajouter la réservation
    reservationService.ajouterReservation(nouvelleReservation);

    System.out.println("Nouvelle réservation ajoutée avec succès.");
    break;


               case 2:
    System.out.print("ID de la réservation à modifier : ");
    int idModification = scanner.nextInt();
    scanner.nextLine();

    Reservation reservationAModifier = reservationService.obtenirReservationParId(idModification);
    
    if (reservationAModifier != null) {
        System.out.print("Nouveau tarif : ");
        Double nouveauTarif = scanner.nextDouble();
        reservationAModifier.setTarif(nouveauTarif);

        System.out.println("Nouvelles places disponibles : ");
        int nouvellesPlacesDisponibles = scanner.nextInt();
        reservationAModifier.setPlacesDisponibles(nouvellesPlacesDisponibles);

        System.out.println("Nouvelles places réservées : ");
        int nouvellesPlacesReservees = scanner.nextInt();
        reservationAModifier.setPlacesReservees(nouvellesPlacesReservees);

 

       
                 

        // Maintenant, vous pouvez appeler la méthode de modification de la réservation
        reservationService.modifierReservation(reservationAModifier);
        System.out.println("Réservation modifiée avec succès.");
    } else {
        System.out.println("Réservation non trouvée.");
    }
    break;


                case 3:
                 System.out.print("ID de l'événement à supprimer : ");
                    int idSuppression = scanner.nextInt();
                    Reservation  res = new Reservation(idSuppression );
                    reservationService.supprimerReservation(res.getId());  
                                  
                    break;

                case 4:
                    // Code pour obtenir une réservation par ID
                    break;

                case 5:
                List<Reservation> tousLesReservation= reservationService.obtenirToutesReservations();
                    for (Reservation reservation : tousLesReservation) {
                        System.out.println(reservation);
                    }                    break;

                case 6:
                    System.out.println("Terminé.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Option invalide. Réessayez.");
                    break;
            }
        }
        }
    }

