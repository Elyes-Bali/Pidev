/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author mahmo
 */
public class Commentaire {
    
    private int id_coment;
    private String contenu;
    private String nom; 
    private LocalDateTime dateNow=LocalDateTime.now();
    private int post_id;
    private Post post;
    public Commentaire() {
    }

    public Commentaire(int id_coment, String contenu, String nom, int post_id) {
        this.id_coment = id_coment;
        this.contenu = contenu;
        this.nom = nom;
        this.post_id = post_id;
    }

    public Commentaire(String contenu, String nom, int post_id) {
        this.contenu = contenu;
        this.nom = nom;
        this.post_id = post_id;
    }

    public Commentaire(int id_coment, String contenu, String nom) {
        this.id_coment = id_coment;
        this.contenu = contenu;
        this.nom = nom;
    }

    public Commentaire(String contenu, String nom) {
        this.contenu = contenu;
        this.nom = nom;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }


    public int getId_coment() {
        return id_coment;
    }

    public void setId_coment(int id_coment) {
        this.id_coment = id_coment;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDateTime getDateNow() {
        return LocalDateTime.now();
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id_coment;
        hash = 67 * hash + Objects.hashCode(this.contenu);
        hash = 67 * hash + Objects.hashCode(this.nom);
        hash = 67 * hash + Objects.hashCode(this.dateNow);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commentaire other = (Commentaire) obj;
        if (this.id_coment != other.id_coment) {
            return false;
        }
        if (!Objects.equals(this.contenu, other.contenu)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.dateNow, other.dateNow)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id_coment=" + id_coment + ", contenu=" + contenu + ", nom=" + nom + ", dateNow=" + dateNow + '}';
    }

}
