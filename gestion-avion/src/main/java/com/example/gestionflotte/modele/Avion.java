package com.example.gestionflotte.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "avion")
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="modele_id")
    private Modele modele;
    private String matricule;
    
    @OneToMany
    @JsonIgnore
    private Set<Kilometrage> kilometrages;

    @OneToMany
    @JsonIgnore
    private Set<Assurance> assurances;
    private String image;
}
