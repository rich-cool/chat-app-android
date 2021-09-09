package com.example.chatapp.data.model.response;

import androidx.annotation.NonNull;

/**
 * Holds client error response
 *
 */
public class ClientError {
    private String error;

    public ClientError(@NonNull String error) {
        this.error = error;
    }

    @NonNull
    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return error;
    }
}
