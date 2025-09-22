package com.aluguel_carros.sistema.infrastructure.mapper;

import com.aluguel_carros.sistema.domain.dto.RentRequestDTO;
import com.sistema_aluguel.model.RentRequest;
import com.sistema_aluguel.model.RentRequestResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentRequestMapper {
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "contractId", target = "contract.id")
    @Mapping(source = "automobileId", target = "automobile.id")
    RentRequest toDomain(RentRequestDTO rentRequestDTO);

    @Mapping(source = "user.id", target = "user.id")
    @Mapping(source = "contract.id", target = "contractId")
    @Mapping(source = "automobile.id", target = "automobileId")
    RentRequestDTO toRentRequestDTO(RentRequest rentRequest);

    RentRequestResponse toResponse(RentRequestDTO rentRequestDTO);
}
