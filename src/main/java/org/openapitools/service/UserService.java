package org.openapitools.service;

import jakarta.validation.constraints.NotNull;
import org.openapitools.model.UserRegistration;
import org.openapitools.model.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponse createUser(@NotNull UserRegistration userRegistration);

    List<UserResponse> getAllUsers(int page, int size);

    // Eliminar usuario por ID
    boolean deleteUser(@NotNull String id);

    Optional<UserResponse> getUserById(@NotNull String id);

    UserResponse updateUserPassword(@NotNull String id, @NotNull String newPassword, @NotNull String currentPassword);
}
