/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Services;

import java.util.List;
import tn.esprit.entity.Transport;
import tn.esprit.entity.reservation;
/**
 *
 * @author amadd
 */
public interface IServices<transport> {
    void addTransport(Transport transport);
    void updateTransport(Transport transport);
    void deleteTransport(int id);
    List<Transport> getAllTransports();
}

/**
 *
 * @author amadd
 */
