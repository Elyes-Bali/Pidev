package Besttrip.agence.entity;

import java.util.Date;
public class reservationTransport {
    private int id;
    private int transport_id;
    private int client_id;
    private Date debutReservation;
    public reservationTransport(int id, int transport_id, int client_id) {
        this.id = id;
        this.transport_id = transport_id;
        this.client_id = client_id;
    }
    
     public reservationTransport(int transport_id, int client_id) {
        this.transport_id = transport_id;
        this.client_id = client_id;
    }
     public reservationTransport(int id, int transport_id, int client_id, Date debutReservation) {
        this.id = id;
        this.transport_id = transport_id;
        this.client_id = client_id;
        this.debutReservation = debutReservation;
    }

    public reservationTransport(int transport_id, int client_id, Date debutReservation) {
        this.transport_id = transport_id;
        this.client_id = client_id;
        this.debutReservation = debutReservation;
    }
  


    // Constructor without any attributes (assuming they will be set later)
    public reservationTransport() {
        // You can choose to initialize attributes with default values here
    }


    // Getters and setters for the attributes

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransportId() {
        return transport_id;
    }

    public void setTransportId(int transportId) {
        this.transport_id = transportId;
    }

    public int getClientId() {
        return client_id;
    }

    public void setClientId(int client_id) {
        this.client_id = client_id;
    }
    public Date getDebutReservation() {
        return debutReservation;
    }

    public void setDebutReservation(Date debutReservation) {
        this.debutReservation = debutReservation;
    }

    public reservationTransport(Date debutReservation) {
        this.debutReservation = debutReservation;
    }
    
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", transportId=" + transport_id +
                ", clientId=" + client_id +
                '}';
    }
    
}
