package org.example.service;

import org.example.model.Livreur;
import org.example.repository.LivreurRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LivreurService {

    private LivreurRepository livreurRepository;

    public LivreurService(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;
    }

    public Livreur createLivreur(Livreur livreur) {
        return livreurRepository.save(livreur);
    }

    public List<Livreur> getAllLivreurs() {
        return livreurRepository.findAll();
    }

    public Optional<Livreur> getLivreurById(UUID id) {
        return livreurRepository.findById(id);
    }

    public Livreur updateLivreur(UUID id, Livreur updatedLivreur) {
        return livreurRepository.findById(id)
                .map(livreur -> {
                    livreur.setNom(updatedLivreur.getNom());
                    livreur.setPrenom(updatedLivreur.getPrenom());
                    livreur.setTelephone(updatedLivreur.getTelephone());
                    livreur.setVehicule(updatedLivreur.getVehicule());
                    return livreurRepository.save(livreur);
                })
                .orElseThrow(() -> new RuntimeException("Livreur non trouvé"));
    }

    public void deleteLivreur(UUID id) {
        if (!livreurRepository.existsById(id)) {
            throw new RuntimeException("Livreur non trouvé");
        }
        livreurRepository.deleteById(id);
    }
}
