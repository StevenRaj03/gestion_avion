package com.example.gestionflotte.service;

import com.example.gestionflotte.exception.ResourceNotFoundException;
import com.example.gestionflotte.modele.Avion;
import com.example.gestionflotte.repository.AvionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class AvionService {

    private final AvionRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AvionService(AvionRepository repository) {
        this.repository = repository;
    }

    public List<Avion> getavions() {
        return this.repository.findAll();
    }

    public Avion getavionById(long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("avion n'existe pas avec l'id :" + id));
    }

    public void saveOrUpdate(Avion avion) {
        this.repository.save(avion);
    }
    
    public void delete(long id) {
        this.repository.deleteById(id);
    }
    
    public void update(Avion avion, long avionid) {
        Avion get = getavionById(avionid);
        get.setMatricule(avion.getMatricule());
        get.setModele(avion.getModele());
        this.repository.save(get);
    }
    @Transactional
    public void updateImageAvion(long avionid,String image){
        /*Avion get = getavionById(avionid);
        get.setImage(image);
        this.repository.saveImageAvion(avionid,image);*/
        String sql = "UPDATE avion set image=decode('"+image+"','base64') where id="+avionid;
        entityManager.createNativeQuery(sql);
    }
}
