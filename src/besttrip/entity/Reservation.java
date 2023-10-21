/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.entity;

import java.util.Date;

/**
 *
 * @author Dhouha
 */
public class Reservation {
    private int id;
 
    private int placesReservees;
    private Evenement evenement;
    private Participant participant; // Ajout de la référence au Participant
    private Date dateHeureReservation; // Nouvelle colonne pour la date et l'heure de la réservation
private boolean validate; 

    public Reservation(int placesReservees, Evenement evenement, Participant participant, Date dateHeureReservation, boolean validate) {
        this.placesReservees = placesReservees;
        this.evenement = evenement;
        this.participant = participant;
        this.dateHeureReservation = dateHeureReservation;
        this.validate = validate;
    }

    public Reservation(int id, int placesReservees, Evenement evenement, Participant participant, Date dateHeureReservation, boolean validate) {
        this.id = id;
        this.placesReservees = placesReservees;
        this.evenement = evenement;
        this.participant = participant;
        this.dateHeureReservation = dateHeureReservation;
        this.validate = validate;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public Date getDateHeureReservation() {
        return dateHeureReservation;
    }

    public void setDateHeureReservation(Date dateHeureReservation) {
        this.dateHeureReservation = dateHeureReservation;
    }

    public Reservation(int id) {
        this.id = id;
    }

    public Reservation() {
    }

    public Reservation(int id, int placesReservees, Evenement evenement, Participant participant, Date dateHeureReservation) {
        this.id = id;
        
        this.placesReservees = placesReservees;
        this.evenement = evenement;
        this.participant = participant;
        this.dateHeureReservation = dateHeureReservation;
    }

    public Reservation(  int placesReservees, Evenement evenement, Participant participant, Date dateHeureReservation) {
    
        this.placesReservees = placesReservees;
        this.evenement = evenement;
        this.participant = participant;
        this.dateHeureReservation = dateHeureReservation;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 

    public int getPlacesReservees() {
        return placesReservees;
    }

    public void setPlacesReservees(int placesReservees) {
        this.placesReservees = placesReservees;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                
                ", placesReservees=" + placesReservees +
                ", evenement=" + evenement +
                ", participant=" + participant +
                '}';
    }
}
