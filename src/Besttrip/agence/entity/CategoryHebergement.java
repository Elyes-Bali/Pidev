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
public class CategoryHebergement {
    int id;
    String contenu;

    public CategoryHebergement() {
    }

    public CategoryHebergement(int id, String contenu) {
        this.id = id;
        this.contenu = contenu;
    }

    public CategoryHebergement(String contenu) {
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    
}
