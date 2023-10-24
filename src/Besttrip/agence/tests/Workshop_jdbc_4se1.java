/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.tests;

import Besttrip.agence.entity.Circuit;
import Besttrip.agence.services.ServiceCircuit;


/**
 *
 * @author balia
 */
public class Workshop_jdbc_4se1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Circuit p1 = new Circuit(1, 200, "A", "B","7h", "plane","aaaaaaa");
        
        Circuit p3 = p1;
        System.out.println(p1);
       
        
        ServiceCircuit sp = new ServiceCircuit();
       sp.ajouter(p1);
  
       
        System.out.println(sp.afficher());
        // TODO code application logic here
    }
    
}
