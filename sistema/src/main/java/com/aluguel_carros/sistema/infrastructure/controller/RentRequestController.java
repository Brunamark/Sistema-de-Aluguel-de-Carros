package com.aluguel_carros.sistema.infrastructure.controller;


import com.aluguel_carros.sistema.domain.service.AutomobileService;
import com.aluguel_carros.sistema.domain.service.ContractService;
import com.aluguel_carros.sistema.domain.service.RentRequestService;
import com.sistema_aluguel.api.PedidosAluguelApi;
import com.sistema_aluguel.model.RentRequest;
import com.sistema_aluguel.model.RentRequestResponse;
import com.sistema_aluguel.model.RentRequestStatusUpdate;
import com.sistema_aluguel.model.RentRequestUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.aluguel_carros.sistema.infrastructure.mapper.RentRequestMappper;

@Controller
public class RentRequestController implements PedidosAluguelApi {

    @Autowired
    private RentRequestService rentRequestService;
    @Autowired
    private AutomobileService automobileService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private RentRequestMappper rentRequestMappper;

    @Override
    public ResponseEntity<RentRequestResponse> createRentRequest(RentRequest rentRequest) {
        rentRequestService.createRentRequest(rentRequest);
        return ResponseEntity.ok();

    }

    @Override
    public ResponseEntity<Void> deleteRentRequest(Long id) {

        rentRequestService.deleteRentRequest(id);
        return ResponseEntity.ok();

    }

    @Override
    public ResponseEntity<RentRequestResponse> getRentRequest(Long id) {

        var rentRequest = rentRequestService.getRentRequestById(id);
        return ResponseEntity.ok().body(rentRequestMappper.toResponse(rentRequest));

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

        rentRequest = rentRequestService.updateRentRequest(rentRequest);

        return ResponseEntity.ok().body(rentRequestMappper.toResponse(rentRequest));
    }

    @Override
    public ResponseEntity<RentRequestResponse> updateRentRequestStatus(Long id, RentRequestStatusUpdate rentRequestStatusUpdate) {
        var rentRequest = rentRequestService.getRentRequestById(id);


        return ResponseEntity.ok().body(rentRequestMappper.toResponse(rentRequest));
    }
}
