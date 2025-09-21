package com.aluguel_carros.sistema.domain.enums;

import lombok.Getter;

@Getter
public enum Role {
    CLIENT("Cliente"),
    AGENT("Agente");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public static Role fromString(String text) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(text)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Função desconhecida: " + text);
    }
}