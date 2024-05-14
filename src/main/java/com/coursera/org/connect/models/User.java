package com.coursera.org.connect.models;

public record User(
    long id,
    String name,
    String email,
    String password,
    UserType role,
    String createdAt,
    String updatedAt
) {
    public User {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
    }

    @Override
    public long id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public UserType role() {
        return role;
    }
}
