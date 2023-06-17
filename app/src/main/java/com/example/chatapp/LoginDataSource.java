package com.example.chatapp;

import android.util.Pair;

import androidx.annotation.NonNull;

import com.example.chatapp.data.model.Token;
import com.example.chatapp.data.model.response.ClientError;
import com.example.chatapp.data.model.response.ResponseCode;
import com.example.chatapp.data.model.response.ServiceResponse;
import com.example.chatapp.data.model.response.Result;
import com.example.chatapp.data.model.UserCredentials;
import com.example.chatapp.service.APIService;
import com.example.chatapp.serialize.ClientErrorSerializer;
import com.example.chatapp.serialize.TokenSerializer;
import com.example.chatapp.serialize.UserCredentialsSerializer;
import com.example.chatapp.util.Constants;
import com.example.chatapp.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Communicate with login api endpoint
 */

public class LoginDataSource {

    private static final String TAG = Constants.LOGIN_FEATURE;

    private static final String LOGIN_URL = BuildConfig.LOGIN_URL;

    private static final Object lock = new Object();
    private static volatile LoginDataSource instance;

    private APIService service;

    private LoginDataSource() {
        service = new APIService();
    }

    public static LoginDataSource getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new LoginDataSource();
                }
            }
        }

        return instance;
    }

    /**
     * @return {@link Result.Success} containing JWT if login successful,
     * {@link Result.Error} containing error data if login unsuccessful, or
     * {@link Result.Failure} containing exception.
     */
    @NonNull
    public ServiceResponse login(@NonNull JSONObject jsonCredentials) {
        try {
            ServiceResponse response = service.post(LOGIN_URL, jsonCredentials);
            return response;
        } catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
            Logger.e(TAG, "Error requesting data");
        }

        return new ServiceResponse(new Pair<>(new ResponseCode(-1000), new JSONObject()));
    }

    public void logout(@NonNull Token token) {
        // TODO: logout user
    }
}
