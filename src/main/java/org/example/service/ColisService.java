package org.example.service;

import org.example.model.Colis;
import org.example.model.Livreur;
import org.example.repository.ColisRepository;
import org.example.repository.LivreurRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ColisService {

    private ColisRepository colisRepository;
    private LivreurRepository livreurRepository;

    // 🔹 Constructeur pour injection XML
    public ColisService(ColisRepository colisRepository, LivreurRepository livreurRepository) {
        this.colisRepository = colisRepository;
        this.livreurRepository = livreurRepository;
    }

    public Colis createColis(Colis colis, UUID livreurId) {
        Livreur livreur = livreurRepository.findById(livreurId)
                .orElseThrow(() -> new RuntimeException("Livreur non trouvé"));
        colis.setLivreur(livreur);
        return colisRepository.save(colis);
    }

    public List<Colis> getAllColis() {
        return colisRepository.findAll();
    }

    public Optional<Colis> getColisById(UUID id) {
        return colisRepository.findById(id);
    }

    public Colis updateStatut(UUID id, String nouveauStatut) {
        Colis colis = colisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colis non trouvé"));
        colis.setStatut(nouveauStatut);
        return colisRepository.save(colis);
    }

    public List<Colis> getColisByLivreur(UUID livreurId) {
        Livreur livreur = livreurRepository.findById(livreurId)
                .orElseThrow(() -> new RuntimeException("Livreur non trouvé"));
        return livreur.getColis();
    }

    public Colis updateColis(UUID id, Colis updatedColis) {
        return colisRepository.findById(id)
                .map(colis -> {
                    colis.setDestinataire(updatedColis.getDestinataire());
                    colis.setAdresse(updatedColis.getAdresse());
                    colis.setPoids(updatedColis.getPoids());
                    colis.setStatut(updatedColis.getStatut());
                    return colisRepository.save(colis);
                })
                .orElseThrow(() -> new RuntimeException("Colis non trouvé"));
    }

    public void deleteColis(UUID id) {
        if (!colisRepository.existsById(id)) {
            throw new RuntimeException("Colis non trouvé");
        }
        colisRepository.deleteById(id);
    }
}
