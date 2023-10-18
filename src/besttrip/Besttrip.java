/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip;

import Entity.Commentaire;
import Entity.Post;
import Service.CommentaireService;
import Service.PostService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.smartcardio.CommandAPDU;

/**
 *
 * @author abdennour
 */
public class Besttrip {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LocalDate d = LocalDate.of(2023, 8, 13);
         LocalDateTime d2 = LocalDateTime.now();
         Post p=new Post("abdennour","amdouni","image2",d);
           PostService pp=new PostService();
         //  pp.ajouterPost(p);
       Commentaire c = new Commentaire("comment","user");

        CommentaireService ps=new CommentaireService();
        //System.out.println( ps.afficheCommentaireByIdPost(21));
        ps.Clear(21);
        
        pp.ajouterPost(p);
    } 
}
