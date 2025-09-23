package com.aluguel_carros.sistema.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="automobile")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Automobile {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "automobile")
    private List<RentRequest> rentRequests;

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
