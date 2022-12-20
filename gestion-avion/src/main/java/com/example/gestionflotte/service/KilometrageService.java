package com.example.gestionflotte.service;

import com.example.gestionflotte.exception.ResourceNotFoundException;
import com.example.gestionflotte.modele.Kilometrage;
import com.example.gestionflotte.repository.KilometrageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KilometrageService {

    private final KilometrageRepository repository;

    @Autowired
    public KilometrageService(KilometrageRepository repository) {
        this.repository = repository;
    }

    public List<Kilometrage> getKilometrages() {
        return this.repository.findAll();
    }

    public List<Kilometrage> getKilometragesParavion(Long avionid) {
        return this.repository.findByIdavion(avionid);
    }

    public Kilometrage getKilometrageById(long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kilometrage n'existe pas avec l'id :" + id));
    }

    public void saveOrUpdate(Kilometrage kilometrage) {
        this.repository.save(kilometrage);
    }

    public void delete(long id) {
        this.repository.deleteById(id);
    }

    public void update(Kilometrage kilometrage, long kilometrageid) {
        Kilometrage get = getKilometrageById(kilometrageid);
        get.setAvion(kilometrage.getAvion());
        get.setDate(kilometrage.getDate());
        get.setKilometrage(kilometrage.getKilometrage());
        this.repository.save(get);
    }
}
