package com.shawbindro.userservice.user.controllers;

import com.shawbindro.userservice.user.models.User;
import com.shawbindro.userservice.user.services.MockUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mock-users")
@Slf4j
public class MockUserController {

    private final MockUserService mockUserService;

    @GetMapping
    public ResponseEntity<List<User>>
    getAllUsers(@RequestParam(defaultValue = "1000") long delay) throws InterruptedException {
        Thread.sleep(delay);
        log.info("INFO - Handled by thread: " + Thread.currentThread());
        log.debug("DEBUG - Handled by thread: " + Thread.currentThread());
        return new ResponseEntity<>(mockUserService.getAllUsers(),  HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
        mockUserService.addUser(user);
        log.info("Handled by thread: " + Thread.currentThread());
        return new ResponseEntity<>(HttpStatus.OK);


    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        return mockUserService.fetchUser(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
