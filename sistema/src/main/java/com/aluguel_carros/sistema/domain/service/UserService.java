package com.aluguel_carros.sistema.domain.service;

import com.aluguel_carros.sistema.infrastructure.entity.User;
import com.aluguel_carros.sistema.infrastructure.exception.UserException;
import com.aluguel_carros.sistema.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserException("Usuário não encontrado"));

    }
}
