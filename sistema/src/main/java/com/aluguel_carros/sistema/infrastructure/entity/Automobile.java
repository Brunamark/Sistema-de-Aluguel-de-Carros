package com.aluguel_carros.sistema.infrastructure.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Automobile {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String registration;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String registrationPlate;

    @Column(nullable = false)
    private String model;

}
