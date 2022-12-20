package com.example.gestionflotte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestionflotte.modele.Admin;
import com.example.gestionflotte.service.AdminService;

@RestController
@RequestMapping("/GestionFlotte")
public class AdminController {
    private final AdminService service;

    @Autowired
    public AdminController(AdminService services) {
        this.service = services;
    }

    @GetMapping("/login/{email}/{mdp}")
    public boolean logAdmin(@PathVariable("email") String email,@PathVariable("mdp") String mdp){
        boolean rep = false;
        List<Admin> liste = service.getAdminEmail(email, mdp);
        if(liste.size()==1){
            rep = true;
        }
        else{
            rep = false;
        }
        return rep;
    }
    
}
