package com.example.gestionflotte.controller;

import com.example.gestionflotte.modele.Avion;
import com.example.gestionflotte.service.AvionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/GestionFlotte")
public class AvionController {
    private final AvionService service;

    @Autowired
    public AvionController(AvionService service) {
        this.service = service;
    }

    @GetMapping("/avions")
    public List<Avion> getavions() {
        return this.service.getavions();
    }

    @GetMapping("/avions/{avionid}")
    private Avion getavion(@PathVariable("avionid") Long avionid)
    {
        return this.service.getavionById(avionid);
    }

    @PostMapping("/avions")
    private Long saveavion(@RequestBody Avion avion)
    {
        this.service.saveOrUpdate(avion);
        return avion.getId();
    }

    @PutMapping("/avions")
    private Avion updateavion(@RequestBody Avion avion)
    {
        this.service.saveOrUpdate(avion);
        return avion;
    }

    @DeleteMapping("/avions/{avionid}")
    private void deleteavion(@PathVariable("avionid") Long avionid)
    {
        this.service.delete(avionid);
    }
    @PutMapping("/avions/{avionid}/image/{image}")
    private void updateImageAvion(@PathVariable("avionid") Long avionid,@PathVariable("image") String image)
    {
        this.service.updateImageAvion(avionid,image);
    }
}
