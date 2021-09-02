package com.example.chatapp;

import androidx.annotation.NonNull;

import com.example.chatapp.data.model.Token;
import com.example.chatapp.data.model.UserCredentials;
import com.example.chatapp.data.model.response.ClientError;
import com.example.chatapp.data.model.response.Result;
import com.example.chatapp.data.model.response.ServiceResponse;
import com.example.chatapp.serialize.ClientErrorSerializer;
import com.example.chatapp.serialize.TokenSerializer;
import com.example.chatapp.serialize.UserCredentialsSerializer;
import com.example.chatapp.util.Constants;
import com.example.chatapp.util.Logger;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Gets login response from login data source
 *
 */
public class LoginRepository {
    private static final String TAG = Constants.LOGIN_FEATURE;

    private LoginDataSource dataSource;

    public LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    public Result login(@NonNull UserCredentials credentials) {
        try {
            UserCredentialsSerializer serializer = new UserCredentialsSerializer();
            JSONObject jsonCredentials = serializer.toJSON(credentials);

            if (jsonCredentials != null) {
                ServiceResponse response = dataSource.login(jsonCredentials);
                return getLoginResult(response);
            }
            return new Result.Error(new IOException(" Error parsing data"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return new Result.Error(new IOException(" Unexpected error: ", e));
        }
    }

    public void logout(@NonNull Token token) {
        // TODO: logout user
    }

    /**
     * @param response login service response
     * @return the correct result type of the response
     *
     */
    @NonNull
    private Result getLoginResult(@NonNull ServiceResponse response) {
        if (response.successCode()) {
            Token token = response.getData(new TokenSerializer());

            if (token != null) {
                Logger.d(TAG, "Result success");
                return new Result.Success<>(token);
            }
            return new Result.Error(new IOException(" Error parsing response data"));
        }
        else if (response.errorCode()) {
            ClientError error = new ClientError(response.getData(new ClientErrorSerializer()));

            if (error != null) {
                Logger.d(TAG, "Result failure");
                return new Result.Failure<>(error);
            }
        }
        return new Result.Error(new IOException(" Error parsing response error data"));
    }
}
