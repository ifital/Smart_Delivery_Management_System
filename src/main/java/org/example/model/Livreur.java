package org.example.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "livreurs")
public class Livreur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;
    private String nom;
    private String prenom;
    private String vehicule;
    private String telephone;

    @OneToMany(mappedBy = "livreur")
    private List<Colis> colis;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVehicule() {
        return vehicule;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Colis> getColis() {
        return colis;
    }

    public void setColis(List<Colis> colis) {
        this.colis = colis;
    }
}
