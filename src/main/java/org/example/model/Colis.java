package org.example.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "colis")
public class Colis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;
    private String destinataire;
    private String adresse;
    private double poids;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "livreur_id")
    private Livreur livreur;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }
}
