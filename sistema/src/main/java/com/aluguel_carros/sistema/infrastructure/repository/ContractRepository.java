package com.aluguel_carros.sistema.infrastructure.repository;

import com.aluguel_carros.sistema.infrastructure.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
}
