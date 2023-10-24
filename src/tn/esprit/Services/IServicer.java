/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Services;

import java.util.List;


/**
 *
 * @author amadd
 */
public interface IServicer<reservation > {
     void addreservation(reservation r);
    void updatereservation(reservation r);
    void deletereservation(int reservationId);
    List<reservation> getAllreservation();
    
}
