package com.example.gestionflotte.repository;

import com.example.gestionflotte.modele.Kilometrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KilometrageRepository extends JpaRepository<Kilometrage, Long> {
    @Query(value = "SELECT k FROM kilometrage k WHERE k.avion.id = ?1")
    public List<Kilometrage> findByIdavion(Long avionid);
}
