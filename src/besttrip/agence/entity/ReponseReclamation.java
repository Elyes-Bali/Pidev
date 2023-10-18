/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.agence.entity;

/**
 *
 * @author zouar
 */
public class ReponseReclamation {
    
     private int idRepRec;
    private String textRepRec;
    private String textRec;
    private int idU;
    private int idRec;

    public int getIdRepRec() {
        return idRepRec;
    }

    public String getTextRec() {
        return textRec;
    }

    public void setTextRec(String textRec) {
        this.textRec = textRec;
    }

    public void setIdRepRec(int idRepRec) {
        this.idRepRec = idRepRec;
    }

    public String getTextRepRec() {
        return textRepRec;
    }

    public void setTextRepRec(String textRepRec) {
        this.textRepRec = textRepRec;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public ReponseReclamation() {
    }

//    public ReponseReclamation(int idRepRec, String textRepRec, int idU, int idRec) {
//        this.idRepRec = idRepRec;
//        this.textRepRec = textRepRec;
//        this.idU = idU;
//        this.idRec = idRec;
//    }

    public ReponseReclamation(int idRepRec, String textRepRec, String textRec, int idU, int idRec) {
        this.idRepRec = idRepRec;
        this.textRepRec = textRepRec;
        this.textRec = textRec;
        this.idU = idU;
        this.idRec = idRec;
    }
    

    public ReponseReclamation(String textRepRec, int idU, int idRec) {
        this.textRepRec = textRepRec;
        this.idU = idU;
        this.idRec = idRec;
    }

    public ReponseReclamation(String textRepRec, String textRec, int idU, int idRec) {
        this.textRepRec = textRepRec;
        this.textRec = textRec;
        this.idU = idU;
        this.idRec = idRec;
    }
    
    
    @Override
    public String toString() {
        return "L'id de la réponse de réclamation :"+this.getIdRepRec() +" , l'id de reclameur : "+this.idU+ " , le texte est : "+ this.textRepRec ;
    }
}
