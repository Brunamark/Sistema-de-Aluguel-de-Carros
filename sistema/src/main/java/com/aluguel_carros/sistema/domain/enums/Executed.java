package com.aluguel_carros.sistema.domain.enums;

import lombok.Getter;

@Getter
public enum Executed {
        PENDING("Pendente"),
        EVALUATED("Avaliado"),
        REJECTED("Rejeitado"),
        APPROVED("Aprovado");

        private final String displayName;

        Executed(String displayName) {
                this.displayName = displayName;
        }

    public static Executed fromString(String text) {
                for (Executed e : Executed.values()) {
                        if (e.name().equalsIgnoreCase(text)) {
                                return e;
                        }
                }
                throw new IllegalArgumentException("Status desconhecido: " + text);
        }
}
