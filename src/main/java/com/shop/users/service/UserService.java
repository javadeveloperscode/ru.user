package com.shop.users.service;

import com.shop.users.entity.User;
import com.shop.users.exception.UserNotFoundException;
import com.shop.users.model.dto.UserMapper;
import com.shop.users.model.request.CreateUserRequest;
import com.shop.users.model.response.UserResponse;
import com.shop.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public UserResponse addUser(CreateUserRequest request) {
    log.debug("Adding user: {}", request);

    Long id = userRepository.insert(request.email(), request.name());
    User entity = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));

    return UserMapper.toResponse(entity);
  }

  public UserResponse get(Long id) {
    User entity = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));

    return UserMapper.toResponse(entity);
  }

  public List<UserResponse> findAll() {
    return userRepository.findAll()
        .stream()
        .map(UserMapper::toResponse)
        .toList();
  }
}