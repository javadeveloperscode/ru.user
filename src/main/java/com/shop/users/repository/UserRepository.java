package com.shop.users.repository;

import com.shop.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

  private final JdbcTemplate jdbc;

  public Long insert(String email, String name) {
    return jdbc.queryForObject(
        """
        INSERT INTO users(email, name)
        VALUES (?, ?)
        RETURNING id
        """,
        Long.class,
        email, name
    );
  }

  public Optional<User> findById(Long id) {
    return jdbc.query(
        """
        SELECT id, email, name, createdAt
        FROM users
        WHERE id = ?
        """,
        (rs, rowNum) -> new User(
            rs.getLong("id"),
            rs.getString("email"),
            rs.getString("name"),
            rs.getTimestamp("createdAt").toLocalDateTime()
        ),
        id
    ).stream().findFirst();
  }

  public List<User> findAll() {
    return jdbc.query(
        """
        SELECT id, email, name, createdAt
        FROM users
        ORDER BY id DESC
        """,
        (rs, rowNum) -> new User(
            rs.getLong("id"),
            rs.getString("email"),
            rs.getString("name"),
            rs.getTimestamp("createdAt").toLocalDateTime()
        )
    );
  }
}