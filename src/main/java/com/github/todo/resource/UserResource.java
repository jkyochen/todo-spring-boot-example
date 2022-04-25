package com.github.todo.resource;

import com.github.todo.applicaiton.user.UserApplicationService;
import com.github.todo.applicaiton.user.dto.UserLoginParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UserResource {

    private final UserApplicationService service;

    @Autowired
    public UserResource(final UserApplicationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody final UserLoginParameter request) {
        this.service.register((request));
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/login")
                .build()
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody final UserLoginParameter request) {
        this.service.login(request);
        return ResponseEntity.ok().build();
    }
}
