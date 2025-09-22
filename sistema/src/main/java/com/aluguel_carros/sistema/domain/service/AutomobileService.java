package com.aluguel_carros.sistema.domain.service;

import com.aluguel_carros.sistema.infrastructure.entity.Automobile;
import com.aluguel_carros.sistema.infrastructure.exception.AutomobileException;
import com.aluguel_carros.sistema.infrastructure.repository.AutomobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutomobileService {

    @Autowired
    private AutomobileRepository automobileRepository;

    public Automobile getAutomobileById(Long id){
        return  automobileRepository.findById(id).orElseThrow(()-> new AutomobileException("Automóvel não encontrado"));
    }
}
