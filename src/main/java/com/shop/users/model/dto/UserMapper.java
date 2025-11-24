package com.shop.users.model.dto;

import com.shop.users.entity.User;
import com.shop.users.model.response.UserResponse;


public class UserMapper {

  public static UserResponse toResponse(User u) {
    return new UserResponse(
        u.getId(),
        u.getEmail(),
        u.getName(),
        u.getCreatedAt()
    );
  }
}