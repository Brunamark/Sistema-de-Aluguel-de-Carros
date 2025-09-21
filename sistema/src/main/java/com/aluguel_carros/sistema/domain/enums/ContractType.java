package com.aluguel_carros.sistema.domain.enums;

import lombok.Getter;

@Getter
public enum ContractType {
    SIMPLE("Simples"),
    CREDIT("Crédito");

    private final String displayName;

    ContractType(String displayName) {
        this.displayName = displayName;
    }

    public static ContractType fromString(String text) {
        for (ContractType type : ContractType.values()) {
            if (type.name().equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Tipo de contrato desconhecido: " + text);
    }
}
