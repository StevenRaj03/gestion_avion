package com.example.gestionflotte.controller;

import com.example.gestionflotte.modele.Kilometrage;
import com.example.gestionflotte.service.KilometrageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/GestionFlotte")
public class KilometrageController {

    private final KilometrageService service;

    @Autowired
    public KilometrageController(KilometrageService service) {
        this.service = service;
    }

    @GetMapping("/kilometrages")
    public List<Kilometrage> getKilometrages() {
        return this.service.getKilometrages();
    }

    @GetMapping("/avions/{avionid}/kilometrages")
    public List<Kilometrage> getKilometragesParavion(@PathVariable("avionid") Long avionid) {
        return this.service.getKilometragesParavion(avionid);
    }

    @GetMapping("/kilometrages/{kilometrageid}")
    private Kilometrage getKilometrage(@PathVariable("kilometrageid") Long kilometrageid)
    {
        return this.service.getKilometrageById(kilometrageid);
    }

    @PostMapping("/kilometrages")
    private Long saveKilometrage(@RequestBody Kilometrage kilometrage)
    {
        this.service.saveOrUpdate(kilometrage);
        return kilometrage.getId();
    }

    @PutMapping("/kilometrages")
    private Kilometrage updateKilometrage(@RequestBody Kilometrage kilometrage)
    {
        this.service.saveOrUpdate(kilometrage);
        return kilometrage;
    }

    @DeleteMapping("/kilometrages/{kilometrageid}")
    private void deleteKilometrage(@PathVariable("kilometrageid") Long kilometrageid)
    {
        this.service.delete(kilometrageid);
    }
}
