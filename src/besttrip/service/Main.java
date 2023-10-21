/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttrip.service;

/**
 *
 * @author Dhouha
 */
import besttrip.entity.Categorie;
import besttrip.entity.Evenement;
import besttrip.service.ServiceEvent;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ServiceEvent serviceEvent = new ServiceEvent();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choisissez une option :");
            System.out.println("1. Insérer un nouvel événement");
            System.out.println("2. Supprimer un événement par ID");
            System.out.println("3. Modifier un événement par ID");
            System.out.println("4. Afficher tous les événements");
            System.out.println("5. Afficher les événements par catégorie");
            System.out.println("6. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Lire la nouvelle ligne après avoir lu un entier

            switch (choix) {
                case 1:
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Date (aaaa-mm-jj) : ");
                    String dateStr = scanner.nextLine();
                    Date dateDebut = Date.valueOf(dateStr);
                    System.out.print("Description : ");
                    String description = scanner.nextLine();
                    System.out.print("Catégorie (SPORT, CULTURE, LOISIRS, AVENTURE, BIENETRE, AUTRE) : ");
                    String categorieStr = scanner.nextLine();

                    Categorie.Type categorieType = Categorie.getCategorieByName(categorieStr);
                     int categorieId=0;
                    if (categorieType != null) {
                        // L'objet categorieType contient la catégorie correspondante
                        // Vous pouvez obtenir son ID avec categorieType.getId()
                         categorieId = categorieType.getId();
                        // Utilisez categorieId pour ajouter l'événement à la base de données
                    } else {
                        System.out.println("Catégorie non valide. Veuillez saisir une catégorie valide.");
                        // Vous pouvez ajouter une logique pour gérer les cas où la catégorie n'est pas trouvée.
                    }
                    Categorie.Type categorie = Categorie.Type.valueOf(categorieStr);
                    System.out.print("Lieu : ");
                    String lieu = scanner.nextLine();
                    System.out.print("Image : ");
                    String image = scanner.nextLine();

                    Evenement nouvelEvenement = new Evenement(nom, dateDebut, description, categorieId, lieu, image);
                    serviceEvent.ajouter(nouvelEvenement);
                    break;

                case 2:
                    System.out.print("ID de l'événement à supprimer : ");
                    int idSuppression = scanner.nextInt();
                    Evenement evenementASupprimer = new Evenement(idSuppression );
                    serviceEvent.supprimer(evenementASupprimer);
                    break;

                case 3:
                    System.out.print("ID de l'événement à modifier : ");
                    int idModification = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nouveau nom : ");
                    String nouveauNom = scanner.nextLine();
                    System.out.print("Nouvelle date (aaaa-mm-jj) : ");
                    String nouvelleDateStr = scanner.nextLine();
                    Date nouvelleDateDebut = Date.valueOf(nouvelleDateStr);
                    System.out.print("Nouvelle description : ");
                    String nouvelleDescription = scanner.nextLine();
                    System.out.print("Nouveau lieu : ");
                    String nouveauLieu = scanner.nextLine();
                    System.out.print("Nouvelle image : ");
                    String nouvelleImage = scanner.nextLine();

                    Evenement evenementAModifier = new Evenement(idModification, nouveauNom, nouvelleDateDebut, nouvelleDescription, nouveauLieu, nouvelleImage);
                    serviceEvent.modifier(evenementAModifier);
                    break;

                case 4:
                    List<Evenement> tousLesEvenements = serviceEvent.obtenirTousLesEvenements();
                    for (Evenement evenement : tousLesEvenements) {
                        System.out.println(evenement);
                    }
                    break;

                case 5:
                    System.out.print("Catégorie (SPORT, CULTURE, LOISIRS, AVENTURE, BIENETRE, AUTRE) : ");
                  /* String categorieFiltreStr = scanner.nextLine();
                    Categorie.Type categorieFiltre = Categorie.Type.valueOf(categorieFiltreStr);
                    List<Evenement> evenementsParCategorie = serviceEvent.chercherCategorie(categorieFiltre);
                    for (Evenement evenement : evenementsParCategorie) {
                        System.out.println(evenement);
                    }*/
                    break;

                case 6:
                    System.out.println("Au revoir !");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Option invalide. Réessayez.");
                    break;
            }
        }
    }
}

