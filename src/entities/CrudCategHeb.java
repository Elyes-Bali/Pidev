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
public interface CrudCategHeb<Categ> {
    public void ajouter(Categ c);
    public void modifier(Categ c);
    public void supprimer(int id) throws SQLException;
    public List<CategoryHebergement> Show();
    public List<CategoryHebergement> Search(String t);
}
