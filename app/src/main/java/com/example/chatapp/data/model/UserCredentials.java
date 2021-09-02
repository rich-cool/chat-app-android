package com.example.chatapp.data.model;

import androidx.annotation.NonNull;

/**
 * Stores user login credentials
 *
 */
public class UserCredentials {

    private String username;
    private String password;

    public UserCredentials(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    @NonNull public String getUsername() {
        return username;
    }

    @NonNull public String getPassword() {
        return password;
    }
}
