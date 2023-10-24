/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Ons
 */
public class Reservation {
    int id;
    Date date;
    int id_heb;

    public Reservation() {
    }

    public Reservation(int id, Date date, int id_heb) {
        this.id = id;
        this.date = date;
        this.id_heb = id_heb;
    }

    public Reservation(Date date) {
        this.date = date;
    }

    public Reservation(Date date, int id_heb) {
        this.date = date;
        this.id_heb = id_heb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId_heb() {
        return id_heb;
    }

    public void setId_heb(int id_heb) {
        this.id_heb = id_heb;
    }
    
    
    
}
