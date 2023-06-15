package com.example.chatapp.serialize;

import androidx.annotation.NonNull;

import com.example.chatapp.data.model.response.ClientError;

import org.json.JSONException;
import org.json.JSONObject;

public class ClientErrorSerializer implements Serializer<ClientError> {

    public ClientErrorSerializer() {

    }

    @NonNull
    public JSONObject toJSON(@NonNull ClientError error) {
        JSONObject object = new JSONObject();

        try {
            object.put("error", error.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }

    @NonNull
    public ClientError fromJSON(@NonNull JSONObject json) {
        try {
            String error = (String) json.get("error");
            return new ClientError(error);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
