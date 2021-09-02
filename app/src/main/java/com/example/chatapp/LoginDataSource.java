package com.example.chatapp;

import androidx.annotation.NonNull;

import com.example.chatapp.data.model.Token;
import com.example.chatapp.data.model.response.ClientError;
import com.example.chatapp.data.model.response.ServiceResponse;
import com.example.chatapp.data.model.response.Result;
import com.example.chatapp.data.model.UserCredentials;
import com.example.chatapp.service.LoginService;
import com.example.chatapp.serialize.ClientErrorSerializer;
import com.example.chatapp.serialize.TokenSerializer;
import com.example.chatapp.serialize.UserCredentialsSerializer;
import com.example.chatapp.util.Logger;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Requests data from login service
 */

public class LoginDataSource {

    /**
     * @return {@link Result.Success} containing JWT if login successful,
     * {@link Result.Error} containing exception if unsuccessful.
     */
    @NonNull
    public ServiceResponse login(@NonNull JSONObject json) {
        ServiceResponse response = LoginService.getInstance().login(json);
        return response;
    }

    public void logout(@NonNull Token token) {
        // TODO: logout user
    }
}
