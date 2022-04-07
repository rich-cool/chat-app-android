package com.example.chatapp.service;

import android.util.Pair;

import com.example.chatapp.data.model.response.ServiceResponse;
import com.example.chatapp.data.model.response.ResponseCode;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.chatapp.BuildConfig;
import com.example.chatapp.util.Constants;
import com.example.chatapp.util.Logger;

import java.io.IOException;

/**
 * Communicate with login api endpoint
 *
 */
public class LoginService extends APIService {
    private static final String TAG = Constants.LOGIN_FEATURE;
    private static final String LOGIN_URL = BuildConfig.LOGIN_URL;
    
    private static volatile LoginService instance;
    private static final Object lock = new Object();

    private LoginService() {}

    public static LoginService getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new LoginService();
                }
            }
        }
        return instance;
    }

    public ServiceResponse login(JSONObject json) {
        try {
            ServiceResponse response = post(LOGIN_URL, json);
            return response;
        }
        catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
            Logger.e(TAG, "Error requesting data");
        }
        return new ServiceResponse(new Pair<>(new ResponseCode(-1000), new JSONObject()));
    }
}
