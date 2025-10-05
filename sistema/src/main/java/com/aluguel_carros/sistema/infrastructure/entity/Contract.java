package com.aluguel_carros.sistema.infrastructure.entity;

import com.aluguel_carros.sistema.domain.enums.ContractType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name="contract")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contract {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    @Column(nullable = false)
    private int creditQuantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractType contractType;



}
