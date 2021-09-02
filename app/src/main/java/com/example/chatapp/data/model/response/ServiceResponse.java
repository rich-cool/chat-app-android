package com.example.chatapp.data.model.response;

import android.util.Pair;

import androidx.annotation.NonNull;

import com.example.chatapp.R;
import com.example.chatapp.serialize.ClientErrorSerializer;
import com.example.chatapp.serialize.Serializer;
import com.example.chatapp.util.Constants;
import com.example.chatapp.util.Logger;

import org.json.JSONObject;

/**
 * Holds response code and json data
 *
 */
public class ServiceResponse {

    private static final String TAG = Constants.LOGIN_FEATURE;

    private Pair<ResponseCode, JSONObject> response;

    public ServiceResponse(@NonNull Pair<ResponseCode, JSONObject> response) {
        this.response = response;
    }

    @NonNull
    public ResponseCode getResponseCode() {
        return response.first;
    }

    @NonNull
    public JSONObject getJSON() {
        return response.second;
    }

    /**
     * @param s type of {@link Serializer} compatible with T
     * @param <T> type of data to be returned by {@link Serializer}
     *
     */
    @SuppressWarnings("unchecked")
    @NonNull
    public <T> T getData(Serializer s) {
        T data = (T) s.fromJSON(getJSON());

        if (data != null) {
            return data;
        }

        // Incompatible serializer or JSON error
        Logger.e(TAG, "Error parsing data");
        return null;
    }

    /**
     * Returns type of response error (currently only {@link ClientError})
     *
     */
    @NonNull
    public ClientError getError() {
        if (getResponseCode().getCodeType() == getResponseCode().CLIENT_ERROR_CODE) {
            return new ClientError(getData(new ClientErrorSerializer()));
        }
        else {}
        return new ClientError(getData(new ClientErrorSerializer()));
    }

    @NonNull public boolean successCode() {
        if (getResponseCode().getCodeType() == getResponseCode().SUCCESS_CODE) {
            return true;
        }
        return false;
    }

    @NonNull
    public boolean errorCode() {
        if (getResponseCode().getCodeType() == getResponseCode().CLIENT_ERROR_CODE) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getResponseCode().toString();
    }
}
