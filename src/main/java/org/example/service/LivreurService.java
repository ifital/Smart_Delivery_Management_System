package org.example.service;

import org.example.model.Livreur;
import org.example.repository.LivreurRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LivreurService {

    private final LivreurRepository livreurRepository;

    // 🔹 Constructeur pour injection XML
    public LivreurService(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;
    }

    // 🔹 Créer un livreur
    public Livreur createLivreur(Livreur livreur) {
        return livreurRepository.save(livreur);
    }

    // 🔹 Récupérer tous les livreurs
    public List<Livreur> getAllLivreurs() {
        return livreurRepository.findAll();
    }

    // 🔹 Récupérer un livreur par son id
    public Optional<Livreur> getLivreurById(UUID id) {
        return livreurRepository.findById(id);
    }

    // 🔹 Mettre à jour toutes les informations d’un livreur
    public Livreur updateLivreur(UUID id, Livreur updatedLivreur) {
        return livreurRepository.findById(id)
                .map(livreur -> {
                    livreur.setNom(updatedLivreur.getNom());
                    livreur.setPrenom(updatedLivreur.getPrenom());
                    livreur.setTelephone(updatedLivreur.getTelephone());
                    livreur.setVehicule(updatedLivreur.getVehicule());
                    return livreurRepository.save(livreur);
                })
                .orElseThrow(() -> new RuntimeException("Livreur non trouvé avec id " + id));
    }

    // 🔹 Supprimer un livreur par son id
    public void deleteLivreur(UUID id) {
        if (!livreurRepository.existsById(id)) {
            throw new RuntimeException("Livreur non trouvé avec id " + id);
        }
        livreurRepository.deleteById(id);
    }
}
