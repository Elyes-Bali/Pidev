/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Besttrip.agence.test;

import Besttrip.agence.entity.User;
import Besttrip.agence.services.Services;

/**
 *
 * @author Maroua SANDI
 */
public class BestTrip1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
          User u1 =new User("maroua", "sandi", "admin", "maroua@yahoo.com",25369741, "523648","Mr");
         
  
        System.out.println("u1");
        
        
        Services su =new Services();
        su.ajouter(u1);
        System.out.println(su.afficher());
    
    }
    }
    

