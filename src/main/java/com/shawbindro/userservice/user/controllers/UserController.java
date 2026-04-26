package com.shawbindro.userservice.user.controllers;


import com.shawbindro.userservice.user.config.security.CustomUserDetails;
import com.shawbindro.userservice.user.config.security.util.JwtUtility;
import com.shawbindro.userservice.user.data.mappers.UserMapper;
import com.shawbindro.userservice.user.dtos.UserResponse;
import com.shawbindro.userservice.user.exceptions.UserNotFoundException;
import com.shawbindro.userservice.user.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.shawbindro.userservice.user.services.UserService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

   private final UserService userService;
   @Autowired
   private JwtUtility jwtUtil;

    @GetMapping
    public ResponseEntity<List<User>>
    getAllUsers(@RequestParam(defaultValue = "1000") long delay) throws InterruptedException {
        Thread.sleep(delay);
        log.info("INFO - Handled by thread: " + Thread.currentThread());
        log.debug("DEBUG - Handled by thread: " + Thread.currentThread());
        return new ResponseEntity<>(userService.fetchAllUsers(),  HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){
        log.info("Request received for user: {}", id);

        log.trace("This is TRACE level - Very detailed logs");
        log.debug("This is DEBUG level - Used for development debugging");
        log.info("This is INFO level - General system information");
        log.warn("This is WARN level - Something might be wrong");
        log.error("This is ERROR level - Something failed");

        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody  User user){
        userService.addUser(user);
        return ResponseEntity.ok("User added successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id,
                                             @RequestBody User updateUser){
        boolean updated = userService.updateUser(Long.valueOf(id), updateUser);
        if (updated)
            return ResponseEntity.ok("User updated successfully");
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFoundException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Users not found");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getProfile(Authentication authentication) {

        if (!(authentication.getPrincipal() instanceof CustomUserDetails userDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = userDetails.getEmail();

        User user = userService.getUserByEmail(email);

        return ResponseEntity.ok(UserMapper.toResponse(user));
    }

    @GetMapping("/test-secure")
    public String testSecure() {
        return "secured";
    }


}
