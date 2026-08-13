package com.absjrdev.abscommerce.user.api;

import com.absjrdev.abscommerce.user.application.UserService;
import com.absjrdev.abscommerce.user.domain.User;
import com.absjrdev.abscommerce.user.dto.UserRequestDTO;
import com.absjrdev.abscommerce.user.dto.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "Users", description = "Operations related to users")
public
class UserController {

    @Autowired
    private
    UserService userService;

    @Operation(
            summary = "Retrieve all users",
            description = "Returns a list of all registered users."
    )
    @GetMapping
    public
    ResponseEntity<List<UserResponseDTO>> findAll() {

        List<UserResponseDTO> list = userService.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();

        return ResponseEntity.ok().body(list);
    }

    @Operation(
            summary = "Retrieve a user by ID",
            description = "Returns the details of a user identified by the provided ID."
    )
    @GetMapping(value = "/{id}")
    public
    ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(toResponseDTO(user));
    }

    @Operation(
            summary = "Create a new user",
            description = "Creates a new user in the system."
    )
    @PostMapping
    public ResponseEntity<UserResponseDTO> insert(@RequestBody UserRequestDTO dto){
        User user = toEntity(dto);

        user = userService.insert(user);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(toResponseDTO(user));
    }

    @Operation(
            summary = "Delete a user",
            description = "Removes a user identified by the provided ID from the system."
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Update an existing user",
            description = "Updates the information of an existing user identified by the provided ID."
    )
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UserRequestDTO  dto){
        User user = toEntity(dto);

        user = userService.update(id, user);

        return ResponseEntity.ok().body(toResponseDTO(user));
    }
    private UserResponseDTO toResponseDTO(User user) {

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }

    private User toEntity(UserRequestDTO dto) {

        return new User(
                null,
                dto.name(),
                dto.email(),
                dto.phone(),
                dto.password()
        );
    }

}
