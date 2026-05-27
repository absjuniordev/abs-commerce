package com.absjrdev.nexora.user.api;

import com.absjrdev.nexora.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public
class UserController {

    @GetMapping
    public
    ResponseEntity<User> findAll() {
        User user = new User(1L, "fau", "fau@gmail","1324432423", "324324");
        return ResponseEntity.ok().body(user);
    }
}
