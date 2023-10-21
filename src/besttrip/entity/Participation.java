/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.entity;

/**
 *
 * @author Dhouha
 */
public class Participation {
    private int id;
    private Evenement evenement;
    private Participant participant;
    private String statut;

    public Participation(int id, Evenement evenement, Participant participant, String statut) {
        this.id = id;
        this.evenement = evenement;
        this.participant = participant;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "id=" + id +
                ", evenement=" + evenement +
                ", participant=" + participant +
                ", statut='" + statut + '\'' +
                '}';
    }
}

