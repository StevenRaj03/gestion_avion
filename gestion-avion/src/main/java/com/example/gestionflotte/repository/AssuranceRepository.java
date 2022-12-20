package com.example.gestionflotte.repository;

import com.example.gestionflotte.modele.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssuranceRepository extends JpaRepository<Assurance, Long> {
    @Query(value = "SELECT a FROM assurance a WHERE a.avion.id = ?1")
    public List<Assurance> findByIdavion(Long avionid);
    @Query(value = "SELECT * FROM assurance a WHERE  a.date_expiration < now() + (interval '1 month' * :myInterval) and a.date_expiration > now()", nativeQuery = true)
    public List<Assurance> findExpiration(@Param("myInterval") int interval);
}
