package com.absjrdev.nexora.user.api;

import com.absjrdev.nexora.user.application.UserService;
import com.absjrdev.nexora.user.domain.User;
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
    ResponseEntity<List<User>> findAll() {
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(
            summary = "Retrieve a user by ID",
            description = "Returns the details of a user identified by the provided ID."
    )
    @GetMapping(value = "/{id}")
    public
    ResponseEntity<User> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @Operation(
            summary = "Create a new user",
            description = "Creates a new user in the system."
    )
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = userService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
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
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
        obj = userService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
