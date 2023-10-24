
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.entity;

/**
 *
 * @author Dhouha
 */public class Categorie {
public enum Type {   //Déclaration de l'énumération Type
    SPORT(1, "SPORT"),
    CULTURE(2, "CULTURE"),
    LOISIRS(3, "LOISIRS"),
    AVENTURE(4, "AVENTURE"),
    BIENETRE(5, "BIENÊTRE"),
    AUTRE(6, "AUTRE");

    private final int id;
    private final String libelle;

    Type(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }
}
public static Type getCategorieByName(String name) {           // parcourt les val de l'énum Type et compare le nom de chaque élmt avec le nom en param
        for (Type type : Type.values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null; // Si la catégorie n'est pas trouvée
    }
public int getId() {
        // Ici, vous pouvez renvoyer l'ID en fonction de la catégorie actuelle
        // Vous pouvez utiliser une logique qui correspond à votre modèle de données.
        //  vous pouvez renvoyer l'ID du type en fonction du type de catégorie actuel
        return this.type.getId(); // Exemple basé sur le type actuel
    }
 // Ajoutez l'ID ici
    private Type type;
    private String description;
    public Categorie(  Type type, String description) {
      //constructeur
        this.type = type;
        this.description = description;
    }

 //méthodes d'accès

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
