package com.aluguel_carros.sistema.infrastructure.entity;

import com.aluguel_carros.sistema.domain.enums.ContractType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.util.UUID;

@Entity
public class Contract {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    private Long id;

    @Min(0)
    @Column(nullable = false)
    private int creditQuantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractType contractType;


}
