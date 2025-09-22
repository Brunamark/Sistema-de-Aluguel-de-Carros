package com.aluguel_carros.sistema.infrastructure.repository;

import com.aluguel_carros.sistema.infrastructure.entity.RentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentRequestRepository extends CrudRepository<RentRequest, Long> {
    @Override
    Optional<RentRequest> save(RentRequest rentRequest);



}
