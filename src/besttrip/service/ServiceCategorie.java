/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.service;

/**
 *
 * @author Dhouha
 */
 

import besttrip.entity. Categorie;
import java.util.List;

public interface ServiceCategorie {
    public void ajouterCategorie(Categorie.Type type, String description);
    public List<Categorie> affichageCategories();
    public void supprimerCategorie(Categorie.Type type);
    public Categorie getCategorie(Categorie.Type type);
    public boolean existe(Categorie.Type type);
}
