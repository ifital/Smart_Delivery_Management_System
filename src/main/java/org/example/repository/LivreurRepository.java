package org.example.repository;

import org.example.model.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivreurRepository extends JpaRepository<Livreur, UUID> {
}
