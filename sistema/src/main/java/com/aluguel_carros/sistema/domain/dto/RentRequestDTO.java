package com.aluguel_carros.sistema.domain.dto;

import com.aluguel_carros.sistema.domain.enums.RequestType;

import java.time.LocalDate;

public record RentRequestDTO(
        Long userId,
        Long contractId,
        Long automobileId,
        Float price,
        LocalDate startDate,
        LocalDate endDate,
        RequestType requestType

) {}
