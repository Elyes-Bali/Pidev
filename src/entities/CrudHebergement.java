/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ons
 */
public interface CrudHebergement<Heb> {
    
    public void ajouter(Heb h);
    public void modifier(Heb h);
    public void supprimer(int id) throws SQLException;
    public List<Hebergement> Show();
    public List<Hebergement> Search(String t);
}
