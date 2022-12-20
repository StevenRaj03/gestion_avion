package com.example.gestionflotte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestionflotte.modele.Admin;
import com.example.gestionflotte.repository.AdminRepository;

@Service
public class AdminService {
    AdminRepository repository;

    @Autowired
    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    public List<Admin> getAdminEmail(String email,String mdp){
        return this.repository.findbyEmail(email, mdp);
    }
}
