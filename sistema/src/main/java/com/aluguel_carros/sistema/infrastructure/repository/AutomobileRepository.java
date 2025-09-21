package com.aluguel_carros.sistema.infrastructure.repository;

import com.aluguel_carros.sistema.infrastructure.entity.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AutomobileRepository extends JpaRepository<Automobile, Long> {
}
