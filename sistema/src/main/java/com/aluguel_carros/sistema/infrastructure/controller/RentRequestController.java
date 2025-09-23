package com.aluguel_carros.sistema.infrastructure.controller;


import com.aluguel_carros.sistema.domain.enums.Executed;
import com.aluguel_carros.sistema.domain.service.AutomobileService;
import com.aluguel_carros.sistema.domain.service.ContractService;
import com.aluguel_carros.sistema.domain.service.RentRequestService;
import com.aluguel_carros.sistema.infrastructure.exception.RentRequestException;
import com.sistema_aluguel.api.PedidosAluguelApi;
import com.sistema_aluguel.model.RentRequest;
import com.sistema_aluguel.model.RentRequestResponse;
import com.sistema_aluguel.model.RentRequestStatusUpdate;
import com.sistema_aluguel.model.RentRequestUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.aluguel_carros.sistema.infrastructure.mapper.RentRequestMapper;

@Controller
public class RentRequestController implements PedidosAluguelApi {

    @Autowired
    private RentRequestService rentRequestService;
    @Autowired
    private AutomobileService automobileService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private RentRequestMapper rentRequestMapper;

    @Override
    public ResponseEntity<RentRequestResponse> createRentRequest(RentRequest rentRequest) {
        var rentRequestEntity = rentRequestMapper.toDomain(rentRequest);
        rentRequestService.createRentRequest(rentRequestEntity);
        return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<Void> deleteRentRequest(Long id) {
        rentRequestService.deleteRentRequest(id);
        return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<RentRequestResponse> getRentRequest(Long id) {
        var rentRequest = rentRequestService.getRentRequestById(id);
        return ResponseEntity.ok().body(rentRequestMapper.toResponse(rentRequest));

    }

    @Override
    public ResponseEntity<RentRequestResponse> updateRentRequest(Long id, RentRequestUpdate rentRequestUpdate) {
        var automobileRetrieved = automobileService.getAutomobileById(rentRequestUpdate.getAutomobileId());
        var contractRetrieved = contractService.getContractById(rentRequestUpdate.getContractId());
        var rentRequest = rentRequestService.getRentRequestById(id);

        rentRequest.setContract(contractRetrieved);
        rentRequest.setAutomobile(automobileRetrieved);
        rentRequest.setPrice(rentRequestUpdate.getPrice());
        rentRequest.setStartDate(rentRequestUpdate.getStartDate());
        rentRequest.setEndDate(rentRequestUpdate.getEndDate());

        rentRequest = rentRequestService.updateRentRequestStatus(rentRequest);

        return ResponseEntity.ok().body(rentRequestMapper.toResponse(rentRequest));
    }

    @Override
    public ResponseEntity<RentRequestResponse> updateRentRequestStatus(Long id, RentRequestStatusUpdate rentRequestStatusUpdate) {
        var rentRequest = rentRequestService.getRentRequestById(id);
        Executed mappedStatus = mapStatusEnum(rentRequestStatusUpdate.getStatus());

        if(rentRequest.getExecuted().equals(Executed.APPROVED)){
            throw new RentRequestException("Não pode mudar o status de aprovado.");
        }

        rentRequest.setExecuted(mappedStatus);
        return ResponseEntity.ok().body(rentRequestMapper.toResponse(rentRequest));
    }

    private Executed mapStatusEnum(RentRequestStatusUpdate.StatusEnum statusEnum) {
        return switch (statusEnum) {
            case PENDING -> Executed.PENDING;
            case APPROVED -> Executed.APPROVED;
            case REJECTED -> Executed.REJECTED;
            case CANCELED -> Executed.REJECTED;
            case COMPLETED -> Executed.APPROVED;
            default -> throw new IllegalArgumentException("Status não reconhecido: " + statusEnum);
        };
    }
}
