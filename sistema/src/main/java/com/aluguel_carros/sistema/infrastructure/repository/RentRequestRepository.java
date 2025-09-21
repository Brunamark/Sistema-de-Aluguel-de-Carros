package com.aluguel_carros.sistema.infrastructure.repository;

import com.aluguel_carros.sistema.infrastructure.entity.RentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRequestRepository extends JpaRepository<RentRequest, Long> {
}
