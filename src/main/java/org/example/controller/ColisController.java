package org.example.controller;

import org.example.model.Colis;
import org.example.service.ColisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/colis")
public class ColisController {

    private ColisService colisService;

    // 🔹 Setter pour injection XML
    public void setColisService(ColisService colisService) {
        this.colisService = colisService;
    }

    @PostMapping("/{livreurId}")
    public Colis createColis(@RequestBody Colis colis, @PathVariable("livreurId") UUID livreurId) {
        return colisService.createColis(colis, livreurId);
    }

    @GetMapping
    public List<Colis> getAllColis() {
        return colisService.getAllColis();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colis> getColisById(@PathVariable("id") UUID id) {
        return colisService.getColisById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/statut/{id}")
    public Colis updateStatut(@PathVariable("id") UUID id, @RequestParam String statut) {
        return colisService.updateStatut(id, statut);
    }

    @PutMapping("/{id}")
    public Colis updateColis(@PathVariable("id") UUID id, @RequestBody Colis updatedColis) {
        return colisService.updateColis(id, updatedColis);
    }

    @GetMapping("/livreur/{livreurId}")
    public List<Colis> getColisByLivreur(@PathVariable("livreurId") UUID livreurId) {
        return colisService.getColisByLivreur(livreurId);
    }

    @DeleteMapping("/{id}")
    public void deleteColis(@PathVariable("id") UUID id) {
        colisService.deleteColis(id);
    }
}
