/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.service;

import besttrip.entity.Categorie;
import besttrip.entity.Evenement;
import java.util.List;

/**
 *
 * @author Dhouha
 */
   import java.util.List;
import javafx.collections.ObservableList;

public interface IEvent {
    void ajouter(Evenement evenement);
    List<Evenement> chercherCategorie(Categorie categorie);
    void modifier(Evenement evenement);
    void supprimer(Evenement evenement);
    ObservableList<Evenement> obtenirTousLesEvenements();
}


