package com.aluguel_carros.sistema.infrastructure.exception;

public class RentRequestException extends RuntimeException {
    public RentRequestException(String message) {
        super(message);
    }
}
