package com.example.gestionflotte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.gestionflotte.modele.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query(value = "SELECT k FROM admin k WHERE k.email = ?1 and k.mdp=?2")
    public List<Admin> findbyEmail(String email,String mdp);
}
