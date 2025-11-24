package com.shop.users.controller;

import com.shop.users.model.request.CreateUserRequest;
import com.shop.users.model.response.UserResponse;
import com.shop.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponse createUser(@RequestBody @Valid CreateUserRequest request) {
    log.info("Creating user with email: {}", request.email());
    return userService.addUser(request);
  }

  @GetMapping("/{id}")
  public UserResponse getUser(@PathVariable Long id) {
    log.info("Fetching user with id: {}", id);
    return userService.get(id);
  }

  @GetMapping
  public List<UserResponse> getAllUsers() {
    log.info("Fetching all users");
    return userService.findAll();
  }
}