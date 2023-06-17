package com.example.chatapp.data.model.response;

import androidx.annotation.NonNull;

import com.example.chatapp.serialize.JSONSerializable;

/**
 * Holds client error response
 */
public class ClientError extends JSONSerializable {

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
