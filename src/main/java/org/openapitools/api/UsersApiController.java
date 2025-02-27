package org.openapitools.api;

import lombok.RequiredArgsConstructor;
import org.openapitools.exception.EmailAlreadyExistsException;
import org.openapitools.exception.InvalidDataException;
import org.openapitools.model.*;
import org.openapitools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")

public class UsersApiController {

    private final UserService userService;
    private final NativeWebRequest request;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersApiController(UserService userService, NativeWebRequest request, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.request = request;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRegistration request) {
        try {
            UserResponse user = userService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("Conflict", e.getMessage()));
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Bad Request", e.getMessage()));
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<UserResponse> users = userService.getAllUsers(page, size);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserRegistration request) {
        try {
            Optional<UserResponse> existingUser = userService.getUserById(id);
            if (existingUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("Not Found", "User not found"));
            }

            userService.deleteUser(id); // Eliminar el usuario existente
            UserResponse updatedUser = userService.createUser(request); // Crear el usuario actualizado
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Bad Request", e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        Optional<UserResponse> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.ok(new SuccessResponse("User deleted successfully"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Not Found", "User not found"));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<?> updateUserPassword(
            @PathVariable String id,
            @Valid @RequestBody PasswordUpdate passwordUpdate) {

        Optional<UserResponse> userOptional = userService.getUserById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Not Found", "User not found"));
        }

        UserResponse user = userOptional.get();

        // Verificar si la contraseña actual es correcta
        if (!passwordEncoder.matches(passwordUpdate.getCurrentPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Bad Request", "Incorrect current password"));
        }

        // Actualizar la nueva contraseña encriptada
        userService.updateUserPassword(id, passwordEncoder.encode(passwordUpdate.getNewPassword()), passwordUpdate.getCurrentPassword());

        return ResponseEntity.ok(new SuccessResponse("Password updated successfully"));
    }


}
