package com.aluguel_carros.sistema.infrastructure.controller;


import com.aluguel_carros.sistema.domain.enums.Executed;
import com.aluguel_carros.sistema.domain.enums.Role;
import com.aluguel_carros.sistema.domain.service.AutomobileService;
import com.aluguel_carros.sistema.domain.service.ContractService;
import com.aluguel_carros.sistema.domain.service.RentRequestService;
import com.aluguel_carros.sistema.domain.service.UserService;
import com.aluguel_carros.sistema.infrastructure.exception.RentRequestException;
import com.aluguel_carros.sistema.infrastructure.exception.UnauthorizedException;
import com.sistema_aluguel.api.PedidosAluguelApi;
import com.sistema_aluguel.model.*;
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
    private UserService userService;
    @Autowired
    private RentRequestMapper rentRequestMapper;

    @Override
    public ResponseEntity<RentRequestResponse> createRentRequest(Long userId, RentRequest rentRequest) {
        var user = userService.getUserById(userId);
        if(!user.getRole().equals(Role.CLIENT)){
            throw new UnauthorizedException("Usuário não pode criar um pedido de aluguel");
        }

        var rentRequestEntity = rentRequestMapper.toDomain(rentRequest);
        rentRequestEntity.setUser(user);
        rentRequestEntity.setExecuted(Executed.PENDING);
        rentRequestService.createRentRequest(rentRequestEntity, userId);
        return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<Void> deleteRentRequest(Long userId, Long id) {
        var user = userService.getUserById(userId);
        if(!user.getRole().equals(Role.CLIENT)){
            throw new UnauthorizedException("Usuário não pode cancelar um pedido de aluguel");
        }
        rentRequestService.deleteRentRequest(id, userId);
        return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<RentRequestResponse> getRentRequest(Long userId, Long id) {
        var user = userService.getUserById(userId);
        if(!user.getRole().equals(Role.CLIENT) && !user.getRole().equals(Role.AGENT)){
            throw new UnauthorizedException("Usuário não pode buscar o pedido de aluguel");
        }
        var rentRequest = rentRequestService.getRentRequestById(id);
        return ResponseEntity.ok().body(rentRequestMapper.toResponse(rentRequest));

    }

    @Override
    public ResponseEntity<RentRequestStatusResponse> getRentRequestStatus(Long userId, Long id) {
        var user = userService.getUserById(userId);
        if(!user.getRole().equals(Role.CLIENT) && !user.getRole().equals(Role.AGENT)){
            throw new UnauthorizedException("Usuário não pode buscar o pedido de aluguel");
        }
        var rentRequest = rentRequestService.getRentRequestById(id);
        return ResponseEntity.ok().body(rentRequestMapper.toStatusResponse(rentRequest));

    }

    @Override
    public ResponseEntity<RentRequestResponse> updateRentRequest(Long userId, Long id, RentRequestUpdate rentRequestUpdate) {
        var user = userService.getUserById(userId);
        if(!user.getRole().equals(Role.CLIENT) && !user.getRole().equals(Role.AGENT)){
            throw new UnauthorizedException("Usuário não pode modificar o pedido de aluguel");
        }

        var rentRequest = rentRequestMapper.toDomain(rentRequestUpdate);

        rentRequest = rentRequestService.updateRentRequest(rentRequest,id, userId);

        return ResponseEntity.ok().body(rentRequestMapper.toResponse(rentRequest));
    }

    @Override
    public ResponseEntity<RentRequestResponse> updateRentRequestStatus(Long userId, Long id, RentRequestStatusUpdate rentRequestStatusUpdate) {
        var user = userService.getUserById(userId);
        if(!user.getRole().equals(Role.AGENT)){
            throw new UnauthorizedException("Usuário não pode modificar status do pedido de aluguel");
        }

        var rentRequest = rentRequestService.getRentRequestById(id);

        if(rentRequest.getExecuted().equals(Executed.APPROVED)){
            throw new RentRequestException("Não pode mudar o status de um pedido de aluguel já aprovado.");
        }

        Executed mappedStatus = mapStatusEnum(rentRequestStatusUpdate.getStatus());
        rentRequest.setExecuted(mappedStatus);


        rentRequestService.updateRentRequestStatus(rentRequest,userId);
        return ResponseEntity.ok().body(rentRequestMapper.toResponse(rentRequest));
    }

    private Executed mapStatusEnum(RentRequestStatusUpdate.StatusEnum statusEnum) {
        return switch (statusEnum) {
            case PENDING -> Executed.PENDING;
            case APPROVED, COMPLETED -> Executed.APPROVED;
            case REJECTED, CANCELED -> Executed.REJECTED;
            default -> throw new IllegalArgumentException("Status não reconhecido: " + statusEnum);
        };
    }

}
