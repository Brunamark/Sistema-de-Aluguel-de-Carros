package com.aluguel_carros.sistema.domain.service;

import com.aluguel_carros.sistema.domain.dto.RentRequestDTO;
import com.aluguel_carros.sistema.domain.enums.Executed;
import com.aluguel_carros.sistema.infrastructure.entity.RentRequest;
import com.aluguel_carros.sistema.infrastructure.exception.RentRequestException;
import com.aluguel_carros.sistema.infrastructure.mapper.RentRequestMapper;
import com.aluguel_carros.sistema.infrastructure.repository.RentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentRequestService {

    @Autowired
    private RentRequestMapper rentRequestMapper;
    @Autowired
    private RentRequestRepository rentRequestRepository;
    @Autowired
    private AutomobileService automobileService;
    @Autowired
    private ContractService contractService;

    public RentRequest createRentRequest(RentRequest rentRequest){
        rentRequest.setExecuted(Executed.PENDING);
        rentRequest = rentRequestRepository.save(rentRequest).orElseThrow(
                () -> new RentRequestException("Não foi possível cadastrar um pedido de aluguel")
        );
        return rentRequest;
    }

    public RentRequest updateRentRequest(RentRequest rentRequest){
        Optional<RentRequest> rentRequestRetrieved = Optional.ofNullable(rentRequestRepository.findById(id).orElseThrow(
                () -> new RentRequestException("Não foi possível encontrar o pedido de aluguel")
        ));
        rentRequestRetrieved.get().setRequestType(rentRequest.getRequestType());
        rentRequestRetrieved.get().setAutomobile(rentRequest.getAutomobile());
        rentRequestRetrieved.get().setContract(rentRequest.getContract());
        rentRequestRetrieved.get().setPrice(rentRequest.getPrice());
        rentRequestRetrieved.get().setRequestType(rentRequest.getRequestType());
        return rentRequestRepository.save(rentRequest).orElseThrow(
                () -> new RentRequestException("Não foi possível atualizar esse pedido de aluguel."));
    }

    public void deleteRentRequest(Long id){

    }

    public RentRequest updateRentRequestStatus(RentRequest rentRequest){
        return null;
    }

    public RentRequest getRentRequestById(Long id){
        return null;
    }
}
