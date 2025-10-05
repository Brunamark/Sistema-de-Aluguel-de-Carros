package com.aluguel_carros.sistema.infrastructure.mapper;

import com.aluguel_carros.sistema.infrastructure.entity.Automobile;
import com.aluguel_carros.sistema.infrastructure.entity.Contract;
import com.aluguel_carros.sistema.infrastructure.entity.RentRequest;
import com.aluguel_carros.sistema.infrastructure.entity.User;
import com.sistema_aluguel.model.RentRequestResponse;
import com.sistema_aluguel.model.RentRequestStatusResponse;
import com.sistema_aluguel.model.RentRequestStatusUpdate;
import com.sistema_aluguel.model.RentRequestUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RentRequestMapper {
    @Mapping(target = "contract", source = "contractId", qualifiedByName = "toContract")
    @Mapping(target = "automobile", source = "automobileId", qualifiedByName = "toAutomobile")
    RentRequest toDomain(com.sistema_aluguel.model.RentRequest rentRequestModel);

    @Mapping(target = "contract", source = "contractId", qualifiedByName = "toContract")
    @Mapping(target = "automobile", source = "automobileId", qualifiedByName = "toAutomobile")
    RentRequest toDomain(RentRequestUpdate rentRequestUpdate);

    RentRequestStatusResponse toStatusResponse(RentRequest rentRequest);


    RentRequestResponse toResponse(RentRequest rentRequest);


    @Named("toContract")
    default Contract toContract(Long contractId) {
        if (contractId == null) {
            return null;
        }
        Contract contract = new Contract();
        contract.setId(contractId);
        return contract;
    }

    @Named("toAutomobile")
    default Automobile toAutomobile(Long automobileId) {
        if (automobileId == null) {
            return null;
        }
        Automobile automobile = new Automobile();
        automobile.setId(automobileId);
        return automobile;
    }
}
