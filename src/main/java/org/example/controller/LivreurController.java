package org.example.controller;

import org.example.model.Livreur;
import org.example.service.LivreurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/livreurs")
public class LivreurController {

    private LivreurService livreurService;

    // 🔹 Setter pour injection XML
    public void setLivreurService(LivreurService livreurService) {
        this.livreurService = livreurService;
    }

    @PostMapping
    public Livreur createLivreur(@RequestBody Livreur livreur) {
        return livreurService.createLivreur(livreur);
    }

    @GetMapping
    public List<Livreur> getAllLivreurs() {
        return livreurService.getAllLivreurs();
    }

    @GetMapping("/{id}")
    public Livreur getLivreurById(@PathVariable("id") UUID id) {
        return livreurService.getLivreurById(id)
                .orElseThrow(() -> new RuntimeException("Livreur non trouvé"));
    }


    @PutMapping("/{id}")
    public Livreur updateLivreur(@PathVariable("id") UUID id, @RequestBody Livreur updatedLivreur) {
        return livreurService.updateLivreur(id, updatedLivreur);
    }

    @DeleteMapping("/{id}")
    public void deleteLivreur(@PathVariable("id") UUID id) {
        livreurService.deleteLivreur(id);
    }
}
