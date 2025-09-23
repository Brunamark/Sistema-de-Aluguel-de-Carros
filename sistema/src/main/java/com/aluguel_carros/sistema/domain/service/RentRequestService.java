package com.aluguel_carros.sistema.domain.service;

import com.aluguel_carros.sistema.domain.enums.Executed;
import com.aluguel_carros.sistema.infrastructure.entity.RentRequest;
import com.aluguel_carros.sistema.infrastructure.exception.DatabaseException;
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

        try{
            rentRequest = rentRequestRepository.save(rentRequest);
        }catch (DatabaseException e){
            throw new RentRequestException("Não foi possível cadastrar um pedido de aluguel");
        }

        return rentRequest;
    }

    public RentRequest updateRentRequest(RentRequest rentRequest){
        return createRentRequest(rentRequest);
    }

    public void deleteRentRequest(Long id){
        getRentRequestById(id);
        try{
            rentRequestRepository.deleteById(id);
        }catch (DatabaseException e){
            throw  new RentRequestException("Erro ao deletar o pedido de aluguel");
        }
    }

    public RentRequest updateRentRequestStatus(RentRequest rentRequest){
        return updateRentRequest(rentRequest);
    }

    public RentRequest getRentRequestById(Long id){
        Optional<RentRequest> rentRequestRetrieved = Optional.ofNullable(rentRequestRepository.findById(id).orElseThrow(
                () -> new RentRequestException("Não foi possível encontrar o pedido de aluguel")
        ));
        return  rentRequestRetrieved.get();
    }
}
