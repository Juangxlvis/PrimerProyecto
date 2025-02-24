package org.openapitools.service;

import jakarta.validation.constraints.NotNull;
import org.openapitools.exception.EmailAlreadyExistsException;
import org.openapitools.model.UserRegistration;
import org.openapitools.model.UserResponse;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    private final List<UserResponse> users = new ArrayList<>();


    public  UserResponse createUser(@NotNull UserRegistration userRegistration) {
        boolean emailExists = users.stream()
                .anyMatch(user -> user.getEmail().equals(userRegistration.getEmail()));

        if (emailExists) {
            throw new EmailAlreadyExistsException("El correo ya está registrado.");
        }
        UserResponse userCreated = new UserResponse();
        userCreated.setId(userRegistration.getId());
        userCreated.setFullName(userRegistration.getFullName());
        userCreated.setEmail(userRegistration.getEmail());
        userCreated.setDateBirth(userRegistration.getDateBirth());
        userCreated.setRol(userRegistration.getRol().getValue());
        users.add(userCreated);

        return userCreated;
    }

    public List<UserResponse> getAllUsers(int page, int size) {
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, users.size());

        if (fromIndex >= users.size()) {
            return new ArrayList<>(); // Retorna lista vacía si la página está fuera de rango
        }

        return users.subList(fromIndex, toIndex);
    }

    // Eliminar usuario por ID
    public boolean deleteUser(@NotNull String id) {
        return users.removeIf(user -> user.getId().equals(id));
    }


    public Optional<UserResponse> getUserById(@NotNull String id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }



}