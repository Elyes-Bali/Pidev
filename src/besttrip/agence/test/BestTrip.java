/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.agence.test;

import besttrip.agence.entity.Reclamation;
import besttrip.agence.services.Services;

/**
 *
 * @author zouar
 */
public class BestTrip {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Reclamation r1= new Reclamation("bbb","tyc", 1003);
        System.out.println(r1);
        Services rs=new Services();
        rs.ajouter(r1);
        
    }
    
}
