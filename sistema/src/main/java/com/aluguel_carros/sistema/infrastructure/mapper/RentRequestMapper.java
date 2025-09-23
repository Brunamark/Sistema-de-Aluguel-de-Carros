package com.aluguel_carros.sistema.infrastructure.mapper;

import com.aluguel_carros.sistema.infrastructure.entity.Automobile;
import com.aluguel_carros.sistema.infrastructure.entity.Contract;
import com.aluguel_carros.sistema.infrastructure.entity.RentRequest;
import com.aluguel_carros.sistema.infrastructure.entity.User;
import com.sistema_aluguel.model.RentRequestResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RentRequestMapper {
    @Mapping(target = "user", source = "userId", qualifiedByName = "toUser")
    @Mapping(target = "contract", source = "contractId", qualifiedByName = "toContract")
    @Mapping(target = "automobile", source = "automobileId", qualifiedByName = "toAutomobile")
    RentRequest toDomain(com.sistema_aluguel.model.RentRequest rentRequestModel);


    RentRequestResponse toResponse(RentRequest rentRequest);

    @Named("toUser")
    default User toUser(Long userId) {
        if (userId == null) {
            return null;
        }
        User user = new User();
        user.setId(userId);
        return user;
    }

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
