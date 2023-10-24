/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.entity;

/**
 *
 * @author Ons
 */
public class Hebergement {
    int id;
    int capacite;
    float prix;
    String adresse;
    String type;
    String description;
    int categorie;

    public Hebergement() {
    }

    public Hebergement(int id, int capacite, float prix, String adresse, String type, String description, int categorie) {
        this.id = id;
        this.capacite = capacite;
        this.prix = prix;
        this.adresse = adresse;
        this.type = type;
        this.description = description;
        this.categorie = categorie;
    }

    public Hebergement(int capacite, float prix, String adresse, String type, String description, int categorie) {
        this.capacite = capacite;
        this.prix = prix;
        this.adresse = adresse;
        this.type = type;
        this.description = description;
        this.categorie = categorie;
    }

    public Hebergement(int capacite, float prix, String adresse, String type, String description) {
        this.capacite = capacite;
        this.prix = prix;
        this.adresse = adresse;
        this.type = type;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }
    
    
}
