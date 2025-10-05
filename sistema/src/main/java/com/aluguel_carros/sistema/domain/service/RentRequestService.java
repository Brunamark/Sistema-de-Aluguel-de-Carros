package com.aluguel_carros.sistema.domain.service;

import com.aluguel_carros.sistema.domain.enums.Executed;
import com.aluguel_carros.sistema.domain.enums.Role;
import com.aluguel_carros.sistema.infrastructure.entity.RentRequest;
import com.aluguel_carros.sistema.infrastructure.exception.ContractException;
import com.aluguel_carros.sistema.infrastructure.exception.DatabaseException;
import com.aluguel_carros.sistema.infrastructure.exception.RentRequestException;
import com.aluguel_carros.sistema.infrastructure.exception.UnauthorizedException;
import com.aluguel_carros.sistema.infrastructure.mapper.RentRequestMapper;
import com.aluguel_carros.sistema.infrastructure.repository.RentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
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
    @Autowired
    private UserService userService;

    public RentRequest createRentRequest(RentRequest rentRequest, Long userId) {
        if (rentRequest.getStartDate().isBefore(LocalDate.now()) || rentRequest.getEndDate().isBefore(LocalDate.now())) {
            throw new RentRequestException("As datas de início e de término não podem ser antes da data de hoje.");
        }

        if (rentRequest.getStartDate().isAfter(rentRequest.getEndDate())) {
            throw new RentRequestException("A data de término não pode ser menor que a data de início do contrato.");
        }

        if( rentRequest.getEndDate().isAfter(rentRequest.getStartDate().plusMonths(12))){
            throw new RentRequestException("A data de término não pode ser maior que 1 ano em relação a data de início");
        }

        if(rentRequest.getPrice()<1000){
            throw new RentRequestException("O preço não pode ser inferior a R$1000,00");
        }

        try {
            rentRequest = rentRequestRepository.save(rentRequest);
        } catch (DatabaseException e) {
            throw new RentRequestException("Não foi possível cadastrar um pedido de aluguel");
        }

        return rentRequest;
    }

    public RentRequest updateRentRequest(RentRequest rentRequestUpdate, Long id, Long userId) {
        var rentRequest = getRentRequestById(id);

        var user = userService.getUserById(userId);

        if(!Objects.equals(userId, rentRequest.getUser().getId()) && user.getRole().equals(Role.CLIENT)){
            throw  new UnauthorizedException("O usuário não pode modificar pedidos que não os pertecem.");
        }

        if(user.getRole().equals(Role.CLIENT)
                && rentRequestUpdate.getExecuted() != null
                && !rentRequest.getExecuted().equals(rentRequestUpdate.getExecuted())){
            throw  new UnauthorizedException("O usuário não pode modificar o status do pedido de aluguel.");

        }

        var automobileRetrieved = automobileService.getAutomobileById(rentRequestUpdate.getAutomobile().getId());
        var contractRetrieved = contractService.getContractById(rentRequestUpdate.getContract().getId());

        rentRequest.setContract(contractRetrieved);
        rentRequest.setAutomobile(automobileRetrieved);
        rentRequest.setPrice(rentRequestUpdate.getPrice());
        rentRequest.setStartDate(rentRequestUpdate.getStartDate());
        rentRequest.setEndDate(rentRequestUpdate.getEndDate());


        return createRentRequest(rentRequest, userId);
    }


    public void deleteRentRequest(Long id, Long userId) {
        var rentRequest = getRentRequestById(id);

        if(!Objects.equals(rentRequest.getUser().getId(), userId)){
            throw  new UnauthorizedException("Usuário só pode cancelar pedidos de aluguel associado a ele.");
        }

        try {
            rentRequestRepository.deleteById(id);
        } catch (DatabaseException e) {
            throw new RentRequestException("Erro ao deletar o pedido de aluguel");
        }
    }

    public RentRequest updateRentRequestStatus(RentRequest rentRequestPatch, Long userId) {
        return createRentRequest(rentRequestPatch, userId);
    }

    public RentRequest getRentRequestById(Long id) {
        return rentRequestRepository.findById(id)
                .orElseThrow(() -> new RentRequestException("Não foi possível encontrar o pedido de aluguel"));
    }
}
