package org.example;

import org.example.model.Colis;
import org.example.model.Livreur;
import org.example.service.ColisService;
import org.example.service.LivreurService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        // 🔹 Charger le contexte Spring XML
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 🔹 Récupérer les services
        LivreurService livreurService = (LivreurService) context.getBean("livreurService");
        ColisService colisService = (ColisService) context.getBean("colisService");

        System.out.println("Nombre de colis : " + colisService.getAllColis().size());

        // 🔹 1. Créer un livreur
        Livreur livreur = new Livreur();
        livreur.setNom("Latifi");
        livreur.setPrenom("Abdelali");
        livreur.setTelephone("0612345678");
        livreur.setVehicule("Moto");

        livreur = livreurService.createLivreur(livreur);
        System.out.println("Livreur créé avec ID : " + livreur.getId());

        // 🔹 2. Créer un colis et l'assigner au livreur
        Colis colis = new Colis();
        colis.setDestinataire("Ahmed");
        colis.setAdresse("Casablanca");
        colis.setPoids(3.5);
        colis.setStatut("En préparation");

        // ⚠️ Attention : maintenant on passe un UUID, pas un Long
        UUID livreurId = livreur.getId();

        colis = colisService.createColis(colis, livreurId);
        System.out.println("Colis créé avec ID : " + colis.getId() + " pour le livreur : " + livreur.getNom());

        // 🔹 3. Lister les colis du livreur
        System.out.println("\nListe des colis du livreur " + livreur.getNom() + " :");
        colisService.getColisByLivreur(livreurId).forEach(c -> {
            System.out.println("Colis ID: " + c.getId() + ", Destinataire: " + c.getDestinataire() + ", Statut: " + c.getStatut());
        });

        // 🔹 4. Mettre à jour le statut du colis
        UUID colisId = colis.getId();
        colisService.updateStatut(colisId, "En transit");

        System.out.println("\nStatut du colis ID " + colisId + " mis à jour : " +
                colisService.getColisById(colisId).get().getStatut());
    }
}
