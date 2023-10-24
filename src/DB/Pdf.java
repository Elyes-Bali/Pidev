/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Entity.Post;
import Service.PostService;
import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author abdennour
 */
public class Pdf {
        public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        PostService m=new PostService();
        List<Post> list=m.afficherPost();    
        document.add(new Paragraph("La liste des Report :"));
        document.add(new Paragraph("     "));
         for(Post u:list)
        {
            
        document.add(new Paragraph("Le Titre :"+u.getTitre()));
        document.add(new Paragraph("Le sujet est :"+u.getSujet()));
        document.add(new Paragraph("image :"+u.getImage()));
        document.add(new Paragraph("cette tableau est depuis :"+u.getDate()));
        
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
}
