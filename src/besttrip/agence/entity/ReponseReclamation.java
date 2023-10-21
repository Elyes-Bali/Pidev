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
    
    
    private int idU;
    private String Prenom;
    private String intitule;
    private String textRepRec;
    private int idRec;
    private int idRepRec;

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getTextRepRec() {
        return textRepRec;
    }

    public void setTextRepRec(String textRepRec) {
        this.textRepRec = textRepRec;
    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public int getIdRepRec() {
        return idRepRec;
    }

    public void setIdRepRec(int idRepRec) {
        this.idRepRec = idRepRec;
    }

    

    public ReponseReclamation() {
    }

//    public ReponseReclamation(int idRepRec, String textRepRec, int idU, int idRec) {
//        this.idRepRec = idRepRec;
//        this.textRepRec = textRepRec;
//        this.idU = idU;
//        this.idRec = idRec;
//    }

//    

    public ReponseReclamation(int idU, String Prenom, String intitule, String textRepRec, int idRec, int idRepRec) {
        this.idU = idU;
        this.Prenom = Prenom;
        this.intitule = intitule;
        this.textRepRec = textRepRec;
        this.idRec = idRec;
        this.idRepRec = idRepRec;
    }
    
    

    public ReponseReclamation(String textRepRec, int idU, int idRec) {
        this.textRepRec = textRepRec;
        this.idU = idU;
        this.idRec = idRec;
    }

//    public ReponseReclamation(String textRepRec, String textRec, int idU, int idRec) {
//        this.textRepRec = textRepRec;
//        this.textRec = textRec;
//        this.idU = idU;
//        this.idRec = idRec;
//    }

    public ReponseReclamation(int idU,String Prenom, String intitule, String textRepRec, int idRec) {
        this.idU = idU;
        this.Prenom = Prenom;
        this.intitule = intitule;
        this.textRepRec = textRepRec;
        this.idRec = idRec;
    }
    
    
    
    @Override
    public String toString() {
        return "ID Client :"+ this.getIdU()+"|| Prénom : "+this.getPrenom()+"|| Intitulé Réclamation : "+this.getIntitule()+ "|| Réponse : "+this.getTextRepRec()+"|| Id Réclamation : "+this.getIdRec();
    }
}
