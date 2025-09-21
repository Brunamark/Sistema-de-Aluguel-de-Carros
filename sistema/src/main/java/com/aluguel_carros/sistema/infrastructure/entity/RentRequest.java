package com.aluguel_carros.sistema.infrastructure.entity;

import ch.qos.logback.core.net.server.Client;
import com.aluguel_carros.sistema.domain.enums.Executed;
import com.aluguel_carros.sistema.domain.enums.RequestType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class RentRequest {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @Column(nullable = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @Column(nullable = false)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @ManyToOne
    @Column(nullable = false)
    @JoinColumn(name = "automobile_id")
    private Automobile automobile;

    @Column(nullable = false)
    @Min(10)
    private float price;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Executed executed;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestType requestType;
}
