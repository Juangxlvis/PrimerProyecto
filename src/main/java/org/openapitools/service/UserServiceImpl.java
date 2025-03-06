package org.openapitools.service;

import jakarta.validation.constraints.NotNull;
import org.openapitools.exception.*;
import org.openapitools.model.UserRegistration;
import org.openapitools.model.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final List<UserResponse> users = new ArrayList<>();
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserResponse createUser(@NotNull UserRegistration userRegistration) {
        boolean emailExists = users.stream()
                .anyMatch(user -> user.getEmail().equals(userRegistration.getEmail()));

        boolean idExists = users.stream()
                .anyMatch(user -> user.getId().equals(userRegistration.getId()));

        if (emailExists || idExists) {
            throw new UserAlreadyExistsException("El ID o el correo ya están registrados.");
        }
        if (!isValidPassword(userRegistration.getPassword())) {
            throw new WeakPasswordException("La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número.");
        }
        UserResponse userCreated = new UserResponse();
        userCreated.setId(userRegistration.getId());
        userCreated.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        userCreated.setFullName(userRegistration.getFullName());
        userCreated.setEmail(userRegistration.getEmail());
        userCreated.setDateBirth(userRegistration.getDateBirth());
        userCreated.setRole(userRegistration.getRol().getValue());
        users.add(userCreated);

        return userCreated;
    }

    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        return password.matches(passwordPattern);
    }

    @Override
    public List<UserResponse> getAllUsers(int page, int size) {
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, users.size());

        if (fromIndex >= users.size()) {
            return new ArrayList<>(); // Retorna lista vacía si la página está fuera de rango
        }

        return users.subList(fromIndex, toIndex);
    }

    // Eliminar usuario por ID
    @Override
    public boolean deleteUser(@NotNull String id) {
        return users.removeIf(user -> user.getId().equals(id));
    }


    @Override
    public Optional<UserResponse> getUserById(@NotNull String id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public UserResponse updateUserPassword(@NotNull String id, @NotNull String newPassword, @NotNull String currentPassword) {
        Optional<UserResponse> optionalUser = getUserById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("Usuario no encontrado.");
        }

        UserResponse user = optionalUser.get();

        // Validar que la contraseña actual coincida
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new InvalidPasswordException("La contraseña actual es incorrecta.");
        }

        // Actualizar la contraseña con la nueva encriptada
        user.setPassword(passwordEncoder.encode(newPassword));
        return user;
    }

    public UserResponse updateUser(String id, UserRegistration request) {
        Optional<UserResponse> existingUser = getUserById(id);

        if (existingUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        deleteUser(id); // Eliminar usuario antiguo
        return createUser(request); // Crear usuario actualizado
    }

}