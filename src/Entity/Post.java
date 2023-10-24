/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mahmo
 */
public class Post {
       
    private int id;
    private String titre;
    private String sujet;
    private String image;
    private LocalDate date;
    private List<Commentaire> commentaires = new ArrayList<>();

    public Post() {
    }

    public Post(String titre, String sujet, String image, LocalDate date) {
        this.titre = titre;
        this.sujet = sujet;
        this.image = image;
        this.date = date;
    }

    public Post(int id, String titre, String sujet, String image, LocalDate date) {
        this.id = id;
        this.titre = titre;
        this.sujet = sujet;
        this.image = image;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

   /* public List<Commentaire> getCommentaires() {
        return commentaires;
    }*/
    
    
    

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.titre);
        hash = 61 * hash + Objects.hashCode(this.sujet);
        hash = 61 * hash + Objects.hashCode(this.image);
        hash = 61 * hash + Objects.hashCode(this.date);
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
        final Post other = (Post) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.sujet, other.sujet)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", titre=" + titre + ", sujet=" + sujet + ", image=" + image + ", date=" + date + '}';
    }

    public List<Commentaire> getCommentaires() {
          return commentaires;    }

}
