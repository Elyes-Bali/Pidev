/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.services;

import java.util.List;

/**
 *
 * @author Maroua SANDI
 
 */
public interface IServices<U> {
    void ajouter (U u);
    void modifier (U u);
    void supprimer(U u);
    List<U> afficher();
    
}
