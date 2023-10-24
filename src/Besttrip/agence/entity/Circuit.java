/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.entity;

/**
 *
 * @author balia
 */
public class Circuit {

  

     private int id , prix; 
   
    private String depart , arrive ,temps, categorie,description,pays;
    
     public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

   
 public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
 public Circuit(int id, int prix, String depart, String arrive, String categorie,String temps,String description) {
        this.id = id;
        this.prix = prix;
        this.depart = depart;
        this.arrive = arrive;
        this.categorie = categorie;        
        this.temps = temps;
        this.description = description;

    }
  

    public Circuit(String depart, String arrive, String categorie,String temps , int prix, String description) {
        this.depart = depart;
        this.arrive = arrive;        
        this.categorie = categorie;
        this.prix = prix;
        this.temps = temps;
        this.description = description;
    }
  public Circuit(int id, int prix, String depart, String arrive, String temps, String categorie, String description, String pays) {
        this.id = id;
        this.prix = prix;
        this.depart = depart;
        this.arrive = arrive;
        this.temps = temps;
        this.categorie = categorie;
        this.description = description;
        this.pays = pays;
    }
  public Circuit(int prix, String depart, String arrive, String temps, String categorie, String description, String pays) {
        this.prix = prix;
        this.depart = depart;
        this.arrive = arrive;
        this.temps = temps;
        this.categorie = categorie;
        this.description = description;
        this.pays = pays;
    }
    public Circuit() {
    }

    @Override
    public String toString() {
        return "Trip id:"+this.id +" , le point de depart est : "+this.depart+ " , le point d'arrive est : "+ this.arrive+ ", le prix est : "+this.prix+"Dt "+", Description: "+this.description  ;
    }
    
}
