package org.example.service;

import org.example.model.Colis;
import org.example.model.Livreur;
import org.example.repository.ColisRepository;
import org.example.repository.LivreurRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ColisService {

    private final ColisRepository colisRepository;
    private final LivreurRepository livreurRepository;

    // 🔹 Constructeur pour injection XML
    public ColisService(ColisRepository colisRepository, LivreurRepository livreurRepository) {
        this.colisRepository = colisRepository;
        this.livreurRepository = livreurRepository;
    }

    // 🔹 Créer un colis et l’assigner à un livreur
    public Colis createColis(Colis colis, UUID livreurId) {
        Livreur livreur = livreurRepository.findById(livreurId)
                .orElseThrow(() -> new RuntimeException("Livreur non trouvé avec id " + livreurId));
        colis.setLivreur(livreur);
        return colisRepository.save(colis);
    }

    // 🔹 Récupérer tous les colis
    public List<Colis> getAllColis() {
        return colisRepository.findAll();
    }

    // 🔹 Récupérer un colis par son id
    public Optional<Colis> getColisById(UUID id) {
        return colisRepository.findById(id);
    }

    // 🔹 Mettre à jour le statut d’un colis
    public Colis updateStatut(UUID id, String nouveauStatut) {
        Colis colis = colisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colis non trouvé avec id " + id));
        colis.setStatut(nouveauStatut);
        return colisRepository.save(colis);
    }

    // 🔹 Récupérer tous les colis d’un livreur
    public List<Colis> getColisByLivreur(UUID livreurId) {
        Livreur livreur = livreurRepository.findById(livreurId)
                .orElseThrow(() -> new RuntimeException("Livreur non trouvé avec id " + livreurId));
        return livreur.getColis();
    }

    // 🔹 Mettre à jour toutes les informations d’un colis
    public Colis updateColis(UUID id, Colis updatedColis) {
        return colisRepository.findById(id)
                .map(colis -> {
                    colis.setDestinataire(updatedColis.getDestinataire());
                    colis.setAdresse(updatedColis.getAdresse());
                    colis.setPoids(updatedColis.getPoids());
                    colis.setStatut(updatedColis.getStatut());
                    return colisRepository.save(colis);
                })
                .orElseThrow(() -> new RuntimeException("Colis non trouvé avec id " + id));
    }

    // 🔹 Supprimer un colis par son id
    public void deleteColis(UUID id) {
        if (!colisRepository.existsById(id)) {
            throw new RuntimeException("Colis non trouvé avec id " + id);
        }
        colisRepository.deleteById(id);
    }
}
