package com.example.gestionflotte.service;

import com.example.gestionflotte.exception.ResourceNotFoundException;
import com.example.gestionflotte.modele.Assurance;
import com.example.gestionflotte.repository.AssuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssuranceService {
    private final AssuranceRepository repository;

    @Autowired
    public AssuranceService(AssuranceRepository repository) {
        this.repository = repository;
    }

    public List<Assurance> getAssurances() {
        return this.repository.findAll();
    }

    public List<Assurance> getAssurancesParavion(Long avionid) {
        return this.repository.findByIdavion(avionid);
    }

    public List<Assurance> getAssurancesExpiration(int valeur) {
        return this.repository.findExpiration(valeur);
    }

    public Assurance getAssuranceById(long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assurance n'existe pas avec l'id :" + id));
    }

    public void saveOrUpdate(Assurance kilometrage) {
        this.repository.save(kilometrage);
    }

    public void delete(long id) {
        this.repository.deleteById(id);
    }

    public void update(Assurance kilometrage, long kilometrageid) {
        Assurance get = getAssuranceById(kilometrageid);
        get.setAvion(kilometrage.getAvion());
        get.setDate_expiration(kilometrage.getDate_expiration());

        get.setMontant(kilometrage.getMontant());
        this.repository.save(get);
    }
}
