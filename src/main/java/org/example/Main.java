package org.example;

import org.example.model.Colis;
import org.example.model.Livreur;
import org.example.service.ColisService;
import org.example.service.LivreurService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {

        // 🔹 Charger le contexte Spring XML
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 🔹 Récupérer les services
        LivreurService livreurService = (LivreurService) context.getBean("livreurService");
        ColisService colisService = (ColisService) context.getBean("colisService");

        System.out.println("Nombre de colis : " + colisService.getAllColis().size());

        // 🔹 1. Créer plusieurs livreurs
        Livreur livreur1 = new Livreur();
        livreur1.setNom("Latifi");
        livreur1.setPrenom("Abdelali");
        livreur1.setTelephone("0612345678");
        livreur1.setVehicule("Moto");
        livreur1 = livreurService.createLivreur(livreur1);

        Livreur livreur2 = new Livreur();
        livreur2.setNom("Benzouaoui");
        livreur2.setPrenom("Sara");
        livreur2.setTelephone("0623456789");
        livreur2.setVehicule("Voiture");
        livreur2 = livreurService.createLivreur(livreur2);

        System.out.println("Livreurs créés avec IDs : " + livreur1.getId() + ", " + livreur2.getId());

        // 🔹 2. Créer plusieurs colis et les assigner aux livreurs
        Colis colis1 = new Colis();
        colis1.setDestinataire("Ahmed");
        colis1.setAdresse("Casablanca");
        colis1.setPoids(3.5);
        colis1.setStatut("En préparation");
        colis1 = colisService.createColis(colis1, livreur1.getId());

        Colis colis2 = new Colis();
        colis2.setDestinataire("Fatima");
        colis2.setAdresse("Rabat");
        colis2.setPoids(2.0);
        colis2.setStatut("En préparation");
        colis2 = colisService.createColis(colis2, livreur1.getId());

        Colis colis3 = new Colis();
        colis3.setDestinataire("Mohamed");
        colis3.setAdresse("Marrakech");
        colis3.setPoids(1.5);
        colis3.setStatut("En préparation");
        colis3 = colisService.createColis(colis3, livreur2.getId());

        System.out.println("Colis créés avec IDs : " + colis1.getId() + ", " + colis2.getId() + ", " + colis3.getId());

        // 🔹 3. Lister les colis de chaque livreur
        System.out.println("\nListe des colis du livreur " + livreur1.getNom() + " :");
        colisService.getColisByLivreur(livreur1.getId()).forEach(c ->
                System.out.println("Colis ID: " + c.getId() + ", Destinataire: " + c.getDestinataire() + ", Statut: " + c.getStatut())
        );

        System.out.println("\nListe des colis du livreur " + livreur2.getNom() + " :");
        colisService.getColisByLivreur(livreur2.getId()).forEach(c ->
                System.out.println("Colis ID: " + c.getId() + ", Destinataire: " + c.getDestinataire() + ", Statut: " + c.getStatut())
        );

        // 🔹 4. Mettre à jour le statut de certains colis
        colisService.updateStatut(colis1.getId(), "En transit");
        colisService.updateStatut(colis3.getId(), "Livré");

        System.out.println("\nStatut mis à jour :");
        System.out.println("Colis ID " + colis1.getId() + " -> " + colisService.getColisById(colis1.getId()).get().getStatut());
        System.out.println("Colis ID " + colis3.getId() + " -> " + colisService.getColisById(colis3.getId()).get().getStatut());
    }
}
