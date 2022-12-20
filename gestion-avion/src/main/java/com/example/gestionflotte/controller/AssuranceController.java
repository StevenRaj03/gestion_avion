package com.example.gestionflotte.controller;

import com.example.gestionflotte.modele.Assurance;
import com.example.gestionflotte.service.AssuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/GestionFlotte")
public class AssuranceController {
    private final AssuranceService service;

    @Autowired
    public AssuranceController(AssuranceService service) {
        this.service = service;
    }

    @GetMapping("/assurances")
    public List<Assurance> getAssurances() {
        return this.service.getAssurances();
    }

    @GetMapping("/avions/{avionid}/assurances")
    public List<Assurance> getAssurancesParavion(@PathVariable("avionid") Long avionid) {
        return this.service.getAssurancesParavion(avionid);
    }

    @GetMapping("/assurances/expiration/{valeur}")
    public List<Assurance> getAssurancesExpiration(@PathVariable("valeur") int valeur) {
        return this.service.getAssurancesExpiration(valeur);
    }

    @GetMapping("/assurances/{assuranceid}")
    private Assurance getAssurance(@PathVariable("assuranceid") Long assuranceid)
    {
        return this.service.getAssuranceById(assuranceid);
    }

    @PostMapping("/assurances")
    private Long saveAssurance(@RequestBody Assurance assurance)
    {
        this.service.saveOrUpdate(assurance);
        return assurance.getId();
    }

    @PutMapping("/assurances")
    private Assurance updateAssurance(@RequestBody Assurance assurance)
    {
        this.service.saveOrUpdate(assurance);
        return assurance;
    }

    @DeleteMapping("/assurances/{assuranceid}")
    private void deleteAssurance(@PathVariable("assuranceid") Long assuranceid)
    {
        this.service.delete(assuranceid);
    }
}
