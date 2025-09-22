package com.aluguel_carros.sistema.domain.service;

import com.aluguel_carros.sistema.infrastructure.entity.Contract;
import com.aluguel_carros.sistema.infrastructure.exception.ContractException;
import com.aluguel_carros.sistema.infrastructure.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;

    public Contract getContractById(Long id){
        return contractRepository.findById(id).orElseThrow(()-> new ContractException("Contrato não encontrado."));
    }
}
