/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.util.List;


/**
 *
 * @author balia
 */
public interface IService<T> {
     void ajouter(T t);
    void supprimer(T t);
    void modifier(T t);
    List<T> afficher();
    
}
