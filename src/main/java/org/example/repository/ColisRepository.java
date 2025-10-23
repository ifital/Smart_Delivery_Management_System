package org.example.repository;

import org.example.model.Colis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ColisRepository extends JpaRepository<Colis, UUID> {
}
