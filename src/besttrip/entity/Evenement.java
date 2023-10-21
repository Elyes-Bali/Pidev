/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.entity;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Dhouha
 */
 
public class Evenement {
    private int id;
    private String nom;
    private Date dateDebut;
    private String description;
    private int categorieId; // L'ID de la catégorie
    private String lieu;
    private String image;
       private double tarif;
    private int placesDisponibles;
private String categorieNom; 

    public String getCategorieNom() {
        return categorieNom;
    }

    public void setCategorieNom(String categorieNom) {
        this.categorieNom = categorieNom;
    }

    public Evenement(int id, String nom, Date dateDebut, String description,  String lieu, String image, double tarif, int placesDisponibles, String categorieNom) {
        this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.description = description;
     
        this.lieu = lieu;
        this.image = image;
        this.tarif = tarif;
        this.placesDisponibles = placesDisponibles;
        this.categorieNom = categorieNom;
    }

    public Evenement() {
    }


    public Evenement(int id, String nom, Date dateDebut, String description, int categorieId, String lieu, String image, double tarif, int placesDisponibles) {
        this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.description = description;
        this.categorieId = categorieId;
        this.lieu = lieu;
        this.image = image;
        this.tarif = tarif;
        this.placesDisponibles = placesDisponibles;
    }

 
    public Evenement(String nom, Date dateDebut, String description, int categorieId, String lieu, String image ,double tarif, int placesDisponibles) {
        
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.description = description;
      
        this.lieu = lieu;
        this.image = image;
        this.tarif = tarif;
        this.placesDisponibles = placesDisponibles;
    }
        public Evenement(int id ) {
        this.id = id;
         
    }

    public Evenement(double tarif, int placesDisponibles) {
        this.tarif = tarif;
        this.placesDisponibles = placesDisponibles;
    }

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public int getPlacesDisponibles() {
        return placesDisponibles;
    }

    public void setPlacesDisponibles(int placesDisponibles) {
        this.placesDisponibles = placesDisponibles;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", dateDebut=" + dateDebut + ", description=" + description + ", categorieId=" + categorieId + ", lieu=" + lieu + ", image=" + image + ", tarif=" + tarif + ", placesDisponibles=" + placesDisponibles + ", categorieNom=" + categorieNom + '}';
    }

   

 
}
