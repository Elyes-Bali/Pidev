/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.agence.services;

import Besttrip.agence.entity.Evenement;
import Besttrip.agence.entity.Participant;
import Besttrip.agence.entity.Reservation;
 
/**
 *
 * @author Dhouha
 * @param <T>
 */
import java.util.List;
import javafx.collections.ObservableList;

public interface ReservationService {
    void ajouterReservation(Reservation reservation,int id);
    void modifierReservation(Reservation reservation);
    void supprimerReservation(int id);
    Reservation obtenirReservationParId(int id);
    Evenement obtenirEvenementParId(int id);
    Participant obtenirParticipantParId(int id) ;
    ObservableList<Reservation> obtenirToutesReservations();
    int foundParticpant(String phoneNumber);
}