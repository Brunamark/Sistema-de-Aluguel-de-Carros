package com.aluguel_carros.sistema.domain.enums;

import lombok.Getter;

@Getter
public enum RequestType {
    CLIENTS("Clientes"),
    BANKS("Bancos"),
    ENTERPRISE("Empresas");

    private final String displayName;

    RequestType(String displayName) {
        this.displayName = displayName;
    }

    public static RequestType fromString(String text) {
        for (RequestType type : RequestType.values()) {
            if (type.name().equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Tipo de solicitação desconhecido: " + text);
    }
}