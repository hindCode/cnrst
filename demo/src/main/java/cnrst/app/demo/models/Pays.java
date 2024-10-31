package cnrst.app.demo.models;


import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDate;

@jakarta.persistence.Entity
@Data
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String abreviation;
    private String intitule;
    private int ordre;
    private LocalDate dateDerniereModification;

 }
