package com.example.springbootapp.model;

import javax.persistence.*;

@Entity
@Table(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String nom;

    public Type(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }


    public Long getId() {
        return id;
    }

    public Type() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }



}