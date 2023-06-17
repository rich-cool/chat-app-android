package com.example.chatapp.data.model;

import androidx.annotation.NonNull;

import com.example.chatapp.serialize.JSONSerializable;

/**
 * Stores JWT received from LoginService
 *
 */
public class Token extends JSONSerializable {

    private String token;

    public Token(@NonNull String token) {
        this.token = token;
    }

    @NonNull
    private String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return token;
    }
}
