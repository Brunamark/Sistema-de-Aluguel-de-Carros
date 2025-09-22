package com.aluguel_carros.sistema.infrastructure.controller;


import com.sistema_aluguel.api.PedidosAluguelApi;
import com.sistema_aluguel.model.RentRequest;
import com.sistema_aluguel.model.RentRequestResponse;
import com.sistema_aluguel.model.RentRequestStatusUpdate;
import com.sistema_aluguel.model.RentRequestUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class RentRequestController implements PedidosAluguelApi {

    @Override
    public ResponseEntity<RentRequestResponse> createRentRequest(RentRequest rentRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteRentRequest(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<RentRequestResponse> getRentRequest(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<RentRequestResponse> updateRentRequest(Long id, RentRequestUpdate rentRequestUpdate) {
        return null;
    }

    @Override
    public ResponseEntity<RentRequestResponse> updateRentRequestStatus(Long id, RentRequestStatusUpdate rentRequestStatusUpdate) {
        return null;
    }
}
