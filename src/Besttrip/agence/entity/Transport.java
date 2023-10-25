/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.entity;

/**
 *
 * @author amadd
 */
    public class Transport  {
     private int id , prix, cap;
     private String type , dd , da ;

    

    public int getId() {
        return id;
    }

    public int getCap() {
        return cap;
    }

    public String getType() {
        return type;
    }

    public String getDd() {
        return dd;
    }

    public String getDa() {
        return da;
    }
    public int getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public void setDa(String da) {
        this.da = da;
    }
     public void setPrix(int prix) {
        this.prix = prix;
    }

    public Transport(int cap, String type, String dd, String da, int prix) {
        this.cap = cap;
        this.type = type;
        this.dd = dd;
        this.da = da;
        this.prix = prix;
    }
    

    public Transport(int id, int cap, String type, String dd, String da, int prix) {
        this.id = id;
        this.cap = cap;
        this.type = type;
        this.dd = dd;
        this.da = da;
        this.prix = prix;

    }

    public Transport(String type) {
        this.type = type;
    }
    
    public Transport() {
       
    }

    public Transport(String type, String dd) {
        this.type = type;
        this.dd = dd;
    }

    /*@Override
    public String toString() {
        return "transport id:"+this.id + " capacité "+ this.cap + " type "+ this.type+ " Date de depart "+ this.dd+ " date d'arrivée "+ this.da  ;
    }*/
    @Override
    public String toString() {
        return getType()  ;
    }
    }