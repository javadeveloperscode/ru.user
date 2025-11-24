package com.shop.users.exception;

public record ErrorResponse(
    int status,
    String message
) {}