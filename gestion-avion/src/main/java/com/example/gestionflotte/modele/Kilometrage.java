package com.example.gestionflotte.modele;

import lombok.*;

import javax.persistence.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "kilometrage")
public class Kilometrage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "avion_id")
    private Avion avion;
    private Date date;
    @Column(name = "kilometrage")
    private int kilometrage;
}
