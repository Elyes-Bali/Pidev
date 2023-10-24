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
public interface CrudReservation<Res> {
    public void ajouter(Res r);
    public void modifier(Res r);
    public void supprimer(int id) throws SQLException;
    public List<Reservation> Show();
}
