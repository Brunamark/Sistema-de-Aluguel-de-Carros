package com.aluguel_carros.sistema.infrastructure.entity;

import com.aluguel_carros.sistema.domain.enums.Executed;
import com.aluguel_carros.sistema.domain.enums.RequestType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="rent_request")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentRequest {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    @NotNull
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "automobile_id")
    @NotNull
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
