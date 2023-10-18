/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.agence.services;

import java.util.List;

/**
 *
 * @author zouar
 */
public interface IServiceRepRec<T> {
     void save(T t);
     List<T> afficher();
}
