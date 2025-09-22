package com.aluguel_carros.sistema.infrastructure.exception;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}
